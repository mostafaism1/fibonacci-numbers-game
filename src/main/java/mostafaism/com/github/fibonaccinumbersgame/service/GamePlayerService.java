package mostafaism.com.github.fibonaccinumbersgame.service;

import mostafaism.com.github.fibonaccinumbersgame.model.entity.Game;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.GamePlayer;

public interface GamePlayerService {

    GamePlayer create(Game game, String playerName);

}
