package com.example.androidfinalproject_busbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class Wallet extends AppCompatActivity {

    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        imgBack = findViewById(R.id.imgBack);
        int imgBackId = getResources().getIdentifier("back","mipmap", getPackageName());
        imgBack.setImageResource(imgBackId);
    }
}