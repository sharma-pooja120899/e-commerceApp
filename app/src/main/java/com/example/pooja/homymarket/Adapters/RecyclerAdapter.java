package com.example.pooja.homymarket.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pooja.homymarket.Models.DataFetch;
import com.example.pooja.homymarket.Fragments.Final_Product;
import com.example.pooja.homymarket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ProgrammingViewHolder1>{


    Context ctx;
    ArrayList<DataFetch> data;



    public RecyclerAdapter(Context ctx,ArrayList<DataFetch> data)
    {

        this.ctx=ctx;
        this.data = data;

    }

    @NonNull
    @Override
    public ProgrammingViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycle_resource,parent,false);       //view created
        return new ProgrammingViewHolder1(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder1 holder, final int position) {



        holder.name1.setText(data.get(position).getName());
        holder.brand1.setText(data.get(position).getBrand());
        holder.price1.setText(data.get(position).getSellprice());
        holder.discount1.setText(data.get(position).getDiscount());

        Picasso.get()
                .load(data.get(position).getImage1())
                .placeholder(R.drawable.placeholder_picasso)
                .error(R.drawable.error_picasso)
                .fit()
                .into(holder.image1);



        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Final_Product ldf = new Final_Product(data.get(position));
                FragmentManager fm = ((FragmentActivity)ctx).getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,ldf).addToBackStack(null).commit();

                //addToBackStack(null)---- enables you to come back to the previous fragment

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ProgrammingViewHolder1 extends RecyclerView.ViewHolder{
        ImageView image1;
        TextView name1,brand1,price1,discount1;
        LinearLayout linearLayout;
        RatingBar ratingBar;
        public ProgrammingViewHolder1(View itemView) {               //view sent to be kept in a viewholder

            super(itemView);

            image1 = itemView.findViewById(R.id.image);
            name1 = itemView.findViewById(R.id.name);
            brand1 = itemView.findViewById(R.id.brand);
            price1 = itemView.findViewById(R.id.price);
            discount1 = itemView.findViewById(R.id.discount);
            linearLayout=itemView.findViewById(R.id.linear);
            ratingBar=itemView.findViewById(R.id.rating);
            ratingBar.setVisibility(View.GONE);



        }
    }
}
