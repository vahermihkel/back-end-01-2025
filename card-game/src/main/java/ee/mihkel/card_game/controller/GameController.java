package ee.mihkel.card_game.controller;

import ee.mihkel.card_game.entity.Game;
import ee.mihkel.card_game.model.Card;
import ee.mihkel.card_game.model.Guess;
import ee.mihkel.card_game.model.GuessResponse;
import ee.mihkel.card_game.repository.GameRepository;
import ee.mihkel.card_game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class GameController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameService gameService;

    @GetMapping("start-game")
    public Card startGame(@RequestParam String playerName) {
        return gameService.startGameAndInitValues(playerName);
    }

    // http://localhost:8080/guess-card?guess=LOWER
    @GetMapping("guess-card")
    public GuessResponse cardGuess(@RequestParam Guess guess) {
        GuessResponse response = new GuessResponse();
        GuessResponse errorResponse = gameService.checkIfHasLives(response);
        if (errorResponse != null) return errorResponse;

        Card resultCard = new Card();
        response.setCard(resultCard);

        errorResponse = gameService.checkIfGuessedOnTime(response, resultCard);
        if (errorResponse != null) return errorResponse;

        gameService.checkIfGuessIsCorrect(guess, resultCard, response);
        gameService.getNewRoundValues(resultCard, response);
        return response;
    }

    // localhost:8080/games?sort=duration,asc
    // localhost:8080/games?sort=duration,desc
    // localhost:8080/games?sort=score,asc
    // localhost:8080/games?sort=score,desc
    @GetMapping("games")
    public Page<Game> getGames(Pageable pageable) {
        return gameRepository.findAll(pageable);
    }

    @GetMapping("player-games")
    public List<Game> getPlayerGames(@RequestParam String playerName) {
        return gameRepository.findByPlayer_Name(playerName);
    }
}
