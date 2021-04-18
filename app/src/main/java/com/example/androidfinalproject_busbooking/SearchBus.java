package com.example.androidfinalproject_busbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SearchBus extends AppCompatActivity {

    TextView tvWelcome,tvDate;
    ImageView imgWallet;
    Button btnSignOut, btnSearchBus;
    Spinner spFrom, spTo;

    String fromList[] = {"Toronto", "Mississauga", "Brampton", "Oshawa", "Hamilton", "Kitchner"};
    String toList[] = {"Mississauga", "Toronto", "Brampton", "Oshawa", "Hamilton", "Kitchner"};
    public static HashMap<String,String> priceList = new HashMap<>();
    public static String fromCity = "";
    public static String toCity = "";
    public static double ticketPrice = 0.0;
    public static ArrayList<Bus> busList = new ArrayList<>();

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bus);

        tvWelcome = findViewById(R.id.tvWelcome);
        tvDate = findViewById(R.id.tvDate);
        imgWallet = findViewById(R.id.imgWallet);
        btnSignOut = findViewById(R.id.btnSignOut);
        btnSearchBus = findViewById(R.id.btnSearchBus);
        spFrom = findViewById(R.id.spFrom);
        spTo = findViewById(R.id.spTo);

        fillData();

        tvWelcome.setText("Hi " + MainActivity.currentUser.name);

        int imgWalletid = getResources().getIdentifier("wallet","mipmap", getPackageName());
        imgWallet.setImageResource(imgWalletid);

        btnSignOut.setOnClickListener(new ButtonEvents());
        btnSearchBus.setOnClickListener(new ButtonEvents());
        imgWallet.setOnClickListener(new ButtonEvents());

        ArrayAdapter aa1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, fromList);
        spFrom.setAdapter(aa1);
        spFrom.setOnItemSelectedListener(new SpinnerEvents());

        ArrayAdapter aa2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, toList);
        spTo.setAdapter(aa2);
        spTo.setOnItemSelectedListener(new SpinnerEvents());

        // getting today's date and separating day, month and year and putting that value to array
        long millis=System.currentTimeMillis();
        java.sql.Date todaydate=new java.sql.Date(millis);
        String ArrtodayDate[] = todaydate.toString().split("-");

        tvDate.setText(getMonthLetter(Integer.parseInt(ArrtodayDate[1])-1).toString() + " "+ ArrtodayDate[2] + ", " + ArrtodayDate[0]);

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpDialog = new DatePickerDialog(SearchBus.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String monthLetter = getMonthLetter(month) ;
                        String stringDate = monthLetter + " " + dayOfMonth + ", " + year;
                        tvDate.setText(stringDate);
                    }
                },year,month,day
                );
                dpDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpDialog.show();
            }
        });
    }

    private class ButtonEvents implements  View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnSignOut){
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            } else if(v.getId() == R.id.btnSearchBus){
                if(fromCity.equals(toCity)){
                    Toast.makeText(getBaseContext(),"From city and To city can not be the same", Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    Character from0 = fromCity.charAt(0);
                    Character to0 = toCity.charAt(0);

                    for(Map.Entry<String, String> price: priceList.entrySet()){
                        Character price0 = price.getKey().charAt(0);
                        Character price1 = price.getKey().charAt(1);
                        if(from0.equals(price0) && to0.equals(price1)){
                            ticketPrice = Double.parseDouble(price.getValue());
                        }
                    }

                    Intent intent = new Intent(getBaseContext(), ShowingResults.class);
                    startActivity(intent);
                }
            } else{
                MainActivity.redirectionFrom = "Home Page";
                Intent intent = new Intent(getBaseContext(), Wallet.class);
                startActivity(intent);
            }
        }
    }

    private class SpinnerEvents implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(parent.getId() == R.id.spFrom){
                fromCity = fromList[position];
            }
            if(parent.getId() == R.id.spTo){
                toCity = toList[position];
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            if(parent.getId() == R.id.spFrom){
                fromCity = fromList[0];
            }
            if(parent.getId() == R.id.spTo){
                toCity = toList[0];
            }
        }
    }

    public static void fillData(){
         priceList.clear();
        priceList.put("TM1" , "9");
        priceList.put("TB1" , "14");
        priceList.put("TO1" , "19");
        priceList.put("TH1" , "22");
        priceList.put("TK1" , "35");
        priceList.put("MT1" , "9");
        priceList.put("MB1" , "7");
        priceList.put("MO1" , "26");
        priceList.put("MH1" , "15");
        priceList.put("MK1" , "29");
        priceList.put("BT1" , "14");
        priceList.put("BM1" , "7");
        priceList.put("BO1" , "29");
        priceList.put("BH1" , "22");
        priceList.put("BK1" , "28");
        priceList.put("OT1" , "19");
        priceList.put("OM1" , "26");
        priceList.put("OB1" , "29");
        priceList.put("OH1" , "41");
        priceList.put("OK1" , "50");
        priceList.put("HT1" , "22");
        priceList.put("HM1" , "15");
        priceList.put("HB1" , "22");
        priceList.put("HO1" , "41");
        priceList.put("HK1" , "21");
        priceList.put("KT1" , "35");
        priceList.put("KM1" , "29");
        priceList.put("KB1" , "28");
        priceList.put("KO1" , "50");
        priceList.put("KH1" , "21");

        busList.clear();
        busList.add(new Bus("McCoy Express", true, false, false, false, new String[]{"mc1", "mc2", "mc3"},"One of the top buses in Toronto.The bus provides smooth journey in enitre Ontario.", new String[]{"Toronto", "Mississauga", "Brampton"}, new String[]{"Surat"}, 9.0, 1.05, 0.0));
        busList.add(new Bus("Ontario Northland Express",  false, false, false,  true, new String[]{"on1", "on2", "on3"},"This bus operator provides buses with moderns service. This bus provides only express service along with Wifi, Netflix, Food and Live tracking.", new String[]{"Mississauga", "Brampton", "Hamilton"}, new String[]{"Kim"}, 7.9,  0.98,  0.0));
        busList.add(new Bus("Great Canadain Travels",  false, false, false,  false, new String[]{"gc1", "gc2", "gc3"},"One of the oldest bus operator from the Canada. This bus will ease your travel from and to Ontario destination.", new String[]{"Mississauga", "Brampton", "Oshawa"}, new String[]{"Bharuch"}, 7.0,  0.95,  0.0));
        busList.add(new Bus("Franklin Travels",  true,  true, true,  false, new String[]{"f1", "f2", "f3"},"One of the High rated bus operator buses in Canada. This  Express Bus provides facilities like AC, Netflix, Wifi and many more.", new String[]{"Toronto", "Oshawa", "Hamilton"}, new String[]{"Khambhat"}, 9.6,  1.25,  0.0));
        busList.add(new Bus("Coach Canada Travels",  true, true, true,  true, new String[]{"cc1", "cc2", "cc3"},"This bus operator is highly recommend for good services. This Express bus provide all the service that need during the travel from Wifi and AC to Netflix and Food.", new String[]{"Mississauga", "Kitchner", "Toronto"}, new String[]{"Valsad"}, 9.2,  1.20,  0.0));
        busList.add(new Bus("Pacific Western Travels",  true,  false, true, true, new String[]{"pw1", "pw2", "pw3"},"One of the highly reputed bus operator in Toronto since last 10 years. This bus provides many optional services from which user can select the service they need.", new String[]{"Kitchner", "Oshawa"}, new String[]{"Bharuch"}, 8.0,  0.90,  0.0));
    }

    public static String getMonthLetter(int month){
        if(month == 0){
            return "Jan";
        } else if(month == 1){
            return "Feb";
        } else if(month == 2){
            return "Mar";
        } else if(month == 3){
            return "Apr";
        } else if(month == 4){
            return "May";
        } else if(month == 5){
            return "Jun";
        } else if(month == 6){
            return "Jul";
        } else if(month == 7){
            return "Aug";
        } else if(month == 8){
            return "Sept";
        } else if(month == 9){
            return "Oct";
        } else if(month == 10){
            return "Nov";
        } else if(month == 11){
            return "Dec";
        }
        return "";
    }
}