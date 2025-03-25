package com.greatmachine.moveplanner.utils;

public class Utilities {

    /**
     * This function converts a fragment number to a card number.
     *
     * Fragments are numbered 0 to n-1 because that is how the FragmentStateAdapter is
     * set up. Cards, however must be numbered 1-13 because that is how the user will
     * count them.
     *
     * So if there are 14 fragments, numbered 0-13, and fragment 6 is the overview
     * fragment with the rest being data fragments, then the following must be true:
     *
     * Fragment 0-5 match cards 1-6
     * Fragment 6 is the overview fragment and has no card number
     * fragment 7-13 match cards 7-13
     */
    public static int cardNumberFromFragmentNumber(int fragmentNumber){
        if (fragmentNumber < Constants.OVERVIEW_FRAGMENT_NUMBER){
            return fragmentNumber + 1;
        }
        else{
            return fragmentNumber;
        }
    }
}
