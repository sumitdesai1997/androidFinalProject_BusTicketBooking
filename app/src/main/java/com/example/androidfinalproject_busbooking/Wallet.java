package com.example.androidfinalproject_busbooking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Pattern;

public class Wallet extends AppCompatActivity {

    ImageView imgBack;
    TextView tvBalance, tvCCSection, tvCCNumber, tvCCName, tvExpiry, tvCCExpiry, tvCVV, tvReqAmount;
    Button btnAddAmount, btnHomePage, btnBookingPage, btnAddToWallet;
    EditText etCCNumber, etCCName, etCVV, etReqAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        imgBack = findViewById(R.id.imgBack);
        tvBalance = findViewById(R.id.tvBalance);
        tvCCSection = findViewById(R.id.tvCCSection);
        tvCCNumber = findViewById(R.id.tvCCNumber);
        tvCCName = findViewById(R.id.tvCCName);
        tvExpiry = findViewById(R.id.tvExpiry);
        tvCCExpiry = findViewById(R.id.tvCCExpiry);
        tvCVV = findViewById(R.id.tvCVV);
        tvReqAmount = findViewById(R.id.tvReqAmount);
        btnAddAmount = findViewById(R.id.btnAddAmount);
        btnHomePage = findViewById(R.id.btnHomePage);
        btnBookingPage = findViewById(R.id.btnBookingPage);
        btnAddToWallet = findViewById(R.id.btnAddToWallet);
        etCCNumber = findViewById(R.id.etCCNumber);
        etCCName = findViewById(R.id.etCCName);
        etCVV = findViewById(R.id.etCVV);
        etReqAmount = findViewById(R.id.etReqAmount);

        btnHomePage.setVisibility(View.GONE);
        btnBookingPage.setVisibility(View.GONE);
        tvCCSection.setVisibility(View.GONE);
        tvCCNumber.setVisibility(View.GONE);
        etCCNumber.setVisibility(View.GONE);
        tvCCName.setVisibility(View.GONE);
        etCCName.setVisibility(View.GONE);
        tvCCExpiry.setVisibility(View.GONE);
        tvExpiry.setVisibility(View.GONE);
        tvCVV.setVisibility(View.GONE);
        etCVV.setVisibility(View.GONE);
        tvReqAmount.setVisibility(View.GONE);
        etReqAmount.setVisibility(View.GONE);
        btnAddToWallet.setVisibility(View.GONE);

        tvBalance.setText("$"+ MainActivity.currentUser.balance);

        btnAddAmount.setOnClickListener(new ButtonEvents());
        btnHomePage.setOnClickListener(new ButtonEvents());
        btnAddToWallet.setOnClickListener(new ButtonEvents());
        btnBookingPage.setOnClickListener(new ButtonEvents());
        imgBack.setOnClickListener(new ButtonEvents());

        int imgBackId = getResources().getIdentifier("back","mipmap", getPackageName());
        imgBack.setImageResource(imgBackId);

        // getting today's date and separating day, month and year and putting that value to array
        long millis=System.currentTimeMillis();
        java.sql.Date todaydate=new java.sql.Date(millis);
        String ArrtodayDate[] = todaydate.toString().split("-");

        tvCCExpiry.setText(SearchBus.getMonthLetter(Integer.parseInt(ArrtodayDate[1])-1).toString() + " "+ ArrtodayDate[2] + ", " + ArrtodayDate[0]);

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        tvCCExpiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpDialog = new DatePickerDialog(Wallet.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String monthLetter = SearchBus.getMonthLetter(month) ;
                        String stringDate = monthLetter + " " + dayOfMonth + ", " + year;
                        tvCCExpiry.setText(stringDate);
                    }
                },year,month,day
                );
                dpDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpDialog.show();
            }
        });
    }

    private class ButtonEvents implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnAddAmount){
                tvCCSection.setVisibility(View.VISIBLE);
                tvCCNumber.setVisibility(View.VISIBLE);
                etCCNumber.setVisibility(View.VISIBLE);
                tvCCName.setVisibility(View.VISIBLE);
                etCCName.setVisibility(View.VISIBLE);
                tvCCExpiry.setVisibility(View.VISIBLE);
                tvExpiry.setVisibility(View.VISIBLE);
                tvCVV.setVisibility(View.VISIBLE);
                etCVV.setVisibility(View.VISIBLE);
                tvReqAmount.setVisibility(View.VISIBLE);
                etReqAmount.setVisibility(View.VISIBLE);
                btnAddToWallet.setVisibility(View.VISIBLE);
            } else if(v.getId() == R.id.btnHomePage){
                Intent intent = new Intent(getBaseContext(), SearchBus.class);
                startActivity(intent);
            } else if(v.getId() == R.id.btnBookingPage){
                Intent intent = new Intent(getBaseContext(), BusDetail.class);
                startActivity(intent);
            } else if(v.getId() == R.id.btnAddToWallet){

                String CCNumber = etCCNumber.getText().toString();
                String CCName = etCCName.getText().toString();
                String CVV = etCVV.getText().toString();
                String ReqAmount = etReqAmount.getText().toString();

                String CCNumberReg = "[0-9]{16}";
                String CVVReg = "[0-9]{3}";

                if(CCNumber.isEmpty() || CCName.isEmpty() || CVV.isEmpty() || ReqAmount.isEmpty()){
                    alertBox("Please enter value for required fields","Alert");
                    //Toast.makeText(getBaseContext(), "Please enter value for required fields", Toast.LENGTH_SHORT).show();
                    return;
                } else if(!Pattern.matches(CCNumberReg, CCNumber)){
                    alertBox("Please enter valid 16 digit credit card numbe","Alert");
                    //Toast.makeText(getBaseContext(),"Please enter valid 16 digit credit card number",Toast.LENGTH_SHORT).show();
                    return;
                } else if(!Pattern.matches(MainActivity.letterReg, CCName)){
                    alertBox("Please enter valid name","Alert");
                    //Toast.makeText(getBaseContext(),"Please enter valid name",Toast.LENGTH_SHORT).show();
                    return;
                } else if(!Pattern.matches(CVVReg, CVV)){
                    alertBox("Please enter valid 3 digit CVV","Alert");
                    //Toast.makeText(getBaseContext(),"Please enter valid 3 digit CVV",Toast.LENGTH_SHORT).show();
                    return;
                } else if(!Pattern.matches(CVVReg, ReqAmount)){
                    alertBox("Maximum limit to add is $999","Alert");
                    //Toast.makeText(getBaseContext(),"Maximum limit to add is $999",Toast.LENGTH_SHORT).show();
                    return;
                }

                MainActivity.currentUser.balance += Double.parseDouble(ReqAmount);
                alertBox("Your account is credited with $"+ ReqAmount,"Information");
                Toast.makeText(getBaseContext(),"Your account is credited with $"+ ReqAmount,Toast.LENGTH_SHORT).show();
                tvBalance.setText("$"+MainActivity.currentUser.balance);

                tvCCSection.setVisibility(View.GONE);
                tvCCNumber.setVisibility(View.GONE);
                etCCNumber.setVisibility(View.GONE);
                tvCCName.setVisibility(View.GONE);
                etCCName.setVisibility(View.GONE);
                tvCCExpiry.setVisibility(View.GONE);
                tvExpiry.setVisibility(View.GONE);
                tvCVV.setVisibility(View.GONE);
                etCVV.setVisibility(View.GONE);
                tvReqAmount.setVisibility(View.GONE);
                etReqAmount.setVisibility(View.GONE);
                btnAddToWallet.setVisibility(View.GONE);
                if(MainActivity.redirectionFrom.equalsIgnoreCase("Home Page")){
                    btnHomePage.setVisibility(View.VISIBLE);
                } else if(MainActivity.redirectionFrom.equalsIgnoreCase("Booking Page")) {
                    btnBookingPage.setVisibility(View.VISIBLE);
                }
            } else{
                if(MainActivity.redirectionFrom.equalsIgnoreCase("Home Page")){
                    Intent intent = new Intent(getBaseContext(), SearchBus.class);
                    startActivity(intent);
                } else if(MainActivity.redirectionFrom.equalsIgnoreCase("Booking Page")) {
                    Intent intent = new Intent(getBaseContext(), BusDetail.class);
                    startActivity(intent);
                }
            }
        }
    }

    public void alertBox(String message, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(Wallet.this);
        builder.setTitle(title);
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