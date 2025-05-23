package com.greatmachine.moveplanner.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.greatmachine.moveplanner.R;
import com.greatmachine.moveplanner.utils.Adapter;
import com.greatmachine.moveplanner.utils.CardType;
import com.greatmachine.moveplanner.utils.ViewModelFactory;

public class DetainmentCountActivity extends AppCompatActivity {

    public ViewModelFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detainment_count);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pager), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        CardType[] deckContents = (CardType[]) getIntent().getSerializableExtra(DeckContentsActivity.DECK_CONTENTS_KEY);
        this.factory = new ViewModelFactory(deckContents);
        ViewPager2 viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new Adapter(this, deckContents));
    }
}