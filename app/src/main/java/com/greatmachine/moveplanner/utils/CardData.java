package com.greatmachine.moveplanner.utils;


/**
 * Stores data gathered about a single card in the movement deck.
 */
public class CardData {
    public final CardType cardType;
    public int servant1Detainments;
    public int servant2Detainments;
    public int servant3Detainments;


    public CardData(CardType cardType) {
        this.cardType = cardType;
        this.servant1Detainments = 0;
        this.servant2Detainments = 0;
        this.servant3Detainments = 0;
    }

    public CardData(CardType cardType, int servent1Detainments, int servent2Detainments, int servent3Detainments) {
        this.cardType = cardType;
        this.servant1Detainments = servent1Detainments;
        this.servant2Detainments = servent2Detainments;
        this.servant3Detainments = servent3Detainments;
    }


    /**
     * Sets the detainment count for a specific servant, and leaves the others unmodified
     * @param servantNumber the servant number, which should be 1-3
     * @param detainments the number of detainments
     */
    public void setDetainmentByServant(int servantNumber, int detainments){
        checkServantAndDetainmentCount(servantNumber, detainments);

        switch (servantNumber){
            case 1:
                this.servant1Detainments = detainments;
            case 2:
                this.servant2Detainments = detainments;
            case 3:
                this.servant3Detainments = detainments;
        }
    }


    /**
     * Error checking to ensure the servant number and the detainment count is valid.
     */
    private static void checkServantAndDetainmentCount(int servantNumber, int detainments){
        if (servantNumber > Constants.NUMBER_OF_SERVANTS || servantNumber < 0){
            throw new IllegalArgumentException(String.format(
                    "Servant %d does not exist. There are only %d servants.",
                    servantNumber,
                    Constants.NUMBER_OF_SERVANTS));
        }

        if (detainments > Constants.MAX_DETAINMENTS_PER_SERVANT || detainments < Constants.MIN_DETAINMENTS_PER_SERVANT){
            throw new IllegalArgumentException(String.format(
                    "There can only be %d-%d detainments per servant. Got %d",
                    Constants.MIN_DETAINMENTS_PER_SERVANT,
                    Constants.MAX_DETAINMENTS_PER_SERVANT,
                    detainments));
        }
    }

}