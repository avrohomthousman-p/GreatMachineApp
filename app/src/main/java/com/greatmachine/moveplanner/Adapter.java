package com.greatmachine.moveplanner;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Adapter extends FragmentStateAdapter {
    public Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return OverviewFragment.newInstance("only fragment", "lets see what happens");
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
