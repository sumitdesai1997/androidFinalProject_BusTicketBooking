package com.example.androidfinalproject_busbooking;

public class User {
    String name;
    String email;
    String password;
    String question;
    double balance;

    public User() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.question = "";
        this.balance = 0.0;
    }

    public User(String name, String email, String password, String question, double balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.question = question;
        this.balance = balance;
    }

}
