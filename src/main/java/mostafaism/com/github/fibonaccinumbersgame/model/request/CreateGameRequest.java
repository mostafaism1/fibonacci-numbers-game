package mostafaism.com.github.fibonaccinumbersgame.model.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateGameRequest {
    @Size(min = 2, max = 10)
    List<@NotBlank @Size(max = 8, message = "Player name must be less than 9 characters long") @Pattern(regexp = "^[A-Za-z0-9]*$") String> players;
}
