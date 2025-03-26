package com.greatmachine.moveplanner.activities;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.greatmachine.moveplanner.R;
import com.greatmachine.moveplanner.utils.Constants;

public class DataEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data_entry);

        ViewPager2 viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new Adapter(this));
        viewPager.setOffscreenPageLimit(Constants.NUMBER_OF_FRAGMENTS - 1);
        viewPager.setCurrentItem(Constants.OVERVIEW_FRAGMENT_NUMBER);
    }
}