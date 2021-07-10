package mostafaism.com.github.fibonaccinumbersgame.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.Player;
import mostafaism.com.github.fibonaccinumbersgame.repository.PlayerRepository;

@Service
@AllArgsConstructor
public class DefaultPlayerService implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Player create(String name) {
        Player player = new Player();
        player.setName(name);
        return playerRepository.save(player);
    }

    @Override
    public Player createOrGetExisting(String name) {
        Optional<Player> player = playerRepository.getByName(name);
        if (player.isEmpty()) {
            return create(name);
        }
        return player.get();
    }

}
