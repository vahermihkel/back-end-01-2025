package ee.mihkel.card_game.service;

import ee.mihkel.card_game.entity.Game;
import ee.mihkel.card_game.entity.Player;
import ee.mihkel.card_game.model.Card;
import ee.mihkel.card_game.model.Guess;
import ee.mihkel.card_game.model.GuessResponse;
import ee.mihkel.card_game.repository.GameRepository;
import ee.mihkel.card_game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;

    Card baseCard;
    int points;
    int lives;
    Long startRoundTime;
    Long startGameTime;
    Player player;

    public Card startGameAndInitValues(String playerName) {
        player = playerRepository.findById(playerName).orElseThrow();
        baseCard = new Card();
        points = 0;
        lives = 3;
        startRoundTime = new Date().getTime();
        startGameTime = new Date().getTime();
        return baseCard;
    }

    public GuessResponse checkIfHasLives(GuessResponse response) {
        if (lives == 0) {
            if (baseCard == null) {
                response.setMessage("Enne arvamist pead mängu alustama!");
            } else {
                response.setMessage("Mäng on juba läbi, su elud on nullis. Alusta uut mängu!");
            }
            response.setPoints(points);
            response.setLives(lives);
            return response;
        }
        return null;
    }

    public GuessResponse checkIfGuessedOnTime(GuessResponse response, Card resultCard) {
        Long guessTime = new Date().getTime() - 10*1000;
        if (startRoundTime < guessTime) {
            lives--;
            if (lives == 0) {
                response.setMessage("Läks liiga kaua aega. Elud on otsas!");
                saveToDatabase();
            } else {
                response.setMessage("Läks liiga kaua aega. Arva uuesti!");
            }
            response.setPoints(points);
            response.setLives(lives);
            baseCard = resultCard;
            startRoundTime = new Date().getTime();
            return response;
        }
        startRoundTime = new Date().getTime();
        return null;
    }

    public void checkIfGuessIsCorrect(Guess guess, Card resultCard, GuessResponse response) {
        try {
            if (baseCard.getValue() < resultCard.getValue() && guess == Guess.HIGHER ||
                    baseCard.getValue() > resultCard.getValue() && guess == Guess.LOWER ||
                    baseCard.getValue() == resultCard.getValue() && guess == Guess.EQUAL
            ) {
                points++;
                response.setMessage("Õige");
            } else {
                lives--;
                if (lives > 0) {
                    response.setMessage("Vale");
                } else {
                    response.setMessage("Vale. Mäng lõppenud!");
                    saveToDatabase();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Enne arvamist pead mängu alustama!");
        }
    }

    public void getNewRoundValues(Card resultCard, GuessResponse response) {
        baseCard = resultCard;
        response.setLives(lives);
        response.setPoints(points);
    }

    private void saveToDatabase() {
        Game game = new Game();
        game.setScore(points);
        game.setDuration(new Date().getTime() - startGameTime);
        game.setPlayer(player);
        gameRepository.save(game);
    }
}
