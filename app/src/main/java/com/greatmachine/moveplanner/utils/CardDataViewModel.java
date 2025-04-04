package com.greatmachine.moveplanner.utils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class CardDataViewModel extends ViewModel {
    private final MutableLiveData<CardData[]> data = new MutableLiveData<>();

    public CardDataViewModel(CardType[] cardsInFragmentOrder){
        CardData[] emptyData = new CardData[cardsInFragmentOrder.length];
        for(int i = 0; i < emptyData.length; i++){
            emptyData[i] =  new CardData(cardsInFragmentOrder[i]);
        }

        this.data.setValue(emptyData);
    }


    public CardData getDataAtPosition(int position){
        return this.data.getValue()[position];
    }


    public void setDataAtPosition(int position, int servent1Detainments, int servent2Detainments, int servent3Detainments){
        CardData cardData = this.data.getValue()[position];
        cardData.servant1Detainments = servent1Detainments;
        cardData.servant2Detainments = servent2Detainments;
        cardData.servant3Detainments = servent3Detainments;
    }


    /**
     * WARNING! the return value of this function has no mathematical or statistical value.
     * This function only exists for the purpouse of testing the app. Once the model is setup
     * this function should be deleted.
     */
    public int getTotalDetainments(){
        int total = 0;
        CardData[] gatheredData = this.data.getValue();
        for (int i = 0; i < gatheredData.length; i++){
            total += gatheredData[i].servant1Detainments;
            total += gatheredData[i].servant2Detainments;
            total += gatheredData[i].servant3Detainments;
        }
        return total;
    }



    public CardData[] getAllData(){
        return this.data.getValue().clone();
    }


    public void setDetainmentsForServant(int position, int servantNumber, int detainments){
        CardData cardData = this.data.getValue()[position];
        cardData.setDetainmentsForServant(servantNumber, detainments);
    }



    public int getDetainmentsForServant(int position, int servantNumber){
        return this.data.getValue()[position].getDetainmentsForServant(servantNumber);
    }
}
