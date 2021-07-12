package mostafaism.com.github.fibonaccinumbersgame.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import mostafaism.com.github.fibonaccinumbersgame.model.entity.Game;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.Player;
import mostafaism.com.github.fibonaccinumbersgame.model.request.CreateGameRequest;
import mostafaism.com.github.fibonaccinumbersgame.model.request.MoveRequest;

public interface GameService {

    Game createGame(CreateGameRequest createGameRequest);

    boolean deleteGame(UUID gameCode);

    Player getOnTurnPlayer(UUID gameCode);

    int playAMove(UUID gameCode, UUID playerCode, MoveRequest moveRequest);

    List<Map.Entry<String, Integer>> getPlayerScores(UUID gameCode);

}
