package com.example.androidfinalproject_busbooking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // creating objects for the designing elements
    ImageView imgLogo;
    EditText etEmail, etPassword;
    Button btnSignIn, btnCreateAccount;

    // creating global public static variable that can be accessible for all activities
    public static String emailReg = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
    public static String passReg = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
    public static String letterReg = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
    public static ArrayList<User> userList = new ArrayList<>();
    public static User currentUser = new User();
    public static String redirectionFrom = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assigning value to the objects by finding the view by id
        imgLogo = findViewById(R.id.imgLogo);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        // one default user added for the testing purpose
        userList.add(new User("John", "john123@gmail.com","12345678@Qq","buzo",70.0));

        // setting the bus logo
        int imgLogoId = getResources().getIdentifier("buslogo5","mipmap",getPackageName());
        imgLogo.setImageResource(imgLogoId);

        // on click listener event for Create account and Sign In button
        btnSignIn.setOnClickListener(new ButtonEvents());
        btnCreateAccount.setOnClickListener(new ButtonEvents());
    }

    // class for button event listener and implementing onClick method
    private class ButtonEvents implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnSignIn) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                boolean exist = false;

                // checking if user exist or not, if exist then assigning that user object to currentUser
                for(User user:userList){
                    if(user.email.equalsIgnoreCase(email) && user.password.equalsIgnoreCase(password)){
                        exist = true;
                        currentUser = user;
                    }
                }

                // validations on the click of Sign in button
                if(email.isEmpty() || password.isEmpty()){
                    alertBox("Please enter value for required fields");
                    //Toast.makeText(getBaseContext(), "Please enter value for required fields", Toast.LENGTH_SHORT).show();
                } else if (!Pattern.matches(emailReg,email)){
                    alertBox("Please enter valid email id");
                    //Toast.makeText(getBaseContext(), "Please enter valid email id", Toast.LENGTH_SHORT).show();
                } else if (!Pattern.matches(passReg,password)){
                    alertBox("Please enter at least 8 character that has at least 1 digit, 1 special character , 1 uppercase and 1 lowercase letter");
                    //Toast.makeText(getBaseContext(), "Please enter at least 8 character that has at least 1 digit, 1 special character , 1 uppercase and 1 lowercase letter", Toast.LENGTH_SHORT).show();
                } else if(!exist){
                    alertBox("Account does not exist");
                    //Toast.makeText(getBaseContext(), "User does not exist", Toast.LENGTH_SHORT).show();
                } else{
                    // redirecting to the search bus page
                    Intent intent = new Intent(getBaseContext(), SearchBus.class);
                    startActivity(intent);
                }
            } else {
                // redirecting ot the sign up page
                Intent intent = new Intent(getBaseContext(), SignUp.class);
                startActivity(intent);
            }
        }
    }

    // method that will display the alert dialog
    public void alertBox(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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