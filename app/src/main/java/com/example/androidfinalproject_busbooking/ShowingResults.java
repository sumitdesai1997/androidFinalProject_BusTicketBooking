package com.example.androidfinalproject_busbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.Collections;
import java.util.Comparator;

public class ShowingResults extends AppCompatActivity {

    ListView lvBus;
    Button btnHighToLow, btnLowToHigh;
    public static BusAdapter busAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_results);

        lvBus = findViewById(R.id.lvBus);
        btnHighToLow = findViewById(R.id.btnLowToHigh);
        btnLowToHigh = findViewById(R.id.btnHighToLow);

        busAdapter = new BusAdapter(this, SearchBus.busList);
        lvBus.setAdapter(busAdapter);
        lvBus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchBus.currentBus = SearchBus.busList.get(position);

                Intent intent = new Intent(getBaseContext(), BusDetail.class);
                startActivity(intent);
            }
        });

        btnLowToHigh.setOnClickListener(new ButtonEvents());
        btnHighToLow.setOnClickListener(new ButtonEvents());
    }

    private class ButtonEvents implements  View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnHighToLow){
                Collections.sort(SearchBus.busList, new Comparator<Bus>() {
                    @Override
                    public int compare(Bus bus1, Bus bus2) {
                        return String.valueOf(bus2.price).compareTo(String.valueOf(bus1.price));
                    }
                });
                lvBus.setAdapter(busAdapter);
            }else{
                Collections.sort(SearchBus.busList, new Comparator<Bus>() {
                    @Override
                    public int compare(Bus bus1, Bus bus2) {
                        return String.valueOf(bus1.price).compareTo(String.valueOf(bus2.price));
                    }
                });
                lvBus.setAdapter(busAdapter);
            }
        }
    }
}