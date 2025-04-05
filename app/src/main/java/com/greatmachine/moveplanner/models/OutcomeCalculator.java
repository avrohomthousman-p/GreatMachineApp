package com.greatmachine.moveplanner.models;
import com.greatmachine.moveplanner.utils.CardData;
import com.greatmachine.moveplanner.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class OutcomeCalculator {
    private static final int ROUND_TO = 2;

    /**
    * Given a deck of cards and the results that each card would produce if drawn,
    * this function generates and returns a breakdown of all the possible results
    * and their likelihood.
    *
    * The data is formatted as an array of doubles with a length of 10, such that
    * array[i] = (percent chance that there are i detainments)
    */
    public static double[] CalculateProbibilityOfOutcome(CardData[] deck) {
        List<Integer> detainmentCounts = GetNumberOfDetainmentsPerPossibility(deck);

        //Map the number of detainments to the number of possibilities that result in
        //that number of detainments.
        HashMap<Integer, Integer> detainmentsToPossibilities = new HashMap<>();
        for (int quantity : detainmentCounts) {
            detainmentsToPossibilities.compute(quantity,
                    (key, value) -> (value == null ? 1 : value + 1));

        }

        //Now copy the results to an output array that has 0's where there are no possibilities
        // There can be 0 - TOTAL_MAX_DETAINMENTS detainments
        double[] output = new double[Constants.TOTAL_MAX_DETAINMENTS + 1];

        for(int i = 0; i < output.length; i++) {
            if (detainmentsToPossibilities.containsKey(i)) {
                output[i] = (detainmentsToPossibilities.get(i) / (double)detainmentCounts.size()) * 100;
                output[i] = round((output[i]));
            }
        }

        return output;
    }


    /**
     * Rounds a number to the number of decimal places defined by the global constant
     * ROUND_TO
     */
    private static double round(double number){
        int decimalsToKeep = (int) Math.pow(10, ROUND_TO);
        return ((double)Math.round(number * decimalsToKeep)) / decimalsToKeep;
    }


    /**
    * Given a deck of cards and the results that each card would produce if drawn,
    * this function generates and returns a list of the number of detainments
    * that would be caused for each possible set of cards drawn.
    *
    * E.g: the return of [0, 0, 1, 2] means there are a total of 4 possible ways
    * the cards can be drawn. Two of them result in 0 detainments, one results
    * in 1 detainment and one results in 2 detainments.
    *
    * No guarantee is made about the order the results are returned in, and the
    * results include only the number of detainments, but not the cards drawn
    * that led to that result.
    */
    public static List<Integer> GetNumberOfDetainmentsPerPossibility(CardData[] deck) {
        List<CardData[]> drawOptions = GetDrawPossibilities(deck);
        List<Integer> detainments = new ArrayList<>();

        for (CardData[] cardsDrawn: drawOptions) {
            detainments.add(getNumDetainmentsIfDrawn(cardsDrawn));
        }

        return detainments;
    }


    /**
     * Counts the total number of detainments that would happen if the specified 3 cards
     * are drawn in the order they are provided (servant1 then 2 then 3).
     */
    private static int getNumDetainmentsIfDrawn(CardData[] cardsDrawn){
        if (cardsDrawn.length != 3) {
            throw new IllegalArgumentException("Only 3 cards can be drawn");
        }

        int numDetainments = 0;
        for (int i = 0; i < cardsDrawn.length; i++) {
            CardData currentCard = cardsDrawn[i];
            numDetainments += currentCard.getDetainmentsForServant(i + 1);
        }

        return numDetainments;
    }



    /**
    * Given an array of cards that are still in the deck, generates all possible draws that
    * can happen.
    */
    public static List<CardData[]> GetDrawPossibilities(CardData[] deck)
    {
        if (deck.length < Constants.MIN_CARDS_IN_DECK) {
            throw new IllegalArgumentException(String.format(
                    "There cannot be fewer than %d cards in the deck. Did you forget to shuffle?",
                    Constants.MIN_CARDS_IN_DECK));
        }


        List<CardData[]> posibilities = new ArrayList<>();

        for (int i = 0; i < deck.length; i++) {
            for (int j = 0; j < deck.length; j++) {
                if (i == j) {
                    continue;
                }

                for (int k = 0; k < deck.length; k++) {
                    if (k == i || k == j) {
                        continue;
                    }

                    posibilities.add(new CardData[] { deck[i], deck[j], deck[k] });
                }
            }
        }


        return posibilities;
    }
}
