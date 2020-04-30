package com.example.pooja.homymarket.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pooja.homymarket.Adapters.MyOrdersAdapter;
import com.example.pooja.homymarket.Models.MyOrdersFetch;
import com.example.pooja.homymarket.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrders extends Fragment {

    private View v;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private RecyclerView recyclerView;
    private ArrayList<MyOrdersFetch> data = new ArrayList<>();
    private ProgressBar progressBar;
    private Animation animation;
    private ImageView no_order_pic;


    public MyOrders() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_my_orders, container, false);


        no_order_pic = v.findViewById(R.id.norder);
        progressBar=v.findViewById(R.id.progress);
        animation= AnimationUtils.loadAnimation(getActivity(),R.anim.rotate);
        progressBar.startAnimation(animation);


        mAuth= FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        String id = currentUser.getUid().trim();

        recyclerView=v.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        data.clear();
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        String url = "https://homimarket.com/wp-content/Android/MyOrders.php?get=select*from Orders where customerid LIKE \""+id+"\"";
        StringRequest sr= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jo = new JSONObject(response);
                    JSONArray ja = jo.getJSONArray("result");



                    if(ja.length()==0)
                    {
                        no_order_pic.setVisibility(View.VISIBLE);
                    }

                    for(int i =ja.length()-1;i>=0;i--)
                    {

                        JSONObject jo1 = ja.getJSONObject(i);

                        String ORDER_NO=jo1.getString("ORDER_NO");
                        String TITLE= jo1.getString("TITLE");
                        String PRICE=jo1.getString("PRICE");
                        String STATUS=jo1.getString("STATUS");
                        String ADDRESS=jo1.getString("ADDRESS");
                        String PHONE=jo1.getString("PHONE");
                        String DELIVARY_SLOT=jo1.getString("DELIVARY_SLOT");
                        String ORDER_TIME=jo1.getString("TIME");



                        //Instead of creating many arrayList we can create model class and then create list of model
                        //we can assign values to model class by creating constructor and sending values like shown below
                        MyOrdersFetch ob = new MyOrdersFetch(ORDER_NO,TITLE,PRICE,STATUS,ADDRESS,PHONE,DELIVARY_SLOT,ORDER_TIME);

                        data.add(ob);

                    }


                } catch (JSONException e) {
                    Toast.makeText(getActivity(),"Unable to Fetch Data",Toast.LENGTH_LONG).show();
                }

                progressBar.clearAnimation();
                progressBar.setVisibility(View.INVISIBLE);
                recyclerView.setAdapter(new MyOrdersAdapter(getActivity(),data));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                progressBar.clearAnimation();
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(),"Please check your internet connection...",Toast.LENGTH_LONG).show();

            }
        });

        sr.setShouldCache(false);
        rq.add(sr);

        return v;
    }

}
