package com.greatmachine.moveplanner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class Adapter extends FragmentStateAdapter {
    public static final int PAGE_COUNT = 13;

    public Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //TODO: return diff fragments depending on the position
        return OverviewFragment.newInstance("only fragment", "lets see what happens");
    }

    @Override
    public int getItemCount() {
        return PAGE_COUNT;
    }
}
