package mostafaism.com.github.fibonaccinumbersgame.model.response;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import mostafaism.com.github.fibonaccinumbersgame.model.dto.PlayerCodeDto;

@Data
@AllArgsConstructor
@Builder
public class GameResponse {

    private UUID gameCode;
    private List<PlayerCodeDto> playerCodes;

}
