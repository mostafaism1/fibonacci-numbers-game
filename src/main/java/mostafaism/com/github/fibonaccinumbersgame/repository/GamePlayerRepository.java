package mostafaism.com.github.fibonaccinumbersgame.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mostafaism.com.github.fibonaccinumbersgame.model.entity.GamePlayer;

@Repository
public interface GamePlayerRepository extends CrudRepository<GamePlayer, Long> {

}
