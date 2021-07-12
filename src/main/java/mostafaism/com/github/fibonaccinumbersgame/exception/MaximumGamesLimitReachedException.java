package mostafaism.com.github.fibonaccinumbersgame.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The maximum number of active games has been reached. Please try again later")
public class MaximumGamesLimitReachedException extends RuntimeException {

    public MaximumGamesLimitReachedException() {
        super("The maximum number of active games has been reached. Please try again later");
    }

}
