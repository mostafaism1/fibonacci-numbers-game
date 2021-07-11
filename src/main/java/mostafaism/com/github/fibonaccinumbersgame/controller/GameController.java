package mostafaism.com.github.fibonaccinumbersgame.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.Game;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.Player;
import mostafaism.com.github.fibonaccinumbersgame.model.mapper.GameToGameResponseMapper;
import mostafaism.com.github.fibonaccinumbersgame.model.mapper.PlayerToNextPlayerResponseMapper;
import mostafaism.com.github.fibonaccinumbersgame.model.request.CreateGameRequest;
import mostafaism.com.github.fibonaccinumbersgame.model.request.MoveRequest;
import mostafaism.com.github.fibonaccinumbersgame.model.response.GameResponse;
import mostafaism.com.github.fibonaccinumbersgame.model.response.NextPlayerResponse;
import mostafaism.com.github.fibonaccinumbersgame.model.response.PlayerScoreResponse;
import mostafaism.com.github.fibonaccinumbersgame.service.GameService;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class GameController {

    private final GameService gameService;
    private final GameToGameResponseMapper gameToGameResponseMapper;
    private final PlayerToNextPlayerResponseMapper playerToNextPlayerResponseMapper;

    @PostMapping(path = "/new-game", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public GameResponse createGame(@RequestBody @Valid CreateGameRequest createGameRequest) {
        Game game = gameService.createGame(createGameRequest);
        return gameToGameResponseMapper.apply(game);
    }

    @PostMapping(path = "/{gameCode}/end")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void endGame(@PathVariable(name = "gameCode") UUID gameCode) {
        gameService.deleteGame(gameCode);
    }

    @GetMapping(path = "/{gameCode}/turn")
    @ResponseStatus(code = HttpStatus.OK)
    public NextPlayerResponse getNextPlayer(@PathVariable(name = "gameCode") UUID gameCode) {
        Player onTurnPlayer = gameService.getOnTurnPlayer(gameCode);
        return playerToNextPlayerResponseMapper.apply(onTurnPlayer);
    }

    @PostMapping(path = "/{gameCode}/{playerCode}/play")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PlayerScoreResponse playAMove(@PathVariable(name = "gameCode") UUID gameCode,
            @PathVariable(name = "playerCode") UUID playerCode, @RequestBody @Valid MoveRequest moveRequest) {
        int score = gameService.playAMove(gameCode, playerCode, moveRequest);
        return new PlayerScoreResponse(score);
    }

}
