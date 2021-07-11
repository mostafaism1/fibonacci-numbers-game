package mostafaism.com.github.fibonaccinumbersgame.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.Game;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.Player;
import mostafaism.com.github.fibonaccinumbersgame.repository.PlayerRepository;

@Service
@AllArgsConstructor
@Transactional
public class DefaultPlayerService implements PlayerService {

    private final PlayerRepository playerRepository;

    public Player create(Game game, String playerName) {
        Player player = Player.builder().playerName(playerName).game(game).playerCode(UUID.randomUUID()).score(0).build();
        return playerRepository.save(player);
    }

}
