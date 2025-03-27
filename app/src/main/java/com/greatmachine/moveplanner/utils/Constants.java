package com.greatmachine.moveplanner.utils;

import com.greatmachine.moveplanner.R;

public class Constants {
    public static final int NUMBER_OF_FRAGMENTS = 14;
    public static final int OVERVIEW_FRAGMENT_NUMBER = 6;
    public static final FragmentData[] FRAGMENT_DATA;


    static {
        FRAGMENT_DATA = new FragmentData[14];
        FRAGMENT_DATA[0] = new FragmentData(CardType.STAY_AND_MAKE_GUARD, 0, 1, R.drawable.stay_and_make_guard);;
        FRAGMENT_DATA[1] = new FragmentData(CardType.STAY_STILL, 1, 2, R.drawable.stay_still);
        FRAGMENT_DATA[2] = new FragmentData(CardType.MOVE_DOWN, 2, 3, R.drawable.move_down);
        FRAGMENT_DATA[3] = new FragmentData(CardType.MOVE_LEFT, 3, 4, R.drawable.move_left);
        FRAGMENT_DATA[4] = new FragmentData(CardType.MOVE_UP, 4, 5, R.drawable.move_up);
        FRAGMENT_DATA[5] = new FragmentData(CardType.MOVE_RIGHT, 5, 6, R.drawable.move_right);
        FRAGMENT_DATA[6] = new FragmentData(null, 6, 0, 0);
        FRAGMENT_DATA[7] = new FragmentData(CardType.CENTRAL_SQUARE, 7, 7, R.drawable.central_square);
        FRAGMENT_DATA[8] = new FragmentData(CardType.BIRD, 8, 8, R.drawable.bird);
        FRAGMENT_DATA[9] = new FragmentData(CardType.GOGGLES, 9, 9, R.drawable.goggles);
        FRAGMENT_DATA[10] = new FragmentData(CardType.ROSE, 10, 10, R.drawable.rose);
        FRAGMENT_DATA[11] = new FragmentData(CardType.MAINTENANCE_1, 11, 11, R.drawable.maintenance);
        FRAGMENT_DATA[12] = new FragmentData(CardType.MAINTENANCE_2, 12, 12, R.drawable.maintenance);
        FRAGMENT_DATA[13] = new FragmentData(CardType.MAINTENANCE_3, 13, 13, R.drawable.maintenance);
    }
}
