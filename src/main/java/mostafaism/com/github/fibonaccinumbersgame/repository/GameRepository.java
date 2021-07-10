package mostafaism.com.github.fibonaccinumbersgame.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mostafaism.com.github.fibonaccinumbersgame.model.entity.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    int countByIsEnded(boolean isEnded);

    void deleteByGameCode(UUID gameCode);

}
