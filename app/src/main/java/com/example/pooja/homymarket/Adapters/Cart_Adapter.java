package com.example.pooja.homymarket.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pooja.homymarket.Models.CartFetch;
import com.example.pooja.homymarket.Models.DataFetch;
import com.example.pooja.homymarket.Fragments.Final_Product;
import com.example.pooja.homymarket.Fragments.Cart;
import com.example.pooja.homymarket.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.ProgrammingViewHolder> {

    private Context ctx;
    private List<DataFetch> data;
    private List<CartFetch> pricedata;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private String size;
    private float PriceFloat;

    // data required in view is recieved here
    public Cart_Adapter(Context ctx, List<DataFetch> data,List<CartFetch> pricedata)
    {

        this.ctx=ctx;
        this.data=data;
        this.pricedata=pricedata;

    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cart_resource,parent,false);       //view created
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProgrammingViewHolder holder, final int position) {

        holder.Title.setText(data.get(position).getName());
        Picasso.get()
                .load(data.get(position).getImage1())
                .placeholder(R.drawable.placeholder_picasso)
                .error(R.drawable.error_picasso)
                .fit()
                .into(holder.imageView);

       /* PriceFloat = Float.parseFloat(pricedata.get(position).getPrice());
        holder.Price.setText(String.valueOf(PriceFloat));*/



        String key="Product_"+data.get(position).getId1();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        mRef= FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("WishList").child(key);
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                size=dataSnapshot.child("Quantity").getValue().toString();
                holder.Qty.setText(size);
                PriceFloat=Float.parseFloat(dataSnapshot.child("Price").getValue().toString());
                holder.Price.setText(String.valueOf(PriceFloat));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                //This is a process to delete a node in firebase .................

                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user=mAuth.getCurrentUser();
                String key="Product_"+data.get(position).getId1();
                mRef= FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("WishList").child(key);
                mRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        //This is the process to call a fragment from any Adapter Class................

                        Cart ldf = new Cart();
                        FragmentManager fm = ((FragmentActivity)ctx).getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.frame,ldf).addToBackStack(null).commit();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });



            }
        });




        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Final_Product ldf = new Final_Product(data.get(position));
                FragmentManager fm = ((FragmentActivity)ctx).getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,ldf).addToBackStack(null).commit();
                data.clear();

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        ImageButton del;
        ImageView imageView;
        TextView Title, Qty,Price;
        public ProgrammingViewHolder(View itemView) {               //view sent to be kept in a viewholder

            super(itemView);


            del = itemView.findViewById(R.id.delete);
            imageView = itemView.findViewById(R.id.imageview);
            Title = itemView.findViewById(R.id.title);
            Qty = itemView.findViewById(R.id.qty);
            Price = itemView.findViewById(R.id.price);



        }
    }
}
