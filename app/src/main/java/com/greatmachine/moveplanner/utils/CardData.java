package com.greatmachine.moveplanner.utils;


/**
 * Stores data gathered about a single card in the movement deck.
 */
public class CardData {
    public final CardType cardType;
    public boolean inDeck;
    public int servant1Detainments;
    public int servant2Detainments;
    public int servant3Detainments;


    public CardData(CardType cardType) {
        this.cardType = cardType;
        this.inDeck = true;
        this.servant1Detainments = 0;
        this.servant2Detainments = 0;
        this.servant3Detainments = 0;
    }

    public CardData(CardType cardType, boolean inDeck, int servent1Detainments, int servent2Detainments, int servent3Detainments) {
        this.cardType = cardType;
        this.inDeck = inDeck;
        this.servant1Detainments = servent1Detainments;
        this.servant2Detainments = servent2Detainments;
        this.servant3Detainments = servent3Detainments;
    }

}
