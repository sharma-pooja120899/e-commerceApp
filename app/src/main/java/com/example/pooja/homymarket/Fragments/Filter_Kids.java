package com.example.pooja.homymarket.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.pooja.homymarket.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Filter_Kids extends Fragment {

    CheckBox xs,s,m,l,xl;
    private Button done;
    String clause = "";
    Spinner spinner_cloath, spinner_size;
    ArrayAdapter<String> cloath_adapter,size_adapter;
    View v;
    String cloath_selected, size_selected;
    RelativeLayout relativeLayout;

    public Filter_Kids() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_filter_women, container, false);

        done = v.findViewById(R.id.done);

        final String CLOTH[] = {"SELECT CLOATH","JEANS","SHIRTS","T-SHIRTS"};
        final String SHIRT_SIZE[] = {"SELECT SIZE","XS","S","M","L","XL"};
        final String JEAN_SIZE[] = {"SELECT SIZE","HALF","3/4","FULL"};
        final String T_SHIRT_SIZE[]={"SELECT SIZE","XS","S","M","L","XL"};
        spinner_cloath = v.findViewById(R.id.cloath);
        spinner_size = v.findViewById(R.id.size);



        relativeLayout = v.findViewById(R.id.size1);
        relativeLayout.setVisibility(View.GONE);


        cloath_adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,CLOTH);
        spinner_cloath.setAdapter(cloath_adapter);


        spinner_cloath.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                cloath_selected = spinner_cloath.getItemAtPosition(i).toString().trim();
                if(!cloath_selected.equals("SELECT CLOATH"))
                {
                    clause = clause + "AND TYPE LIKE "+ "\""+cloath_selected+"\"" ;
                    relativeLayout.setVisibility(View.VISIBLE);
                    spinner_cloath.setSelection(i);


                    if(cloath_selected.equals("JEANS"))
                    {

                        size_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, JEAN_SIZE);
                        spinner_size.setAdapter(size_adapter);

                    }

                    if(cloath_selected.equals("SHIRTS"))
                    {

                        size_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, SHIRT_SIZE);
                        spinner_size.setAdapter(size_adapter);

                    }

                    if(cloath_selected.equals("T-SHIRTS"))
                    {

                        size_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, T_SHIRT_SIZE);
                        spinner_size.setAdapter(size_adapter);

                    }

                    spinner_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            size_selected = spinner_size.getItemAtPosition(i).toString().trim();

                            if(!size_selected.equals("SELECT SIZE"))
                            {
                                clause = clause + " AND SIZE LIKE \""+ size_selected+"\"";
                            }


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });




                }
                else
                {
                    clause = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                clause = "";
            }


        });



        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                /*
                if(xs.isChecked()) {
                    i=1;
                    clause = clause + "\"XS\"";
                }

                if(s.isChecked())
                {
                    if(i==1)
                        clause = clause + " OR SIZE LIKE \"S\"";

                    else
                    {i=1;
                        clause = clause + "\"S\"";}
                }

                if(m.isChecked())
                {
                    if(i==1)
                        clause = clause + " OR SIZE LIKE \"M\"";

                    else
                    { i=1;
                        clause = clause + "\"M\"";}
                }
                if(l.isChecked())
                {
                    if(i==1)
                        clause = clause + " OR SIZE LIKE \"L\"";

                    else
                    {i=1;
                        clause = clause + " \"L\"";}
                }

                if(xl.isChecked())
                {
                    if(i==1)
                        clause = clause + " OR SIZE LIKE \"XL\"";

                    else
                    {i=1;
                        clause = clause + " \"XL\"";}
                }

                if(i==0)
                    clause = "";
*/
                Log.d("@@@",clause);

                Kids_Toys ldf = new Kids_Toys(clause);
                FragmentManager fm = (getActivity()).getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,ldf).addToBackStack(null).commit();
                spinner_cloath.setSelection(0);
            }
        });




        return v;
    }

}
