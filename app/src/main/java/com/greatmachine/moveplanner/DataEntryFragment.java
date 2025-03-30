package com.greatmachine.moveplanner;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

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
    private static final String FRAGMENT_NUMBER = "fragmentNumber";


    private int fragmentNumber;
    private ConstraintLayout layout;


    public DataEntryFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param fragmentNumber
     * @return A new instance of fragment DataEntryFragment.
     */
    public static DataEntryFragment newInstance(int fragmentNumber) {
        DataEntryFragment fragment = new DataEntryFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_NUMBER, fragmentNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.fragmentNumber = getArguments().getInt(FRAGMENT_NUMBER);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.layout = (ConstraintLayout) inflater.inflate(R.layout.fragment_data_entry, container, false);

        //Replace the placeholder data in the layout
        //setCardNumber();
        //setCardImage();

        return this.layout;
    }


    /**
     * The layout has default values for the card number. These defaults
     * need to be replaced at runtime with the actual data.
     */
    private void setCardNumber(){
        TextView cardNumberDisplay = layout.findViewById(R.id.card_number);
        String text = String.format(
                Locale.US,
                "Card %d/%d",
                0,
                0);
        //TODO: add correct numbers here

        cardNumberDisplay.setText(text);
    }


    /**
     * The layout has a default image in each data fragment. It needs
     * to be replaced with an actual card image on creation.
     */
    private void setCardImage(){
        Drawable cardPic = null; //TODO: get card image

        ImageView cardDisplay = layout.findViewById(R.id.card_image);
        cardDisplay.setImageDrawable(cardPic);
    }
}
