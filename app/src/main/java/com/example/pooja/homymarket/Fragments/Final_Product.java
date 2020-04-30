package com.example.pooja.homymarket.Fragments;


import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pooja.homymarket.Models.DataFetch;
import com.example.pooja.homymarket.R;
import com.example.pooja.homymarket.Adapters.ViewPagerAdapter_FinalProduct;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Final_Product extends Fragment {

    DatabaseReference mRef;
    private FirebaseAuth mAuth;
    private ViewPager viewPager;
    LinearLayout linearLayout;
    private Button add_to_cart, buy_now;
    Spinner spinner;
    TextView tvquantity;
    String[] quantity = {"Quantity","1","2","3","4","5","6","7","8","9","10",
                         "11","12","13","14","15","16","17","18","19","20",
                         "21","22","23","24","25","26","27","28","29","30",
                         "31","32","33","34","35","36","37","38","39","40",
                         "41","42","43","44","45","46","47","48","49","50"};
    ArrayAdapter<String> adp;
    RelativeLayout relativeLayout;
    private int dots_count;
    private ImageView[] dots;
    private TextView brand,name,markPrice,sellPrice,discount,length,color,gender,type,rating,material,desc;
    String Qty="";

    DataFetch ob;

    public Final_Product(DataFetch ob) {

        this.ob=ob;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_final__product, container, false);

        //retrieving cart_data from previous fragment...............
        String brand1 = ob.getBrand();
        String name1 = ob.getName();
        String mark_price1 = ob.getMarkprice();
        String sell_price1 = ob.getSellprice();
        String discount1 = ob.getDiscount();
        String length1 = ob.getLength();
        String color1 = ob.getColor();
        String gender1 = ob.getGender();
        String type1 = ob.getType();
        String rating1 = ob.getRating();
        String material1 = ob.getMaterial();
        String description1 = ob.getDesc();
        String size1 = ob.getSize();
        String img1 = ob.getImage1();
        String img2 = ob.getImage2();
        String img3 = ob.getImage3();
        String img4 = ob.getImage4();
        String img5 = ob.getImage5();
        String cat = ob.getCategory();

       /* quantity = size1.split(",");
        quantity = insert(quantity,"Select Quantity",0);*/



        mAuth=FirebaseAuth.getInstance();
        add_to_cart = v.findViewById(R.id.add_to_cart);
        buy_now = v.findViewById(R.id.buy_now);



        brand = v.findViewById(R.id.company_name);
        name = v.findViewById(R.id.product_name);
        markPrice = v.findViewById(R.id.markPrice);
        sellPrice = v.findViewById(R.id.sellPrice);
        discount = v.findViewById(R.id.discount);
        length = v.findViewById(R.id.length);
        color = v.findViewById(R.id.color);
        gender = v.findViewById(R.id.gender);
        type = v.findViewById(R.id.type);
        rating = v.findViewById(R.id.rating);
        material = v.findViewById(R.id.material);
        desc = v.findViewById(R.id.description);

        //setting values to final page elements
        brand.setText(brand1);
        name.setText(name1);
        markPrice.setText("₹"+mark_price1);
        sellPrice.setText("₹"+sell_price1);
        discount.setText(discount1+"%");
        length.setText(length1);
        color.setText(color1);
        gender.setText(gender1);
        type.setText(type1);
        rating.setText(rating1);
        material.setText(material1);
        desc.setText(description1);

        if(cat.equals("STAT") || cat.equals("CHAK") || cat.equals("FRT") || cat.equals("DAI"))
        {
            brand.setVisibility(View.GONE);

            v.findViewById(R.id.linear_color).setVisibility(View.GONE);
            v.findViewById(R.id.linear_len).setVisibility(View.GONE);
            v.findViewById(R.id.linear_mat).setVisibility(View.GONE);
            v.findViewById(R.id.linear_rate).setVisibility(View.GONE);
            v.findViewById(R.id.linear_type).setVisibility(View.GONE);
            v.findViewById(R.id.linear_gen).setVisibility(View.GONE);
            v.findViewById(R.id.size_chart).setVisibility(View.GONE);


            v.findViewById(R.id.view1).setVisibility(View.GONE);
            v.findViewById(R.id.view2).setVisibility(View.GONE);
            v.findViewById(R.id.view3).setVisibility(View.GONE);
            v.findViewById(R.id.view4).setVisibility(View.GONE);
            v.findViewById(R.id.view5).setVisibility(View.GONE);
            v.findViewById(R.id.view6).setVisibility(View.GONE);

        }

        if(discount1.equals("0"))
        {
            v.findViewById(R.id.markPrice).setVisibility(View.GONE);
            v.findViewById(R.id.disc_linear).setVisibility(View.GONE);
        }


        //make a cut in a textView
        strikeThroughText(markPrice);


        //spinner
       // tvquantity=v.findViewById(R.id.setQuantitytext);
        spinner=v.findViewById(R.id.size);
        adp=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,quantity);
        spinner.setAdapter(adp);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Qty = spinner.getItemAtPosition(i).toString().trim();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //tvquantity.setText(quantity[0]);
                //Qty = Integer.parseInt(quantity[0]);
            }
        });


        //HR3018344633       54325



        //viewPager........................................................................................

        viewPager=v.findViewById(R.id.viewitemangle);
        linearLayout=v.findViewById(R.id.lineardots);
        relativeLayout=v.findViewById(R.id.viewpagerlinear);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
                relativeLayout.setLayoutParams(params);
            }
        });

        ViewPagerAdapter_FinalProduct viewPagerAdapterdone=new ViewPagerAdapter_FinalProduct(getActivity(),img1,img2,img3,img4,img5);
        viewPager.setAdapter(viewPagerAdapterdone);
        dots_count=viewPagerAdapterdone.getCount();
        dots=new ImageView[dots_count];

        for(int i=0;i<dots_count;i++)
        {
            dots[i]=new ImageView(getActivity());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.inactive_dots));
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);
            linearLayout.addView(dots[i],params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.active_dots));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for(int k=0;k<dots_count;k++){
                    dots[k].setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.inactive_dots));
                }
                dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.active_dots));

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //viewPagerEnds..............................................................................................



        //add_to_cart................................................................................................

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Qty.equals("Quantity") || Qty.equals("Quantity"))
                {
                    Toast.makeText(getActivity(),"Select Quantity",Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseUser user=mAuth.getCurrentUser();
                Map<String,String> map=new HashMap<>();
                String key="Product_"+ob.getId1();

                map.put("Product_ID",ob.getId1());
                map.put("Quantity",Qty);
                map.put("Name",ob.getName());
                map.put("Price",String.valueOf(Integer.parseInt(ob.getSellprice()) * Integer.parseInt(Qty)) );

                mRef= FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("WishList").child(key);
                mRef.setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(getActivity(),"Added To Cart",Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getActivity(),"Oops! Something went wrong",Toast.LENGTH_LONG).show();

                    }
                });

            }
        });

        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Qty.equals("Quantity")|| Qty.equals("Quantity"))
                {
                    Toast.makeText(getActivity(),"Select Quantity",Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseUser user=mAuth.getCurrentUser();
                Map<String,String> map=new HashMap<>();
                String key="Product_"+ob.getId1();

                map.put("Product_ID",ob.getId1());
                map.put("Quantity",Qty);
                map.put("Name",ob.getName());
                map.put("Price",String.valueOf(Integer.parseInt(ob.getSellprice()) * Integer.parseInt(Qty)) );


                mRef= FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("WishList").child(key);
                mRef.setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Cart ldf = new Cart();
                        FragmentManager fm = (getActivity()).getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.frame,ldf).addToBackStack(null).commit();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getActivity(),"Oops! Something went wrong",Toast.LENGTH_LONG).show();

                    }
                });

            }
        });


        return v;
    }



    //function for textView Cut
    private void strikeThroughText(TextView textView)
    {
        textView.setPaintFlags(textView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
    }




}

