package mostafaism.com.github.fibonaccinumbersgame.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mostafaism.com.github.fibonaccinumbersgame.model.entity.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    Optional<Player> getByName(String name);

}
