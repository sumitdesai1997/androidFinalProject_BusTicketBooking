package com.example.androidfinalproject_busbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    ImageView imgLogo1;
    EditText etName, etEmail1, etPassword1, etConfirmPassword, etSecurityQuestion;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        imgLogo1 = findViewById(R.id.imgLogo1);
        etName = findViewById(R.id.etName);
        etEmail1= findViewById(R.id.etEmail1);
        etPassword1 = findViewById(R.id.etPassword1);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etSecurityQuestion = findViewById(R.id.etSecurityQuestion);
        btnSignUp = findViewById(R.id.btnSignUp);

        int imglogo1id = getResources().getIdentifier("buslogo5","mipmap",getPackageName());
        imgLogo1.setImageResource(imglogo1id);

        btnSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String name = etName.getText().toString();
        String email = etEmail1.getText().toString();
        String password = etPassword1.getText().toString();
        String cPassword = etConfirmPassword.getText().toString();
        String sQuestion = etSecurityQuestion.getText().toString();

        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || cPassword.isEmpty() || sQuestion.isEmpty()){
            Toast.makeText(getBaseContext(),"Please enter value for required fields", Toast.LENGTH_SHORT).show();
        } else if(!Pattern.matches(MainActivity.letterReg, name)){
            Toast.makeText(getBaseContext(),"Please enter valid name", Toast.LENGTH_SHORT).show();
        } else if(!Pattern.matches(MainActivity.emailReg,email)){
            Toast.makeText(getBaseContext(),"Please enter valid email", Toast.LENGTH_SHORT).show();
        } else if(!Pattern.matches(MainActivity.passReg, password)){
            Toast.makeText(getBaseContext(),"Please enter at least 8 character that has at least 1 digit, 1 special character , 1 uppercase and 1 lowercase letter", Toast.LENGTH_SHORT).show();
        } else if(!password.equals(cPassword)){
            Toast.makeText(getBaseContext(),"Password and Confirm Password should be the exact match", Toast.LENGTH_SHORT).show();
        } else if(!Pattern.matches(MainActivity.letterReg, sQuestion)){
            Toast.makeText(getBaseContext(),"Please enter valid dog name", Toast.LENGTH_SHORT).show();
        } else {
            MainActivity.userList.add(new User(name, email, password, sQuestion,70.0));

            Intent intent = new Intent(getBaseContext(),MainActivity.class);
            startActivity(intent);
        }
    }
}