package com.example.pooja.homymarket.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pooja.homymarket.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {


    EditText name,email,phone,pass,conPass,landmark;
    Button reg;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    DatabaseReference mRef;
    TextView login,area,pin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        //if user is already signed in it takes you to the home page
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null){
            Intent i=new Intent(Register.this,HomePage.class);
            startActivity(i);
            finish();
        }

        progressDialog = new ProgressDialog(this);


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phoneNo);
        pass = findViewById(R.id.pass);
        conPass = findViewById(R.id.conPass);
        login=findViewById(R.id.login);
        reg = findViewById(R.id.register);//button
        area = findViewById(R.id.area);
        pin = findViewById(R.id.pin);
        landmark = findViewById(R.id.landmark);





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Register.this,Login.class);
                startActivity(i);
                finish();

            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String t_name=name.getText().toString().trim();
                final String t_email=email.getText().toString().trim();
                final String t_phone=phone.getText().toString().trim();
                final String t_pass=pass.getText().toString().trim();
                final String t_conPass=conPass.getText().toString().trim();
                final String t_area=area.getText().toString().trim();
                final String t_pin=pin.getText().toString().trim();
                final String t_landmark=landmark.getText().toString().trim();

                if(t_name.equals(""))
                {
                    name.setError("Enter the name");
                    name.requestFocus();
                    return;
                }

                if(t_email.equals(""))
                {
                    email.setError("Enter the email");
                    email.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(t_email).matches())
                {
                    email.setError("Please Enter a Valid Email");
                    email.requestFocus();
                    return;

                }

                if(t_phone.equals(""))
                {
                    phone.setError("Enter phone no");
                    phone.requestFocus();
                    return;
                }

                if(t_phone.length()<10 || t_phone.length()>10)
                {
                    phone.setError("Enter valid phone no");
                    phone.requestFocus();
                    return;
                }


                if(t_pass.equals(""))
                {
                    pass.setError("Enter Password");
                    pass.requestFocus();
                    return;
                }
                if(t_pass.length()<7)
                {
                    pass.setError("min length is 7");
                    pass.requestFocus();
                    return;
                }

                if(t_conPass.equals(""))
                {
                    pass.setError("Enter Confirm Password");
                    pass.requestFocus();
                    return;
                }
                if(!t_conPass.equals(t_pass))
                {
                    pass.setError("Password Mismatch");
                    pass.requestFocus();
                    return;
                }


                progressDialog.setTitle("Registering the User");
                progressDialog.setMessage("Please wait while we create your account");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(t_email,t_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            sendEmailVerification();
                            sendDataToFirebase(t_name,t_email,t_phone,t_pass,t_area,t_pin,t_landmark);
                        }else{
                            progressDialog.hide();
                            Toast.makeText(Register.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });







    }

    private void sendEmailVerification(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    Toast.makeText(Register.this,"You can login after verification.Verification mail sent!",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Register.this, "Not a verified user", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendDataToFirebase(String tname,String tmail,String tphone,String tpass,String tarea,String tpin,String tlandmark){

        FirebaseUser user=mAuth.getCurrentUser();
        Map<String,String> map=new HashMap<>();
        map.put("Name",tname);
        map.put("Email",tmail);
        map.put("Phone",tphone);
        map.put("Password",tpass);
        map.put("Area",tarea);
        map.put("Pin",tpin);
        map.put("Landmark",tlandmark);
        mRef= FirebaseDatabase.getInstance().getReference().child(user.getUid());
        mRef.setValue(map);

        progressDialog.dismiss();
    }


}
