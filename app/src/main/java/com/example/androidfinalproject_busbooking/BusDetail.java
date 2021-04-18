package com.example.androidfinalproject_busbooking;

import androidx.appcompat.app.AppCompatActivity;

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

    TextView tvBusName, tvBusDetail, tvNumberOfSeats, tvFinalPrice;
    ImageView imgBus, imgLeft, imgRight;
    SeekBar sbNumberOfSeats;
    CheckBox cbFood, cbLiveTracking, cbNetflix, cbSleeper, cbAC, cbElectricalPlug;
    Button btnBookTicket,btnAddAmount1;

    public static Bus currentBus = new Bus("Great Canadain Travels",  false, false, false,  false, new String[]{"gc1", "gc2", "gc3"},"One of the oldest bus operator from the Canada. This bus will ease your travel from and to Ontario destination.", new String[]{"Mississauga", "Brampton", "Oshawa"}, new String[]{"Bharuch"}, 7.0,  0.95,  10.0);
    public static double finalPrice = 0.0;
    public static double service = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_detail);

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

        tvBusName.setText(currentBus.busName);

        int imgLeftId = getResources().getIdentifier("left","mipmap",getPackageName());
        imgLeft.setImageResource(imgLeftId);

        int imgRightId = getResources().getIdentifier("right","mipmap",getPackageName());
        imgRight.setImageResource(imgRightId);

        int imgBusId = getResources().getIdentifier(currentBus.images[0],"mipmap",getPackageName());
        imgBus.setImageResource(imgBusId);

        finalPrice = currentBus.price;

        tvBusDetail.setText(currentBus.description);
        tvFinalPrice.setText("$"+finalPrice);
        tvNumberOfSeats.setText("1");
        btnAddAmount1.setVisibility(View.GONE);

        sbNumberOfSeats.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               tvNumberOfSeats.setText(String.valueOf(progress));
               cbFood.setChecked(false);
               cbAC.setChecked(false);
               cbElectricalPlug.setChecked(false);
               cbLiveTracking.setChecked(false);
               cbNetflix.setChecked(false);
               cbSleeper.setChecked(false);
               service = 0;
               finalPrice = (currentBus.price * progress);
               tvFinalPrice.setText("$"+finalPrice);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        cbFood.setOnCheckedChangeListener(new CheckBoxEvents());
        cbAC.setOnCheckedChangeListener(new CheckBoxEvents());
        cbElectricalPlug.setOnCheckedChangeListener(new CheckBoxEvents());
        cbLiveTracking.setOnCheckedChangeListener(new CheckBoxEvents());
        cbSleeper.setOnCheckedChangeListener(new CheckBoxEvents());
        cbNetflix.setOnCheckedChangeListener(new CheckBoxEvents());

        btnBookTicket.setOnClickListener(new ButtonEvents());
        btnAddAmount1.setOnClickListener(new ButtonEvents());
        imgLeft.setOnClickListener(new ButtonEvents());
        imgRight.setOnClickListener(new ButtonEvents());
    }

    public class CheckBoxEvents implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            finalPrice -= service;
            service = 0.0;
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

            finalPrice += service;
            tvFinalPrice.setText("$"+finalPrice);
        }
    }

    public class ButtonEvents implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnBookTicket){
                if(Integer.parseInt(tvNumberOfSeats.getText().toString()) == 0){
                    Toast.makeText(getBaseContext(),"User has to choose at least 1 seat",Toast.LENGTH_SHORT).show();
                } else if(finalPrice > MainActivity.currentUser.balance){
                    Toast.makeText(getBaseContext(),"User doesn't have sufficient balance in the wallet",Toast.LENGTH_SHORT).show();
                    btnAddAmount1.setVisibility(View.VISIBLE);
                } else {
                    MainActivity.currentUser.balance -= finalPrice;
                    Intent intent = new Intent(getBaseContext(),BookingConfirmation.class);
                    startActivity(intent);
                }
            } else if(v.getId() == R.id.btnAddAmount1){
                MainActivity.redirectionFrom = "Booking Page";
                Intent intent = new Intent(getBaseContext(),Wallet.class);
                startActivity(intent);
            }
        }
    }

}