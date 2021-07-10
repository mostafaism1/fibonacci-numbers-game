package mostafaism.com.github.fibonaccinumbersgame.model.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PlayerCodeDto {

    private String name;
    private UUID code;

}
