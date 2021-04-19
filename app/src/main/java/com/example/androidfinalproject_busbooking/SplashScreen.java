package com.example.androidfinalproject_busbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    // creating ImageView object
    ImageView splashLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // setting image for bus logo
        splashLogo = findViewById(R.id.splashLogo);
        int splashLogoId = getResources().getIdentifier("buslogo5","mipmap",getPackageName());
        splashLogo.setImageResource(splashLogoId);

        // handler object to handle the thread
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // redirecting ot the sign in screen after 3.5 seconds
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
            }
        },3500);
    }
}