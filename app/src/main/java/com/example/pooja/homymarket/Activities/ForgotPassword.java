package com.example.pooja.homymarket.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pooja.homymarket.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    EditText email;
    Button change_pass;
    FirebaseAuth Auth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.email_for_change_pass);
        change_pass = findViewById(R.id.change_passBtn);
        progressDialog=new ProgressDialog(this);
        Auth = FirebaseAuth.getInstance();



        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setTitle("Verifying Email");
                progressDialog.setMessage("Please wait ...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                    Auth.sendPasswordResetEmail(email.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(ForgotPassword.this,"Reset link sent to your mail",Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                            else
                            {
                                Toast.makeText(ForgotPassword.this,"No Records found",Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }


                        }
                    });

            }
        });

    }
}
