package mostafaism.com.github.fibonaccinumbersgame.model.request;

import java.util.List;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MoveRequest {

    @Size(min = 3, max = 3)
    List<Integer> numbers;

}
