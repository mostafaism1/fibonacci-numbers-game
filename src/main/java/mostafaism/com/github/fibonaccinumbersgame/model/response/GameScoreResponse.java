package mostafaism.com.github.fibonaccinumbersgame.model.response;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GameScoreResponse {

    List<Map.Entry<String, Integer>> scores;

}
