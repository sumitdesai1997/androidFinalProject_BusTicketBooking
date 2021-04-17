package com.example.androidfinalproject_busbooking;

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
}
