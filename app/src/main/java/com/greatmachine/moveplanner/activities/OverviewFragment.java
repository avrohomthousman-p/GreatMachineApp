package com.greatmachine.moveplanner.activities;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.greatmachine.moveplanner.R;
import com.greatmachine.moveplanner.models.OutcomeCalculator;
import com.greatmachine.moveplanner.utils.CardData;
import com.greatmachine.moveplanner.utils.CardDataViewModel;
import com.greatmachine.moveplanner.utils.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass used to display a summary of
 * the data entered in the data entry fragments.
 *
 * Use the {@link OverviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OverviewFragment extends Fragment {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static OverviewFragment newInstance() {
        return new OverviewFragment();
    }


    private ConstraintLayout layout;


    public OverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.layout = (ConstraintLayout) inflater.inflate(R.layout.fragment_overview, container, false);

        setFabClickListener();

        return this.layout;
    }


    private void setFabClickListener(){
        ViewModelFactory factory = ((DetainmentCountActivity)getActivity()).factory;
        CardDataViewModel viewModel = new ViewModelProvider(requireActivity(), factory).get(CardDataViewModel.class);

        ExtendedFloatingActionButton fab = this.layout.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardData[] deck = viewModel.getAllData();
                double[] results = OutcomeCalculator.CalculateProbibilityOfOutcome(deck);

                TextView outputSection = layout.findViewById(R.id.output_section);
                outputSection.setText(buildNiceOutputString(results, deck.length));
            }
        });
    }


    /**
     * Converts the statistical data into a string to be displayed on the screen.
     */
    private String buildNiceOutputString(double[] data, int deckSize){
        StringBuilder builder = new StringBuilder("There are " + deckSize + " cards in the deck\n");

        for(int i = 0; i < data.length; i++){
            builder.append("There is a " + data[i]);
            builder.append("% chance that there will be " + i);
            builder.append(" detainments\n");
        }

        return builder.toString();
    }
}
