package com.example.androidfinalproject_busbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class SearchBus extends AppCompatActivity {

    TextView tvDate;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bus);

        tvDate = findViewById(R.id.tvDate);
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