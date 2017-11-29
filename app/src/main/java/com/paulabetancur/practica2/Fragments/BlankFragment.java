package com.paulabetancur.practica2.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.paulabetancur.practica2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private RatingBar discoOneRatingBar;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_blank, container, false);
        discoOneRatingBar = (RatingBar) view.findViewById(R.id.rating_bar_0);
        discoOneRatingBar.setRating((float)3.5); // Gracias: Jajaja jaaj
        return view;
    }

}
