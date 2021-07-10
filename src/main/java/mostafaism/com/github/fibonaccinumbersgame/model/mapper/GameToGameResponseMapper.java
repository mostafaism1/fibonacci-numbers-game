package mostafaism.com.github.fibonaccinumbersgame.model.mapper;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import mostafaism.com.github.fibonaccinumbersgame.model.dto.PlayerCodeDto;
import mostafaism.com.github.fibonaccinumbersgame.model.entity.Game;
import mostafaism.com.github.fibonaccinumbersgame.model.response.GameResponse;

@Component
public class GameToGameResponseMapper implements Function<Game, GameResponse> {

    @Override
    public GameResponse apply(Game g) {
        return g == null ? null
                : new GameResponse(g.getGameCode(),
                        g.getPlayers().stream()
                                .map(player -> new PlayerCodeDto(player.getPlayerName(), player.getPlayerCode()))
                                .collect(Collectors.toList()));
    }

}
