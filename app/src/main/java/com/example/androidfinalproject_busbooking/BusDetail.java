package com.example.androidfinalproject_busbooking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class BusDetail extends AppCompatActivity {

    // creating objects for the designing elements
    TextView tvBusName, tvBusDetail, tvNumberOfSeats, tvFinalPrice;
    ImageView imgBus, imgLeft, imgRight;
    SeekBar sbNumberOfSeats;
    CheckBox cbFood, cbLiveTracking, cbNetflix, cbSleeper, cbAC, cbElectricalPlug;
    Button btnBookTicket,btnAddAmount1;

    // creating global public static variable that can be accessible for all activities
    //public static Bus currentBus = new Bus("Great Canadain Travels",  false, false, false,  false, new String[]{"gc1", "gc2", "gc3"},"One of the oldest bus operator from the Canada. This bus will ease your travel from and to Ontario destination.", new String[]{"Mississauga", "Brampton", "Oshawa"}, new String[]{"Bharuch"}, 7.0,  0.95,  10.0);
    public static double finalPrice = 0.0;
    public static double service = 0.0;
    public static int noOfSeats = 1;
    public static String serviceDetails= "";
    public static int initial = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_detail);

        // assigning value to the objects by finding the view by id
        tvBusName = findViewById(R.id.tvBusName);
        tvBusDetail = findViewById(R.id.tvBusDetail);
        tvNumberOfSeats = findViewById(R.id.tvNumberOfSeats);
        tvFinalPrice = findViewById(R.id.tvFinalPrice);
        imgBus = findViewById(R.id.imgBus);
        imgLeft = findViewById(R.id.imgLeft);
        imgRight = findViewById(R.id.imgRight);
        sbNumberOfSeats = findViewById(R.id.sbNumberOfSeats);
        cbFood = findViewById(R.id.cbFood);
        cbLiveTracking = findViewById(R.id.cbLiveTracking);
        cbNetflix = findViewById(R.id.cbNetflix);
        cbSleeper = findViewById(R.id.cbSleeper);
        cbAC = findViewById(R.id.cbAC);
        cbElectricalPlug = findViewById(R.id.cbElectricalPlug);
        btnBookTicket = findViewById(R.id.btnBookTicket);
        btnAddAmount1 = findViewById(R.id.btnAddAmount1);

        tvBusName.setText(SearchBus.currentBus.busName);

        // setting image for the left arrow, right arrow and bus itself
        int imgLeftId = getResources().getIdentifier("left","mipmap",getPackageName());
        imgLeft.setImageResource(imgLeftId);

        int imgRightId = getResources().getIdentifier("right","mipmap",getPackageName());
        imgRight.setImageResource(imgRightId);

        int imgBusId = getResources().getIdentifier(SearchBus.currentBus.images[0],"mipmap",getPackageName());
        imgBus.setImageResource(imgBusId);

        finalPrice = SearchBus.currentBus.price;

        // assigning value to the text field
        tvBusDetail.setText(SearchBus.currentBus.description);
        tvFinalPrice.setText("$"+finalPrice);
        tvNumberOfSeats.setText("1");

        // hiding the button add amount on page load
        btnAddAmount1.setVisibility(View.GONE);

        // change listener event for number of seats seekbar
        sbNumberOfSeats.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               tvNumberOfSeats.setText(String.valueOf(progress));
               noOfSeats = progress;

               // making all checkboxes unchecked
               cbFood.setChecked(false);
               cbAC.setChecked(false);
               cbElectricalPlug.setChecked(false);
               cbLiveTracking.setChecked(false);
               cbNetflix.setChecked(false);
               cbSleeper.setChecked(false);

               // changing final price
               service = 0;
               finalPrice = (SearchBus.currentBus.price * progress);
               tvFinalPrice.setText("$"+String.format("%.2f",finalPrice));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // on checked change listener event for checkboxes
        cbFood.setOnCheckedChangeListener(new CheckBoxEvents());
        cbAC.setOnCheckedChangeListener(new CheckBoxEvents());
        cbElectricalPlug.setOnCheckedChangeListener(new CheckBoxEvents());
        cbLiveTracking.setOnCheckedChangeListener(new CheckBoxEvents());
        cbSleeper.setOnCheckedChangeListener(new CheckBoxEvents());
        cbNetflix.setOnCheckedChangeListener(new CheckBoxEvents());

        //  on click listener event for buttons, left arrow and right arrow
        btnBookTicket.setOnClickListener(new ButtonEvents());
        btnAddAmount1.setOnClickListener(new ButtonEvents());
        imgLeft.setOnClickListener(new ButtonEvents());
        imgRight.setOnClickListener(new ButtonEvents());
    }

    // class for checkbox event listener and implementing onCheckedChanged method
    public class CheckBoxEvents implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // subtracting the service value from the final price as now service will be calculated again
            finalPrice -= service;
            service = 0.0;

            // adding value to services as per user selection
            if(cbFood.isChecked()){
                service += (2.0 * Double.parseDouble(tvNumberOfSeats.getText().toString()));
            }
            if(cbLiveTracking.isChecked()){
                service += (0.50 * Double.parseDouble(tvNumberOfSeats.getText().toString()));
            }
            if(cbSleeper.isChecked()){
                service += (3.0 * Double.parseDouble(tvNumberOfSeats.getText().toString()));
            }
            if(cbNetflix.isChecked()){
                service += (1.0 * Double.parseDouble(tvNumberOfSeats.getText().toString()));
            }
            if(cbElectricalPlug.isChecked()){
                service += (0.25 * Double.parseDouble(tvNumberOfSeats.getText().toString()));
            }
            if(cbAC.isChecked()){
                service += (0.75 * Double.parseDouble(tvNumberOfSeats.getText().toString()));
            }

            // adding service value to final ticket price
            finalPrice += service;
            tvFinalPrice.setText("$"+String.format("%.2f",finalPrice));
        }
    }

    // class for button event listener and implementing onClick method
    public class ButtonEvents implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnBookTicket){
                // validations on the click of book ticket button
                if(Integer.parseInt(tvNumberOfSeats.getText().toString()) == 0){
                    alertBox("User has to choose at least 1 seat");
                    //Toast.makeText(getBaseContext(),"User has to choose at least 1 seat",Toast.LENGTH_SHORT).show();
                } else if(finalPrice > MainActivity.currentUser.balance){
                    alertBox("User doesn't have sufficient balance in the wallet");
                    //Toast.makeText(getBaseContext(),"User doesn't have sufficient balance in the wallet",Toast.LENGTH_SHORT).show();
                    btnAddAmount1.setVisibility(View.VISIBLE);
                } else {

                    // preparing string that holds the name of the services
                    if(cbFood.isChecked()){
                        serviceDetails += "Food,";
                    }
                    if(cbLiveTracking.isChecked()){
                        serviceDetails += " Live Tracking,";
                    }
                    if(cbNetflix.isChecked()){
                        serviceDetails += " Netflix,";
                    }
                    if(cbElectricalPlug.isChecked()){
                        serviceDetails += " Electric plug,";
                    }
                    if(cbAC.isChecked()){
                        serviceDetails += " AC,";
                    }
                    if(cbSleeper.isChecked()){
                        serviceDetails += " Sleeper";
                    }

                    if (serviceDetails.equals("")) {
                        serviceDetails = "No extra service choosen";
                    } else if (!serviceDetails.contains("Sleeper")){
                        serviceDetails = serviceDetails.substring(0, serviceDetails.length() - 1);
                    }

                    // redirecting to the booking confirmation screen
                    MainActivity.currentUser.balance -= finalPrice;
                    Intent intent = new Intent(getBaseContext(),BookingConfirmation.class);
                    startActivity(intent);
                }
            } else if(v.getId() == R.id.btnAddAmount1){
                // redirecting to the wallet screen
                MainActivity.redirectionFrom = "Booking Page";
                Intent intent = new Intent(getBaseContext(),Wallet.class);
                startActivity(intent);
            } else if(v.getId() == R.id.imgLeft){
                // changing bus image on the click of left arrow
                    if(initial != 0){
                        int imgBusId = getResources().getIdentifier(SearchBus.currentBus.images[initial-1],"mipmap",getPackageName());
                        imgBus.setImageResource(imgBusId);
                        initial -= 1;
                    }
            } else{
                // changing bus image on the click of right arrow
                if(initial != SearchBus.currentBus.images.length-1){
                    int imgBusId = getResources().getIdentifier(SearchBus.currentBus.images[initial+1],"mipmap",getPackageName());
                    imgBus.setImageResource(imgBusId);
                    initial += 1;
                }
            }
        }
    }

    // method that will display the alert dialog
    public void alertBox(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(BusDetail.this);
        builder.setTitle("Alert");
        builder.setMessage(message);

        builder.setCancelable(false);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

}