package com.example.pooja.homymarket.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pooja.homymarket.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Final_amount extends Fragment {

    View v;

    public Final_amount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_fragment__final_amount, container, false);

        return v;
    }

}
