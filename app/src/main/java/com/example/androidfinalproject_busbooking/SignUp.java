package com.example.androidfinalproject_busbooking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    // creating objects for the designing elements
    ImageView imgLogo1;
    EditText etName, etEmail1, etPassword1, etConfirmPassword, etSecurityQuestion;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // assigning value to the objects by finding the view by id
        imgLogo1 = findViewById(R.id.imgLogo1);
        etName = findViewById(R.id.etName);
        etEmail1= findViewById(R.id.etEmail1);
        etPassword1 = findViewById(R.id.etPassword1);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etSecurityQuestion = findViewById(R.id.etSecurityQuestion);
        btnSignUp = findViewById(R.id.btnSignUp);

        // setting image for bus logo
        int imglogo1id = getResources().getIdentifier("buslogo5","mipmap",getPackageName());
        imgLogo1.setImageResource(imglogo1id);

        // on click listener for sign up button
        btnSignUp.setOnClickListener(this);
    }

    // method that will execute on the click of sign up button
    @Override
    public void onClick(View v) {
        String name = etName.getText().toString();
        String email = etEmail1.getText().toString();
        String password = etPassword1.getText().toString();
        String cPassword = etConfirmPassword.getText().toString();
        String sQuestion = etSecurityQuestion.getText().toString();

        // validations on the click of sing up button
        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || cPassword.isEmpty() || sQuestion.isEmpty()){
            alertBox("Please enter value for required fields");
            //Toast.makeText(getBaseContext(),"Please enter value for required fields", Toast.LENGTH_SHORT).show();
        } else if(!Pattern.matches(MainActivity.letterReg, name)){
            alertBox("Please enter valid name");
            //Toast.makeText(getBaseContext(),"Please enter valid name", Toast.LENGTH_SHORT).show();
        } else if(!Pattern.matches(MainActivity.emailReg,email)){
            alertBox("Please enter valid email");
            //Toast.makeText(getBaseContext(),"Please enter valid email", Toast.LENGTH_SHORT).show();
        } else if(!Pattern.matches(MainActivity.passReg, password)){
            alertBox("Please enter at least 8 character that has at least 1 digit, 1 special character , 1 uppercase and 1 lowercase letter");
            //Toast.makeText(getBaseContext(),"Please enter at least 8 character that has at least 1 digit, 1 special character , 1 uppercase and 1 lowercase letter", Toast.LENGTH_SHORT).show();
        } else if(!password.equals(cPassword)){
            alertBox("Password and Confirm Password should be the exact match");
            //Toast.makeText(getBaseContext(),"Password and Confirm Password should be the exact match", Toast.LENGTH_SHORT).show();
        } else if(!Pattern.matches(MainActivity.letterReg, sQuestion)){
            alertBox("Please enter valid dog name");
            //Toast.makeText(getBaseContext(),"Please enter valid dog name", Toast.LENGTH_SHORT).show();
        } else {
            // adding value of user to the user list for the user whose account is created
            MainActivity.userList.add(new User(name, email, password, sQuestion,70.0));

            // redirecting to sign in screen
            Intent intent = new Intent(getBaseContext(),MainActivity.class);
            startActivity(intent);
        }
    }

    // method that will display the alert dialog
    public void alertBox(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
        builder.setTitle("Alert");
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