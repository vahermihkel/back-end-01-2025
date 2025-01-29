package ee.mihkel.card_game.model;

public enum Suit {
    DIAMOND, SPADE, HEART, CLUB;

    public static Suit getRandomSuit() {
        int randomNumber = (int) (Math.random() * values().length);
        return values()[randomNumber];
    }
}
