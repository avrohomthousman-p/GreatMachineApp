package com.greatmachine.moveplanner.activities;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

    //Used to find the actual number of detainments in the edit text
    //displaying the number of detainments for a servant
    private static final int INDEX_OF_DETAINMENT_COUNT = 0;


    private static final int MAX_DETAINMENTS = 9;
    private static final int MIN_DETAINMENTS = 0;


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
        setupClickListeners();

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


    /**
     * Sets click listeners for the plus and minus icons used to
     * modify the number of detainments.
     */
    private void setupClickListeners(){
        int[] plusBtns = new int[]{R.id.plus_servant_1, R.id.plus_servant_2, R.id.plus_servant_3};
        int[] minusBtns = new int[]{R.id.minus_servant_1, R.id.minus_servant_2, R.id.minus_servant_3};
        int[] textViews = new int[]{R.id.servant_1_detainments_count, R.id.servant_2_detainments_count, R.id.servant_3_detainments_count};

        ImageButton btn;
        for(int i = 0; i < plusBtns.length; i++){
            int textView = textViews[i];
            btn = layout.findViewById(plusBtns[i]);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    incrementDetainments(layout.findViewById(textView));
                }
            });
        }

        for(int i = 0; i < minusBtns.length; i++){
            int textView = textViews[i];
            btn = layout.findViewById(minusBtns[i]);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    decrementDetainments(layout.findViewById(textView));
                }
            });
        }
    }


    /**
     * Increments the number of detainments displayed in the target text view.
     */
    private void incrementDetainments(TextView target){
        String text = (String) target.getText();
        char embeddedNumber = text.charAt(INDEX_OF_DETAINMENT_COUNT);
        int detainments = Character.getNumericValue(embeddedNumber);

        if (detainments >= MAX_DETAINMENTS){
            return;
        }

        String updatedText = replaceDetainmentNumber(text, detainments + 1);
        target.setText(updatedText);
    }


    /**
     * Decrements the number of detainments displayed in the target text view.
     */
    private void decrementDetainments(TextView target){
        String text = (String) target.getText();
        char embeddedNumber = text.charAt(INDEX_OF_DETAINMENT_COUNT);
        int detainments = Character.getNumericValue(embeddedNumber);

        if (detainments <= MIN_DETAINMENTS){
            return;
        }

        String updatedText = replaceDetainmentNumber(text, detainments - 1);
        target.setText(updatedText);
    }


    /**
     * Replace the number of detainments in the original text with the specified new value.
     */
    private static String replaceDetainmentNumber(String originalText, int updatedValue){
        StringBuilder builder = new StringBuilder(originalText);
        char updatedValueAsChar = Character.forDigit(updatedValue, 10);
        builder.setCharAt(INDEX_OF_DETAINMENT_COUNT, updatedValueAsChar);
        return builder.toString();
    }
}
