package com.example.androidfinalproject_busbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class BookingConfirmation extends AppCompatActivity {

    ImageView imageView;
    TextView tvBookingNo, tvUsername,tvFromCity,tvToCity,tvDateOfTravel,tvBusName1,tvNumberOfSeats1,tvServices,tvTotPayment;
    Button btnHomepage,btnSignout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);

        imageView = findViewById(R.id.imageView);
        tvBookingNo = findViewById(R.id.tvBookingNo);
        tvUsername = findViewById(R.id.tvUsername);
        tvFromCity = findViewById(R.id.tvFromCity);
        tvToCity = findViewById(R.id.tvToCity);
        tvDateOfTravel = findViewById(R.id.tvDateOfTravel);
        tvBusName1 = findViewById(R.id.tvBusName1);
        tvNumberOfSeats1 = findViewById(R.id.tvNumberOfSeats1);
        tvServices = findViewById(R.id.tvServices);
        tvTotPayment = findViewById(R.id.tvTotPayment);
        btnHomepage = findViewById(R.id.btnHomepage);
        btnSignout = findViewById(R.id.btnSignout);

        int imageViewId = getResources().getIdentifier("confirm","mipmap",getPackageName());
        imageView.setImageResource(imageViewId);

        int bookingNo = get6digitNumber();
        tvBookingNo.setText(String.valueOf(bookingNo));
        tvUsername.setText(MainActivity.currentUser.name);
        tvFromCity.setText(SearchBus.fromCity);
        tvToCity.setText(SearchBus.toCity);
        tvDateOfTravel.setText(SearchBus.stringDate);
        tvBusName1.setText(BusDetail.currentBus.busName);
        tvNumberOfSeats1.setText(String.valueOf(BusDetail.noOfSeats));
        tvServices.setText(BusDetail.serviceDetails);
        tvTotPayment.setText(String.valueOf(BusDetail.finalPrice));

        btnHomepage.setOnClickListener(new ButtonEvents());
        btnSignout.setOnClickListener(new ButtonEvents());
    }

    public class ButtonEvents implements View.OnClickListener{

        @Override
        public void onClick(View v) {
           if(v.getId() == R.id.btnHomePage){
               Intent intent = new Intent(getBaseContext(),SearchBus.class);
               startActivity(intent);
           } else{
               Intent intent = new Intent(getBaseContext(),MainActivity.class);
               startActivity(intent);
           }
        }
    }

    public static int get6digitNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        return number;
    }


}