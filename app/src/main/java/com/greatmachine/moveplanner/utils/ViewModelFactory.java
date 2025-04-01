package com.greatmachine.moveplanner.utils;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.greatmachine.moveplanner.activities.CardDataViewModel;

/**
 * Factory for instantiating ViewModelProviders with the deck contents
 */
public class ViewModelFactory implements ViewModelProvider.Factory {
    private final CardType[] cardsInFragmentOrder;


    public ViewModelFactory(CardType[] cardsInFragmentOrder) {
        this.cardsInFragmentOrder = cardsInFragmentOrder;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass){
        if (modelClass.isAssignableFrom(CardDataViewModel.class)) {
            return (T) new CardDataViewModel(cardsInFragmentOrder);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
