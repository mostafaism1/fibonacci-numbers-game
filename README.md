# Fibonacci Numbers Game

## Statement

1. Introduction

   - ‘Fibonacci Numbers Game’ or ‘Numbers Game’ is a **multi-player** turn-base game, in each turn a
     player selects **3** numbers and these numbers assigned to that player, so other players can’t select these
     numbers again.

   - Player score is calculated based on **count of Fibonacci numbers** assigned to that player. For
     example if ‘Player1’ started the game by selected {3, 4, 5} then his score will be 2 as (3 and 5 are
     Fibonacci numbers), then if ‘Player2’ selected {5, 6, 8} then ‘player2’ score will be 1 as (5 is already
     selected by ‘Player1’ and 8 is Fibonacci) and so on.

2. Task

   - Your company assigned to you the task of implementing **REST** API gaming engine server for
     ‘Numbers Game’ using **Java**, **Spring-boot** technology and store running games related data on
     **MySQL** database, taking in consideration that restarting the gaming engine server should not impact
     running games statuses.

3. Requirements

   - Your source code must include **Java Docs** and **Unit Test** cases for each implemented class, and
     preferred **integration testing**.

4. REST API Specification

   1. Create a Game

      - HTTP Verb: POST
      - URL: /api/new-game
      - Request Body:
        `{"players": ["player1", "player2", "player3"]}`
      - Response:
        `{"game-code":<number>, "player-codes": [{"name":"player1", "code":<number>}, {"name":"player1", "code":<number>}, {"name":"player1", "code":<number>}]`

   2. End a Game

      - HTTP Verb: POST
      - URL: /api/{game-code}/end
      - Request Body: Empty
      - Response: Empty

   3. Game Score

      - HTTP Verb: GET
      - URL: /api/{game-code}/score
      - Request Body: Empty
      - Response: `{"scores": {"player1": <number>, "player2": <number>, "player3": <number>}}`

   4. Get On-Turn Player

      - HTTP Verb: GET
      - URL: /api/{game-code}/turn
      - Request Body: Empty
      - Response: `{"next": "player1"}`

   5. Play a Move
      - HTTP Verb: POST
      - URL: /api/{game-code}/{player-code}/play
      - Request Body: `{"Numbers": [3, 4, 5]}`
      - Response: `{"player-score": <number>}`

5. Error Handling
   1. In case of request data doesn’t meet the required game specification, server should return HTTP
      Code 400 and JSON exception payload `{ “error”:”<readable text massage describe the error>”}`
   2. In other server related errors, server should return HTTP Code 500 and JSON exception payload
      `{ “error”:”<readable text massage describe the error>”}`
