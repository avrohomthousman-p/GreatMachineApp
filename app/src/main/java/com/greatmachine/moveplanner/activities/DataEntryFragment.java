package com.greatmachine.moveplanner.activities;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.greatmachine.moveplanner.R;
import com.greatmachine.moveplanner.utils.CardType;
import com.greatmachine.moveplanner.utils.DataFetchingUtils;

import java.util.Locale;

/**
 * A {@link Fragment} subclass that lets the user enter data about
 * a card in the movement deck. Specifically the number of detainments
 * it would case when drawn by each servant.
 *
 * Use the {@link DataEntryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataEntryFragment extends Fragment {
    // Keys used for arguments passed to fragments
    private static final String CARD_TYPE_KEY = "cardType";
    private static  final String FRAGMENT_POSITION_KEY = "fragmentPosition";
    private static final String DECK_SIZE_KEY = "deckSize";


    private CardType cardType;
    private int cardNumber;
    private int deckSize;
    private ConstraintLayout layout;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cardType the card this fragment should display
     * @param fragmentPosition what number fragment this is,
     *             needed to display what number in the deck
     *             is being displayed.
     * @param deckSize the total number of cards in the deck
     */
    public static DataEntryFragment newInstance(CardType cardType, int fragmentPosition, int deckSize) {
        DataEntryFragment fragment = new DataEntryFragment();
        Bundle args = new Bundle();
        args.putInt(CARD_TYPE_KEY, cardType.ordinal());
        args.putInt(FRAGMENT_POSITION_KEY, fragmentPosition);
        args.putInt(DECK_SIZE_KEY, deckSize);
        fragment.setArguments(args);
        return fragment;
    }


    public DataEntryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int cardTypeOrdinal = getArguments().getInt(CARD_TYPE_KEY);
            this.cardType = CardType.values()[cardTypeOrdinal];
            this.cardNumber = getArguments().getInt(FRAGMENT_POSITION_KEY);
            this.deckSize = getArguments().getInt(DECK_SIZE_KEY);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.layout = (ConstraintLayout) inflater.inflate(R.layout.fragment_data_entry, container, false);

        //Replace the placeholder data in the layout
        setCardNumberDisplay();
        setCardImage();

        return this.layout;
    }


    /**
     * The layout has default values for the card number. These defaults
     * need to be replaced at runtime with the actual data.
     */
    private void setCardNumberDisplay(){
        TextView cardNumberDisplay = layout.findViewById(R.id.card_number);
        String text = String.format(
                Locale.US,
                "Card %d/%d",
                this.cardNumber,
                this.deckSize);

        cardNumberDisplay.setText(text);
    }


    /**
     * The layout has a default image in each data fragment. It needs
     * to be replaced with an actual card image on creation.
     */
    private void setCardImage(){
        int resource_id = DataFetchingUtils.getImageIDForDrawable(this.cardType);
        Drawable cardPic = ContextCompat.getDrawable(getActivity(), resource_id);

        ImageView cardDisplay = layout.findViewById(R.id.card_image);
        cardDisplay.setImageDrawable(cardPic);
    }
}
