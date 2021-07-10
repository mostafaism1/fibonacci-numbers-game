package mostafaism.com.github.fibonaccinumbersgame.exception;

public class MaximumGamesLimitReachedException extends RuntimeException {

    public MaximumGamesLimitReachedException() {
        super("The maximum number of active games has been reached. Please try again later");
    }

}
