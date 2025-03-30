package com.greatmachine.moveplanner.activities;
import static java.util.Arrays.copyOf;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.greatmachine.moveplanner.utils.CardType;
import java.util.Arrays;

public class Adapter extends FragmentStateAdapter {

    //stores the card type each fragment shows, and null for the overview fragment
    private final CardType[] fragments;



    public Adapter(@NonNull FragmentActivity fragmentActivity, CardType[] deckContents) {
        super(fragmentActivity);

        //The deck contents needs an extra fragment slot for the overview fragment
        this.fragments = new CardType[deckContents.length + 1];
        System.arraycopy(deckContents, 0, this.fragments, 1, deckContents.length);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return OverviewFragment.newInstance();
        }
        else{
            return DataEntryFragment.newInstance(this.fragments[position], position, this.fragments.length - 1);
        }
    }

    @Override
    public int getItemCount() {
        return this.fragments.length;
    }
}
