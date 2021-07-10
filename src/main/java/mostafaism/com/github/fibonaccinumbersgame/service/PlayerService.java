package mostafaism.com.github.fibonaccinumbersgame.service;

import mostafaism.com.github.fibonaccinumbersgame.model.entity.Player;

public interface PlayerService {

    Player create(String name);

    Player createOrGetExisting(String name);

}
