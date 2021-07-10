package mostafaism.com.github.fibonaccinumbersgame.service;

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
import mostafaism.com.github.fibonaccinumbersgame.repository.GameRepository;

@Service
@AllArgsConstructor
@Transactional
public class DefaultGameService implements GameService {

    private static final int MAXIMUM_ACTIVE_GAMES = 100;
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

}
