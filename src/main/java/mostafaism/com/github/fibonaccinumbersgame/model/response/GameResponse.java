package mostafaism.com.github.fibonaccinumbersgame.model.response;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import mostafaism.com.github.fibonaccinumbersgame.model.dto.PlayerCode;

@Data
@AllArgsConstructor
@Builder
public class GameResponse {

    private UUID gameCode;
    private List<PlayerCode> playerCodes;

}
