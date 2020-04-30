package com.example.pooja.homymarket.Fragments;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pooja.homymarket.Adapters.AutoImageSliderAdapter;
import com.example.pooja.homymarket.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    private View v;
    private LinearLayout txt;
    private SliderView sliderView;
    LinearLayout all;
    private CardView stationary,chakhna,fruits,dairy;
    private String ID,AUTO1,AUTO2,AUTO3,AUTO4,AUTO5;
    EditText search;
    ImageButton search_button;



    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        v=inflater.inflate(R.layout.fragment_home_, container, false);


        FirebaseMessaging.getInstance().subscribeToTopic("abhijeet")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       Log.d("#######","done");
                    }
                });


        stationary = v.findViewById(R.id.stationary);
        chakhna    = v.findViewById(R.id.snacks);
        fruits     = v.findViewById(R.id.fruits);
        dairy      = v.findViewById(R.id.dairy);
        search     = v.findViewById(R.id.search);
        search_button = v.findViewById(R.id.search_button);


        RequestQueue rq = Volley.newRequestQueue(getActivity());
        String url = "https://homimarket.com/wp-content/Android/images.php?get=select*from IMAGES";
        StringRequest sr= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jo = new JSONObject(response);
                    JSONArray ja = jo.getJSONArray("result");
                    JSONObject jo1 = ja.getJSONObject(0);

                    ID=jo1.getString("ID");
                    AUTO1= jo1.getString("AUTO1");
                    AUTO2=jo1.getString("AUTO2");
                    AUTO3=jo1.getString("AUTO3");
                    AUTO4=jo1.getString("AUTO4");
                    AUTO5=jo1.getString("AUTO5");





                    //ImageSlider.....
                    sliderView=v.findViewById(R.id.imageSlider);
                    final AutoImageSliderAdapter sliderAdapterExample=new AutoImageSliderAdapter(getActivity(),AUTO1,AUTO2,AUTO3,
                            AUTO4,AUTO5);
                    sliderAdapterExample.setCount(5);
                    sliderView.setSliderAdapter(sliderAdapterExample);
                    sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                    sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
                    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
                    sliderView.setIndicatorSelectedColor(Color.WHITE);
                    sliderView.setIndicatorUnselectedColor(Color.GRAY);
                    sliderView.startAutoCycle();
                    sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
                        @Override
                        public void onIndicatorClicked(int position) {
                            sliderView.setCurrentPagePosition(position);
                        }
                    });
                    //ImageSlider Ends.....



                } catch (JSONException e) {
                    Toast.makeText(getActivity(),"Unable to Fetch Data",Toast.LENGTH_LONG).show();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {




            }
        });

        sr.setShouldCache(false);
        rq.add(sr);


        stationary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();//Actual was---------FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,new Stationary()).addToBackStack(null).commit();//by default this fragment is used


            }
        });

        chakhna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();//Actual was---------FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,new Snacks()).addToBackStack(null).commit();//by default this fragment is used


            }
        });

        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();//Actual was---------FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,new Fruits()).addToBackStack(null).commit();//by default this fragment is used


            }
        });

        dairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();//Actual was---------FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,new DairyProducts()).addToBackStack(null).commit();//by default this fragment is used


            }
        });
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Search ob = new Search();
                Bundle bundle = new Bundle();
                bundle.putString("product",search.getText().toString().trim());
                ob.setArguments(bundle);
                FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();//Actual was---------FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,ob).addToBackStack(null).commit();//by default this fragment is used


            }
        });

        return v;
    }



}
