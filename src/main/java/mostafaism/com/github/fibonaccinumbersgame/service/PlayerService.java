package mostafaism.com.github.fibonaccinumbersgame.service;

import mostafaism.com.github.fibonaccinumbersgame.model.entity.Game;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.Player;

public interface PlayerService {

    Player create(Game game, String playerName);

}
