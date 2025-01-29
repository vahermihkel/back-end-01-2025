package ee.mihkel.card_game.model;

import lombok.Data;

@Data
public class GuessResponse {
    private String message;
    private Card card;
    private int points;
    private int lives;
}
