package com.greatmachine.moveplanner.utils;

import java.util.Locale;

public class Constants {
    public static final int NUMBER_OF_FRAGMENTS = 14;
    public static final int OVERVIEW_FRAGMENT_NUMBER = 6;


    public static int getFragmentNumberByCardType(CardType cardType){
        switch(cardType){
            case STAY_AND_MAKE_GUARD:
                return 0;
            case STAY_STILL:
                return 1;
            case MOVE_DOWN:
                return 2;
            case MOVE_LEFT:
                return 3;
            case MOVE_UP:
                return 4;
            case MOVE_RIGHT:
                return 5;

            // fragment 6 is the overview fragment

            case CENTRAL_SQUARE:
                return 7;
            case BIRD:
                return 8;
            case GOGGLES:
                return 9;
            case ROSE:
                return 10;
            case MAINTENANCE_1:
                return 11;
            case MAINTENANCE_2:
                return 12;
            case MAINTENANCE_3:
                return 13;
            default:
                throw new IllegalArgumentException(String.format(Locale.US, "%s is not a valid card type.", cardType));
        }
    }


    public static int getImageResourceByFragment(int fragmentNumber){
        switch (fragmentNumber){
            //TODO: actually implement this when I know what data to return
            default:
                return -1;
        }
    }


    public static CardType getCardTypeByFragment(int fragmentNumber){
        switch (fragmentNumber){
            case 0:
                return CardType.STAY_AND_MAKE_GUARD;
            case 1:
                return CardType.STAY_STILL;
            case 2:
                return CardType.MOVE_DOWN;
            case 3:
                return CardType.MOVE_LEFT;
            case 4:
                return CardType.MOVE_UP;
            case 5:
                return CardType.MOVE_RIGHT;
            case OVERVIEW_FRAGMENT_NUMBER:
                return null;
            case 7:
                return CardType.CENTRAL_SQUARE;
            case 8:
                return CardType.BIRD;
            case 9:
                return CardType.GOGGLES;
            case 10:
                return CardType.ROSE;
            case 11:
                return CardType.MAINTENANCE_1;
            case 12:
                return CardType.MAINTENANCE_2;
            case 13:
                return CardType.MAINTENANCE_3;
            default:
                throw new IllegalArgumentException(String.format(
                        Locale.US,
                        "Fragments are numbered 0 to %d. There is no fragment %d",
                        NUMBER_OF_FRAGMENTS,
                        fragmentNumber
                ));
        }
    }
}
