package mostafaism.com.github.fibonaccinumbersgame.model.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class CreateGameRequest {
    @NotEmpty
    List<String> players;
}
