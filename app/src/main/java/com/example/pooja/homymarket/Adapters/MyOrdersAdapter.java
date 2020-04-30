package com.example.pooja.homymarket.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pooja.homymarket.Models.MyOrdersFetch;
import com.example.pooja.homymarket.R;
import java.util.ArrayList;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ProgrammingViewHolder>{

    Context ctx;
    ArrayList<MyOrdersFetch> data;


    public MyOrdersAdapter(Context ctx, ArrayList<MyOrdersFetch> data)
    {

        this.ctx=ctx;
        this.data = data;

    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.myorders_resource,parent,false);       //view created
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {

        holder.title.setText(data.get(position).getTITLE());
        holder.orderno.setText(data.get(position).getORDER_NO());
        holder.price.setText(data.get(position).getPRICE());
        holder.status.setText(data.get(position).getSTATUS());
        holder.address.setText(data.get(position).getADDRESS());
        holder.phone.setText(data.get(position).getPHONE());
        holder.delivary_slot.setText(data.get(position).getDELIVARY_SLOT());
        holder.order_time.setText(data.get(position).getORDER_TIME());

        if(data.get(position).getSTATUS().equals("Delivered"))
            holder.status.setTextColor(Color.parseColor("#1B3AD7"));


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        TextView title,orderno,price,status,address,phone,delivary_slot,order_time;

        public ProgrammingViewHolder(View itemView) {               //view sent to be kept in a viewholder

            super(itemView);

            title = itemView.findViewById(R.id.title);
            orderno = itemView.findViewById(R.id.orderNo);
            price = itemView.findViewById(R.id.price);
            status = itemView.findViewById(R.id.status);
            address = itemView.findViewById(R.id.address);
            phone = itemView.findViewById(R.id.phone);
            delivary_slot = itemView.findViewById(R.id.delivary_slot);
            order_time = itemView.findViewById(R.id.orderTime);

        }
    }
}
