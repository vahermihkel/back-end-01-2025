package ee.mihkel.card_game.model;

import lombok.Getter;

@Getter
public enum Rank {
    TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGTH(8), NINE(9),
    TEN(10), JACK(10), QUEEN(10), KING(10), ACE(10);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank getRandomRank() {
        int randomNumber = (int) (Math.random() * values().length);
        return values()[randomNumber];
    }
}
