package com.example.pooja.homymarket.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pooja.homymarket.R;

public class SplashScreen extends AppCompatActivity {
    ImageView logo;
    TextView tagline;
    Animation setanim,translateanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        logo=findViewById(R.id.homylogo);
        tagline=findViewById(R.id.tagline);

        setanim= AnimationUtils.loadAnimation(this,R.anim.set);
        translateanim=AnimationUtils.loadAnimation(this,R.anim.translate);
        logo.startAnimation(setanim);
        tagline.startAnimation(translateanim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashScreen.this, Register.class);
                startActivity(i);
                finish();

            }
        },4500);






    }

    @Override
    public void onBackPressed() {

                Toast.makeText(this,"Press back to exit",Toast.LENGTH_SHORT).show();

    }
}
