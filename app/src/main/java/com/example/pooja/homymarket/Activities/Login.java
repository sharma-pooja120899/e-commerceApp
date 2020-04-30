package com.example.pooja.homymarket.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pooja.homymarket.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText mail,password;
    Button login;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    TextView register,frgtpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();
        mail=findViewById(R.id.loginMail);
        password=findViewById(R.id.loginpass);
        login=findViewById(R.id.loginBtn);
        progressDialog=new ProgressDialog(this);
        register =findViewById(R.id.register);
        frgtpass = findViewById(R.id.forgotpass);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Login.this,Register.class);
                startActivity(i);
                finish();

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmail=mail.getText().toString().trim();
                String tpass=password.getText().toString().trim();

                if(tmail.equals(""))
                {
                    mail.setError("Can't be empty");
                    mail.requestFocus();
                    return;
                }

                if(tpass.equals(""))
                {
                    password.setError("Can't be empty");
                    password.requestFocus();
                    return;
                }

                progressDialog.setTitle("Checking credentials");
                progressDialog.setMessage("Please wait ...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                mAuth.signInWithEmailAndPassword(tmail,tpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            if(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {
                                progressDialog.dismiss();
                                Intent i = new Intent(Login.this, HomePage.class);
                                startActivity(i);
                                finish();
                            }
                            else{
                                sendEmailVerification();
                                progressDialog.dismiss();
                            }
                        }

                        else{
                            Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
            }
        });


        frgtpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Login.this,ForgotPassword.class));

            }
        });




    }

    private void sendEmailVerification(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    Toast.makeText(Login.this,"You are not a verified user. Verification link sent to your mail!",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
