package com.example.myfirsrtpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class loginActivity extends AppCompatActivity implements View.OnLongClickListener{

    //declaring all components
    private EditText editTextPassword,editTextEmail;
    private Button buttonLogin;
    private TextView invalidEmailOrPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //findViewById returns a reference to the object with the specified id
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        invalidEmailOrPass = findViewById(R.id.invalidEmailOrPass);

        //sets login button to react to a long click otherwise it won't,"this" stands for the view --> the button (he's waiting for a long click)
        buttonLogin.setOnLongClickListener(this);

        SharedPreferences sp = getSharedPreferences("settings",MODE_PRIVATE);
        String email = sp.getString("email","");
        String password = sp.getString("password","");
        if(!email.equals("") && !password.equals("")){
            editTextEmail.setText(email);
            editTextPassword.setText(password);
        }

    }

    //clears the email and password input on long click by user
    @Override
    public boolean onLongClick(View view) {
        editTextPassword.setText("");
        editTextEmail.setText("");
        return true;
    }


    public void login(View view){

        Intent intent = new Intent(this,MainActivity.class);
        //if the username is not null move to the next page


        if(!editTextEmail.getText().toString().equals("") && validatePassword(editTextPassword.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString()).find()){

            //saving email and password of user in local file for future use
            //create sp file
            SharedPreferences sp = getSharedPreferences("settings",MODE_PRIVATE);
            //open editor for editing
            SharedPreferences.Editor editor = sp.edit();
            //write the wanted settings
            editor.putString("email",editTextEmail.getText().toString());
            editor.putString("password",editTextPassword.getText().toString());
            //save and close file
            editor.commit();

            invalidEmailOrPass.setText("");
            intent.putExtra("email",editTextEmail.getText().toString());
            startActivity(intent);

        }
        else {
            invalidEmailOrPass.setTextColor(Color.RED);
            if(!validatePassword(editTextPassword.getText().toString())&& !Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString()).find()){
                invalidEmailOrPass.setText("Invalid password and Email");
            }
            else{
                if(!Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString()).find()){
                    invalidEmailOrPass.setText("invalid Email");
                }
                else{
                    invalidEmailOrPass.setText("invalid password");
                }
            }

        }



    }
    public boolean validatePassword(String str){
        Pattern upperCase = Pattern.compile("[A-Z]");
        Pattern lowerCase = Pattern.compile("[a-z]");
        Pattern number = Pattern.compile("[0-9]");
        if(!lowerCase.matcher(str).find()){
            return false;
        }


        if(!upperCase.matcher(str).find()){
            return false;
        }


        if(!number.matcher(str).find()){
            return false ;
        }


        if(str.length()<8){
            return false;
        }

        return true;
    }

}