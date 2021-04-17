package com.example.androidfinalproject_busbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        btnAddAmount.setOnClickListener(new ButtonEvents());
        imgBack.setOnClickListener(new ButtonEvents());


        int imgBackId = getResources().getIdentifier("back","mipmap", getPackageName());
        imgBack.setImageResource(imgBackId);
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
                Intent intent = new Intent(getBaseContext(), SearchBus.class);
                startActivity(intent);
            } else if(v.getId() == R.id.btnAddToWallet){

                String CCNumber = etCCNumber.getText().toString();
                String CCName = etCCName.getText().toString();
                String CVV = etCVV.getText().toString();
                String ReqAmount = etReqAmount.getText().toString();

                String CCNumberReg = "^(d{16})$";
                String CVVReg = "^(d{3})$";

                if(CCNumber.isEmpty() || CCName.isEmpty() || CVV.isEmpty() || ReqAmount.isEmpty()){
                    Toast.makeText(getBaseContext(), "Please enter value for required fields", Toast.LENGTH_SHORT).show();
                    return;
                } /*else if(!Pattern.matches(CCNumberReg, CCNumber)){
                    Toast.makeText(getBaseContext(),"Plese enter valid 16 digit number",Toast.LENGTH_SHORT).show();
                    return;
                } else if(!Pattern.matches(MainActivity.letterReg, CCName)){
                    Toast.makeText(getBaseContext(),"Plese enter valid name",Toast.LENGTH_SHORT).show();
                    return;
                } else if(!Pattern.matches(CVVReg, CVV)){
                    Toast.makeText(getBaseContext(),"Plese enter valid 3 digit CVV",Toast.LENGTH_SHORT).show();
                    return;
                } else if(!Pattern.matches(CVVReg, ReqAmount)){
                    Toast.makeText(getBaseContext(),"Maximum limit to add is $999",Toast.LENGTH_SHORT).show();
                    return;
                }*/

                MainActivity.currentUser.balance += Double.parseDouble(ReqAmount);
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
                } else if(MainActivity.redirectionFrom.equalsIgnoreCase("Booking")) {
                    btnBookingPage.setVisibility(View.VISIBLE);
                }
            } else{
                Intent intent = new Intent(getBaseContext(), SearchBus.class);
                startActivity(intent);
            }
        }
    }
}