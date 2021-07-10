package mostafaism.com.github.fibonaccinumbersgame.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.Game;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.GamePlayer;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.Player;
import mostafaism.com.github.fibonaccinumbersgame.repository.GamePlayerRepository;

@Service
@AllArgsConstructor
public class DefaultGamePlayerService implements GamePlayerService {

    private final GamePlayerRepository gamePlayerRepository;

    public GamePlayer create(Game game, Player player) {
        GamePlayer gamePlayer = GamePlayer.builder().game(game).player(player).playerCode(UUID.randomUUID()).build();
        return gamePlayerRepository.save(gamePlayer);
    }

}
