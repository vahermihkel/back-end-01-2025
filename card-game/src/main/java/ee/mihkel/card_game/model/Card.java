package ee.mihkel.card_game.model;

import lombok.Data;

@Data
public class Card {
    private Suit suit;
    private Rank rank;
    private int value;

    public Card() {
        this.suit = Suit.getRandomSuit();
        this.rank = Rank.getRandomRank();
        this.value = this.rank.getValue();
    }
}
