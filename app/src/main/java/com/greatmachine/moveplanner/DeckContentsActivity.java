package com.greatmachine.moveplanner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.Locale;

public class DeckContentsActivity extends AppCompatActivity {
    private LinearLayout outerLinearLayout;
    private TextView cardCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deck_contents);

        outerLinearLayout = this.findViewById(R.id.outerLinearLayout);
        cardCount = this.findViewById(R.id.card_count);

        this.setupToolbar();
        this.setupHandlersForRemovingCards();
    }


    private void setupToolbar(){
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    /**
     * Sets event handlers for all the delete buttons next to each card.
     */
    private void setupHandlersForRemovingCards(){
        final int INDEX_OF_DELETE_BTN = 1;

        for (int i = 0; i < outerLinearLayout.getChildCount(); i++){
            LinearLayout inner = (LinearLayout) outerLinearLayout.getChildAt(i);
            ImageView deleteBtn = (ImageView) inner.getChildAt(INDEX_OF_DELETE_BTN);
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    outerLinearLayout.removeView(inner);
                    refreshCardCount();
                }
            });
        }
    }


    /**
     * Checks the number of cards still in the deck and sets the card count display
     * to display the correct number.
     */
    private void refreshCardCount(){
        int numCards = outerLinearLayout.getChildCount();
        String updatedText = String.format(
                Locale.US,
                "There are %d card%s in the deck",
                numCards,
                (numCards == 1 ? "" : "s"));

        cardCount.setText(updatedText);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.deck_contents_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) { // Back button
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }
        else if (id == R.id.action_reset){
            resetDeckContents();
            return true;
        }
        else if (id == R.id.action_done){
            //TODO move to next activity
            return false;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Resets all the deck to contain all 13 cards.
     */
    private void resetDeckContents(){
        //Remove any remaining cards
        ScrollView deckContainer = findViewById(R.id.cards_display);
        deckContainer.removeAllViews();

        //Replace the view contents with a fresh copy
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.cards_in_deck, deckContainer);

        //Now that the XML was replaced with a fresh version, some setup needs to be redone
        outerLinearLayout = this.findViewById(R.id.outerLinearLayout);
        setupHandlersForRemovingCards();
        refreshCardCount();
    }
}