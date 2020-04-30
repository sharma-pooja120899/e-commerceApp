package com.example.pooja.homymarket.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pooja.homymarket.Models.CartFetch;
import com.example.pooja.homymarket.Models.DataFetch;
import com.example.pooja.homymarket.R;
import com.example.pooja.homymarket.Adapters.Cart_Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cart extends Fragment {

    View v;
    private RecyclerView recyclerView;
    DatabaseReference mRef;
    List<CartFetch> cart_data = new ArrayList<>();
    List<DataFetch> product_data = new ArrayList<>();
    private FirebaseAuth mAuth;
    CartFetch user ;
    private Animation animation;
    private ProgressBar progressBar;
    private ImageView imageCartEmpty;
    private Button Proceed;





    public Cart() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_cart, container, false);



        //animation starts......
        progressBar=v.findViewById(R.id.progress);
        animation= AnimationUtils.loadAnimation(getActivity(),R.anim.rotate);
        progressBar.startAnimation(animation);

        recyclerView=v.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Proceed = v.findViewById(R.id.proceed);
        Proceed.setVisibility(View.GONE);

        imageCartEmpty = v.findViewById(R.id.imageCartEmpty);
        imageCartEmpty.setVisibility(View.GONE);

        mAuth=FirebaseAuth.getInstance();
        FirebaseUser User = mAuth.getCurrentUser();
        mRef= FirebaseDatabase.getInstance().getReference().child(User.getUid()).child("WishList");

        cart_data.clear();
        product_data.clear();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //counting the no of item in cart_toolbar_icon.......
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    user = ds.getValue(CartFetch.class);

                    String id = user.getProduct_ID();
                    String qty = user.getQuantity();
                    String name = user.getName();
                    String price = user.getPrice();

                    CartFetch ob = new CartFetch(id,qty,name,price);
                    cart_data.add(ob);
                }

                if(cart_data.size()!=0)
                {
                    for (int j = 0; j< cart_data.size(); j++)
                    {


                        RequestQueue rq = Volley.newRequestQueue(getActivity());
                        String url = "https://homimarket.com/wp-content/Android/products.php?get=select*from PRODUCTS where ID LIKE "+ cart_data.get(j).getProduct_ID();
                        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jo = new JSONObject(response);
                                    JSONArray ja = jo.getJSONArray("result");
                                    JSONObject jo1 = ja.getJSONObject(0);

                                    String ID = jo1.getString("ID");
                                    String NAME = jo1.getString("NAME");
                                    String BRAND = jo1.getString("BRAND");
                                    String GENDER = jo1.getString("GENDER");
                                    String DISCOUNT = jo1.getString("DISCOUNT");
                                    String DESC = jo1.getString("DESCRIPTION");
                                    String SELL_PRICE = jo1.getString("SELLPRICE");
                                    String MARK_PRICE = jo1.getString("MARKPRICE");
                                    String RATING = jo1.getString("RATING");
                                    String TYPE = jo1.getString("TYPE");
                                    String SIZE = jo1.getString("SIZE");
                                    String CATEGORY = jo1.getString("CATEGORY");
                                    String LENGTH = jo1.getString("LENGTH");
                                    String IMAGE1 = jo1.getString("IMAGE1");
                                    String IMAGE2 = jo1.getString("IMAGE2");
                                    String IMAGE3 = jo1.getString("IMAGE3");
                                    String IMAGE4 = jo1.getString("IMAGE4");
                                    String IMAGE5 = jo1.getString("IMAGE5");
                                    String SHOP = jo1.getString("SHOP");
                                    String COLOR = jo1.getString("COLOR");
                                    String STOCK = jo1.getString("STOCK");
                                    String MATERIAL = jo1.getString("MATERIAL");



                                    DataFetch ob = new DataFetch(ID, NAME, BRAND, GENDER, DISCOUNT, DESC, SELL_PRICE, MARK_PRICE
                                            , RATING, TYPE, SIZE, CATEGORY, LENGTH, IMAGE1, IMAGE2, IMAGE3, IMAGE4, IMAGE5, SHOP, COLOR, STOCK, MATERIAL);

                                    product_data.add(ob);




                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                                recyclerView.setAdapter(new Cart_Adapter(getActivity(), product_data, cart_data));
                                progressBar.clearAnimation();
                                progressBar.setVisibility(View.INVISIBLE);
                                Proceed.setVisibility(View.VISIBLE);



                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {


                                progressBar.clearAnimation();
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getActivity(), "Please check your internet connection...Or the connection is slow", Toast.LENGTH_LONG).show();

                            }
                        });

                        sr.setShouldCache(false);
                        rq.add(sr);
                    }
                }

                else
                {
                    progressBar.clearAnimation();
                    progressBar.setVisibility(View.GONE);
                    imageCartEmpty.setVisibility(View.VISIBLE);
                    imageCartEmpty.setBackgroundResource(R.drawable.emptycart);
                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                progressBar.clearAnimation();
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(),"Oops! Something went wrong",Toast.LENGTH_LONG).show();

            }




        });

        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DeliveryAdress ldf = new DeliveryAdress(product_data, cart_data);
                FragmentManager fm = (getActivity()).getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,ldf).addToBackStack(null).commit();


            }
        });


        return v;
    }

}
