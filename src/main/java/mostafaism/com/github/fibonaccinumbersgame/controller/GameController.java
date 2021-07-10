package mostafaism.com.github.fibonaccinumbersgame.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import mostafaism.com.github.fibonaccinumbersgame.model.mapper.GameToGameResponseMapper;
import mostafaism.com.github.fibonaccinumbersgame.model.request.CreateGameRequest;
import mostafaism.com.github.fibonaccinumbersgame.model.response.GameResponse;
import mostafaism.com.github.fibonaccinumbersgame.service.GameService;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class GameController {

    private final GameService gameService;
    private final GameToGameResponseMapper mapper;

    @PostMapping(path = "/new-game", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public GameResponse createGame(@RequestBody @Valid CreateGameRequest createGameRequest) {
        return mapper.apply(gameService.createGame(createGameRequest));
    }

}
