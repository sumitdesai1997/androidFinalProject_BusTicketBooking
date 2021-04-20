package com.example.androidfinalproject_busbooking;

// bus class that contains the blueprint of bus
public class Bus {
    String busName;
    boolean isExpress;
    boolean isSleeper;
    boolean hasAC;
    boolean hasWifi;
    String[] images;
    String description;
    String from[];
    String to[];
    double review;
    double multiplier;
    double price;

    // constructor for the bus class
    public Bus(String busName, boolean isExpress, boolean isSleeper, boolean hasAC, boolean hasWifi, String[] images, String description, String[] from, String[] to, double review, double multiplier, double price) {
        this.busName = busName;
        this.isExpress = isExpress;
        this.isSleeper = isSleeper;
        this.hasAC = hasAC;
        this.hasWifi = hasWifi;
        this.images = images;
        this.description = description;
        this.from = from;
        this.to = to;
        this.review = review;
        this.multiplier = multiplier;
        this.price = price;
    }

    public Bus(){
        this.busName = "";
        this.isExpress = false;
        this.isSleeper = false;
        this.hasAC = false;
        this.hasWifi = false;
        this.images = new String[]{};
        this.description = "";
        this.from = new String[]{};
        this.to = new String[]{};
        this.review = 0.0;
        this.multiplier = 0.0;
        this.price = 0.0;
    }
}
