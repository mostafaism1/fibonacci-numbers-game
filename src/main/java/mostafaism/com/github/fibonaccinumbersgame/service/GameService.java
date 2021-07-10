package mostafaism.com.github.fibonaccinumbersgame.service;

import mostafaism.com.github.fibonaccinumbersgame.model.entity.Game;
import mostafaism.com.github.fibonaccinumbersgame.model.request.CreateGameRequest;

public interface GameService {

    Game createGame(CreateGameRequest createGameRequest);

}
