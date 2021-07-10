package mostafaism.com.github.fibonaccinumbersgame.model.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import mostafaism.com.github.fibonaccinumbersgame.model.entity.Player;
import mostafaism.com.github.fibonaccinumbersgame.model.response.NextPlayerResponse;

@Component
public class PlayerToNextPlayerResponseMapper implements Function<Player, NextPlayerResponse> {

    @Override
    public NextPlayerResponse apply(Player p) {
        return p == null ? null : new NextPlayerResponse(p.getPlayerName());
    }

}
