package com.greatmachine.moveplanner.utils;


import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.greatmachine.moveplanner.R;

/**
 * Utilities for getting any data that you might need.
 */
public class DataFetchingUtils {

    /**
     * Given an image view from the cards_in_deck layout, finds and returns
     * the card type enum associated with the card displayed in that image
     * view.
     */
    public static CardType getCardTypeFromImageView(ImageView imageView){
        int id = imageView.getId();
        if (id == R.id.stayAndMakeGuardCardImage){
            return CardType.STAY_AND_MAKE_GUARD;
        }
        else if(id == R.id.stayStillCardImage){
            return CardType.STAY_STILL;
        }
        else if(id == R.id.moveDownCardImage){
            return CardType.MOVE_DOWN;
        }
        else if(id == R.id.moveLeftCardImage){
            return CardType.MOVE_LEFT;
        }
        else if(id == R.id.moveUpCardImage){
            return CardType.MOVE_UP;
        }
        else if(id == R.id.moveRightCardImage){
            return CardType.MOVE_RIGHT;
        }
        else if(id == R.id.centralSquareCardImage){
            return CardType.CENTRAL_SQUARE;
        }
        else if(id == R.id.birdCardImage){
            return CardType.BIRD;
        }
        else if(id == R.id.gogglesCardImage){
            return CardType.GOGGLES;
        }
        else if(id == R.id.roseCardImage){
            return CardType.ROSE;
        }
        else if(id == R.id.maintenance1CardImage){
            return CardType.MAINTENANCE_1;
        }
        else if(id == R.id.maintenance2CardImage){
            return CardType.MAINTENANCE_2;
        }
        else if(id == R.id.maintenance3CardImage){
            return CardType.MAINTENANCE_3;
        }
        else{
            throw new IllegalArgumentException("Id is not for a card image view");
        }
    }


    /**
     * Given a card type, returns the resource ID of the image for that card.
     */
    public static int getImageIDForDrawable(CardType cardType){
        switch (cardType){
            case STAY_AND_MAKE_GUARD:
                return R.drawable.stay_and_make_guard;
            case STAY_STILL:
                return R.drawable.stay_still;
            case MOVE_DOWN:
                return R.drawable.move_down;
            case MOVE_LEFT:
                return R.drawable.move_left;
            case MOVE_UP:
                return R.drawable.move_up;
            case MOVE_RIGHT:
                return R.drawable.move_right;
            case CENTRAL_SQUARE:
                return R.drawable.central_square;
            case BIRD:
                return R.drawable.bird;
            case GOGGLES:
                return R.drawable.goggles;
            case ROSE:
                return R.drawable.rose;
            case MAINTENANCE_1:
            case MAINTENANCE_2:
            case MAINTENANCE_3:
                return R.drawable.maintenance;
            default:
                throw new IllegalArgumentException("Invalid card type");
        }
    }
}
