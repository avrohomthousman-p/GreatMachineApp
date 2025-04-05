package com.greatmachine.moveplanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.greatmachine.moveplanner.utils.Constants;
import com.greatmachine.moveplanner.utils.DataFetchingUtils;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.greatmachine.moveplanner.R;
import com.greatmachine.moveplanner.utils.CardType;

import java.util.Locale;


/**
 * An activity that lets the user select which cards are in the deck.
 */
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
            startDataEntryActivity();
            return true;
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


    /**
     * Builds an array of all the cards left in the deck.
     */
    private CardType[] getDeckContents(){
        final int INDEX_OF_CARD_IMAGE = 0;

        int deckSize = this.outerLinearLayout.getChildCount();
        CardType[] cardsInDeck = new CardType[deckSize];

        for(int i = 0; i < deckSize; i++){
            LinearLayout inner = (LinearLayout) this.outerLinearLayout.getChildAt(i);
            ImageView cardImage = (ImageView) inner.getChildAt(INDEX_OF_CARD_IMAGE);
            CardType cardType = DataFetchingUtils.getCardTypeFromImageView(cardImage);
            cardsInDeck[i] = cardType;
        }

        return cardsInDeck;
    }


    private void startDataEntryActivity(){
        if (this.outerLinearLayout.getChildCount() < Constants.MIN_CARDS_IN_DECK){

            Snackbar.make(this.outerLinearLayout,
                    String.format(Locale.US,
                            "There must be at least %d cards in the deck", Constants.MIN_CARDS_IN_DECK),
                    Snackbar.LENGTH_SHORT).show();

        }
        else{
            Intent intent = new Intent(this, DetainmentCountActivity.class);
            intent.putExtra(DetainmentCountActivity.DECK_CONTENTS_KEY, this.getDeckContents());
            startActivity(intent);
        }
    }
}