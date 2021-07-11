package mostafaism.com.github.fibonaccinumbersgame.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mostafaism.com.github.fibonaccinumbersgame.exception.MaximumGamesLimitReachedException;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.Game;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.Player;
import mostafaism.com.github.fibonaccinumbersgame.model.request.CreateGameRequest;
import mostafaism.com.github.fibonaccinumbersgame.model.request.MoveRequest;
import mostafaism.com.github.fibonaccinumbersgame.repository.GameRepository;

@Service
@AllArgsConstructor
@Transactional
public class DefaultGameService implements GameService {

    private static final int MAXIMUM_ACTIVE_GAMES = 100;
    private static final int MAXIMUM_GAME_TURNS = 20;
    private final GameRepository gameRepository;
    private final PlayerService playerService;

    @Override
    public Game createGame(CreateGameRequest createGameRequest) {
        if (!canAcceptNewGames()) {
            throw new MaximumGamesLimitReachedException();
        }
        final Game game = createNewGame();
        List<Player> players = createPlayers(game, createGameRequest.getPlayers());
        game.setPlayers(players);
        game.setOnTurnPlayer(players.get(0));
        return gameRepository.save(game);
    }

    private Game createNewGame() {
        Game game = Game.builder().gameCode(UUID.randomUUID()).turn(0).isEnded(false).build();
        return gameRepository.save(game);
    }

    private List<Player> createPlayers(Game game, List<String> playerNames) {
        return playerNames.stream().map(playerName -> playerService.create(game, playerName))
                .collect(Collectors.toList());
    }

    private boolean canAcceptNewGames() {
        return countActiveGames() <= MAXIMUM_ACTIVE_GAMES - 1;
    }

    private int countActiveGames() {
        return gameRepository.countByIsEnded(false);
    }

    @Override
    public boolean deleteGame(UUID gameCode) {
        gameRepository.deleteByGameCode(gameCode);
        return true;
    }

    @Override
    public Player getOnTurnPlayer(UUID gameCode) {
        Game game = getGameByGameCode(gameCode);
        return game.getOnTurnPlayer();
    }

    @Override
    public int playAMove(UUID gameCode, UUID playerCode, MoveRequest moveRequest) {
        Game game = getGameByGameCode(gameCode);
        validateGameIsNotEnded(game);
        Player player = game.getOnTurnPlayer();
        validatePlayerCode(player, playerCode);
        List<Integer> newFibonacciNumbers = filterNewFibonacciNumbers(moveRequest.getNumbers(), game);
        updateGameFibonacciNumbers(game, newFibonacciNumbers);
        incrementPlayerScore(player, newFibonacciNumbers.size());
        updateGameTurn(game);
        updateOnTurnPlayer(game);
        gameRepository.save(game);
        return player.getScore();
    }

    private void validateGameIsNotEnded(Game game) {
        if (game.isEnded()) {
            throw new IllegalArgumentException("This game has already been ended");
        }
    }

    private void updateGameTurn(Game game) {
        List<Player> players = game.getPlayers();
        Player onTurnPlayer = game.getOnTurnPlayer();
        int onTurnPlayerIndex = players.indexOf(onTurnPlayer);
        if (onTurnPlayerIndex == players.size()) {
            int turn = game.getTurn();
            if (turn == MAXIMUM_GAME_TURNS) {
                game.setEnded(true);
                return;
            }
            game.setTurn(++turn);
        }
    }

    private void updateOnTurnPlayer(Game game) {
        if (game.isEnded()) {
            return;
        }
        List<Player> players = game.getPlayers();
        Player onTurnPlayer = game.getOnTurnPlayer();
        int onTurnPlayerIndex = players.indexOf(onTurnPlayer);
        int nextOnTurnPlayerIndex = (onTurnPlayerIndex + 1) % players.size();
        Player nextOnTurnPlayer = players.get(nextOnTurnPlayerIndex);
        game.setOnTurnPlayer(nextOnTurnPlayer);
    }

    private List<Integer> filterNewFibonacciNumbers(List<Integer> numbers, Game game) {
        List<Integer> fibonacciNumbers = filterFibonacciNumbers(numbers);
        List<Integer> result = new ArrayList<>(fibonacciNumbers);
        result.removeAll(game.getFibonacciNumbers());
        return result;
    }

    private void incrementPlayerScore(Player player, int amount) {
        int newPlayerScore = player.getScore() + amount;
        player.setScore(newPlayerScore);
    }

    private void updateGameFibonacciNumbers(Game game, List<Integer> newFibonacciNumbers) {
        game.getFibonacciNumbers().addAll(newFibonacciNumbers);
    }

    private List<Integer> filterFibonacciNumbers(List<Integer> numbers) {
        return numbers.stream().filter(this::isFibonacciNumber).collect(Collectors.toList());
    }

    private Game getGameByGameCode(UUID gameCode) {
        return gameRepository.getByGameCode(gameCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid game code"));
    }

    private void validatePlayerCode(Player player, UUID playerCode) {
        if (!player.getPlayerCode().equals(playerCode)) {
            throw new IllegalArgumentException("Invalid player code");
        }
    }

    private boolean isFibonacciNumber(int n) {
        return isPerfectSquare(5 * n * n + 4) || isPerfectSquare(5 * n * n - 4);
    }

    private boolean isPerfectSquare(int n) {
        int x = (int) Math.sqrt(n);
        return Math.pow(x, 2) == n;
    }

}
