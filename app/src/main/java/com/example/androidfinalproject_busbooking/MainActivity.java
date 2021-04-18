package com.example.androidfinalproject_busbooking;

import androidx.appcompat.app.AppCompatActivity;

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

    ImageView imgLogo;
    EditText etEmail, etPassword;
    Button btnSignIn, btnCreateAccount;
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

        imgLogo = findViewById(R.id.imgLogo);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        userList.add(new User("John", "john123@gmail.com","12345678@Qq","buzo",70.0));

        int imgLogoId = getResources().getIdentifier("buslogo5","mipmap",getPackageName());
        imgLogo.setImageResource(imgLogoId);

        btnSignIn.setOnClickListener(new ButtonEvents());
        btnCreateAccount.setOnClickListener(new ButtonEvents());
    }

    private class ButtonEvents implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnSignIn) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                boolean exist = false;

                for(User user:userList){
                    if(user.email.equalsIgnoreCase(email) && user.password.equalsIgnoreCase(password)){
                        exist = true;
                        currentUser = user;
                    }
                }

                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getBaseContext(), "Please enter value for required fields", Toast.LENGTH_SHORT).show();
                } else if (!Pattern.matches(emailReg,email)){
                    Toast.makeText(getBaseContext(), "Please enter valid email id", Toast.LENGTH_SHORT).show();
                } else if (!Pattern.matches(passReg,password)){
                    Toast.makeText(getBaseContext(), "Please enter at least 8 character that has at least 1 digit, 1 special character , 1 uppercase and 1 lowercase letter", Toast.LENGTH_SHORT).show();
                } else if(!exist){
                    Toast.makeText(getBaseContext(), "User does not exist", Toast.LENGTH_SHORT).show();
                } else{
                    Intent intent = new Intent(getBaseContext(), SearchBus.class);
                    startActivity(intent);
                }
            } else {
                Intent intent = new Intent(getBaseContext(), SignUp.class);
                startActivity(intent);
            }
        }
    }
}