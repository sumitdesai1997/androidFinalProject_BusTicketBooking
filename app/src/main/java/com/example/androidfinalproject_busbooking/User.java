package com.example.androidfinalproject_busbooking;

// user class that contains the blueprint of user
public class User {
    String name;
    String email;
    String password;
    String question;
    double balance;

    // user constructor with no parameters
    public User() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.question = "";
        this.balance = 0.0;
    }

    // user constructor for the object that has the value as per parameters
    public User(String name, String email, String password, String question, double balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.question = question;
        this.balance = balance;
    }

}
