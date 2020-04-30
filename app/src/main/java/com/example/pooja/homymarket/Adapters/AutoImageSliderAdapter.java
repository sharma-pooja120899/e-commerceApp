package com.example.pooja.homymarket.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pooja.homymarket.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

public class AutoImageSliderAdapter extends SliderViewAdapter<AutoImageSliderAdapter.SliderAdapterVH> {

    private Context context;
    private int mCount;
    private String ID,AUTO1,AUTO2,AUTO3,AUTO4,AUTO5;


    public AutoImageSliderAdapter(Context context,String AUTO1,String AUTO2,String AUTO3,String AUTO4,String AUTO5) {
        this.context = context;
        this.AUTO1 = AUTO1;
        this.AUTO2 = AUTO2;
        this.AUTO3 = AUTO3;
        this.AUTO4 = AUTO4;
        this.AUTO5 = AUTO5;

    }

    public void setCount(int count) {
        this.mCount = count;
    }


    @Override
    public AutoImageSliderAdapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout, null);
        return new SliderAdapterVH(inflate);

    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {


        /*viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(context, NextActivity.class));
            }
        });*/



        switch (position) {
            case 0:
                Picasso.get()
                        .load(AUTO1)
                        .placeholder(R.drawable.placeholder_picasso)
                        .error(R.drawable.error_picasso)
                        .fit()
                        .into(viewHolder.imageViewBackground);






                break;

            case 1:
                Picasso.get()
                        .load(AUTO2)
                        .placeholder(R.drawable.placeholder_picasso)
                        .error(R.drawable.error_picasso)
                        .fit()
                        .into(viewHolder.imageViewBackground);

                break;

            case 2:
                Picasso.get()
                        .load(AUTO3)
                        .placeholder(R.drawable.placeholder_picasso)
                        .error(R.drawable.error_picasso)
                        .fit()
                        .into(viewHolder.imageViewBackground);

                break;

            case 3:
                Picasso.get()
                        .load(AUTO4)
                        .placeholder(R.drawable.placeholder_picasso)
                        .error(R.drawable.error_picasso)
                        .fit()
                        .into(viewHolder.imageViewBackground);

                break;


            case 4:
                Picasso.get()
                        .load(AUTO5)
                        .placeholder(R.drawable.placeholder_picasso)
                        .error(R.drawable.error_picasso)
                        .fit()
                        .into(viewHolder.imageViewBackground);

                break;
            default:

                Picasso.get().load("http://www.pixstudija.com/wp-content/uploads/2018/06/Screen-Shot-2018-03-05-at-19.56.05.png").fit().into(viewHolder.imageViewBackground);
                break;

        }


    }

    @Override
    public int getCount() {
        return mCount;
    }


    class SliderAdapterVH extends SliderViewAdapter.ViewHolder{

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView=itemView;

        }
    }
}


