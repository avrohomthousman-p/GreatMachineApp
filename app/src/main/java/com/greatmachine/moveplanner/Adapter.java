package com.greatmachine.moveplanner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class Adapter extends FragmentStateAdapter {
    public static final int PAGE_COUNT = 14;

    public Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return OverviewFragment.newInstance("only fragment", "lets see what happens");
            default:
                return DataEntryFragment.newInstance(position);
        }
    }

    @Override
    public int getItemCount() {
        return PAGE_COUNT;
    }
}
