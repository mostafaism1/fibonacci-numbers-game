package mostafaism.com.github.fibonaccinumbersgame.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class NextPlayerResponse {
    private String next;
}
