package com.greatmachine.moveplanner.utils;

/**
 * Stores all static data associated with a single fragment,
 * like the fragment number, and card type.
 */
public class FragmentData {
    public final CardType cardType;
    public final int fragmentNumber;
    public final int cardNumber;
    public final int cardImageResource;


    public FragmentData(CardType cardType, int fragmentNumber, int cardNumber, int cardImageResource) {
        this.cardType = cardType;
        this.fragmentNumber = fragmentNumber;
        this.cardNumber = cardNumber;
        this.cardImageResource = cardImageResource;
    }
}
