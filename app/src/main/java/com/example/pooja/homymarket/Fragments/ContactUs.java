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
public class ContactUs extends Fragment {

    View v;
   // ImageButton call,mail;
   // TextView abhijeet,shubahm,pooja,alok;



    public ContactUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_contact_us, container, false);

       // call = v.findViewById(R.id.call);
       // mail = v.findViewById(R.id.mail);
      //  abhijeet = v.findViewById(R.id.mail_abhi);
        //shubahm = v.findViewById(R.id.mail_shubham);
        //pooja = v.findViewById(R.id.mail_pooja);
        //alok = v.findViewById(R.id.mail_alok);
        /* abhijeet.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        shubahm.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        pooja.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        alok.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = "+919068474522";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);


            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","homymarketjpr@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Query");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        abhijeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","abhijeet.gpta12@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Query");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));



            }
        });



        shubahm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","shubham128166@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Query");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));

            }
        });



        pooja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","poojasharma120899@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Query");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));

            }
        });


        alok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","alokrc1997@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Query");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));

            }
        });
*/

        return v;
    }

}
