package mostafaism.com.github.fibonaccinumbersgame.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.Game;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.GamePlayer;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.Player;
import mostafaism.com.github.fibonaccinumbersgame.model.request.CreateGameRequest;
import mostafaism.com.github.fibonaccinumbersgame.repository.GameRepository;

@Service
@AllArgsConstructor
public class DefaultGameService implements GameService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final GamePlayerService gamePlayerService;

    @Override
    public Game createGame(CreateGameRequest createGameRequest) {
        final Game game = createNewGame();
        List<Player> players = createOrGetExistingPlayers(createGameRequest.getPlayers());
        List<GamePlayer> gamePlayers = createGamePlayers(game, players);
        game.setGamePlayers(gamePlayers);
        return gameRepository.save(game);
    }

    private Game createNewGame() {
        Game game = Game.builder().gameCode(UUID.randomUUID()).turn(0).isEnded(false).build();
        return gameRepository.save(game);
    }

    private List<Player> createOrGetExistingPlayers(List<String> players) {
        return players.stream().map(playerService::createOrGetExisting).collect(Collectors.toList());
    }

    private List<GamePlayer> createGamePlayers(Game game, List<Player> players) {
        return players.stream().map(player -> gamePlayerService.create(game, player)).collect(Collectors.toList());
    }

}
