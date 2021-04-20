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

    // creating objects for the designing elements
    ImageView imageView;
    TextView tvBookingNo, tvUsername,tvFromCity,tvToCity,tvDateOfTravel,tvBusName1,tvNumberOfSeats1,tvServices,tvTotPayment;
    Button btnHomepage,btnSignout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);

        // assigning value to the objects by finding the view by id
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

        // setting the confirmed tick image
        int imageViewId = getResources().getIdentifier("confirm","mipmap",getPackageName());
        imageView.setImageResource(imageViewId);

        // generating 6 digit random number for booking number
        int bookingNo = get6digitNumber();

        // assigning value to the text field for the user and ticket related information
        tvBookingNo.setText(String.valueOf(bookingNo));
        tvUsername.setText(MainActivity.currentUser.name);
        tvFromCity.setText(SearchBus.fromCity);
        tvToCity.setText(SearchBus.toCity);
        tvDateOfTravel.setText(SearchBus.stringDate);
        tvBusName1.setText(SearchBus.currentBus.busName);
        tvNumberOfSeats1.setText(String.valueOf(BusDetail.noOfSeats));
        tvServices.setText(BusDetail.serviceDetails);
        tvTotPayment.setText(String.valueOf(BusDetail.finalPrice));

        // on click listener event for Home page and Sign out button
        btnHomepage.setOnClickListener(new ButtonEvents());
        btnSignout.setOnClickListener(new ButtonEvents());
    }

    // class for button event listener and implementing onClick method
    public class ButtonEvents implements View.OnClickListener{

        @Override
        public void onClick(View v) {
           if(v.getId() == R.id.btnHomepage){
               // redirecting to the search bus page
               Intent intent = new Intent(getBaseContext(),SearchBus.class);
               startActivity(intent);
           } else{
               // redirecting to the sign in page
               Intent intent = new Intent(getBaseContext(),MainActivity.class);
               startActivity(intent);
           }
        }
    }

    // method to generate 6 digit number
    public static int get6digitNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        return number;
    }


}