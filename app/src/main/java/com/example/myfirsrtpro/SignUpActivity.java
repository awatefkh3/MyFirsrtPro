package com.example.myfirsrtpro;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextEmailSignUp,editTextName,editTextAge,editTextPasswordSignUp;
    private RadioButton FemaleRadioButton,MaleRadioButton;
    private Button buttonSubmit;
    private TextView pass_1,pass_2,pass_3,pass_4,validEmail;
    private boolean exist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmailSignUp = findViewById(R.id.editTextEmailSignUp);
        editTextPasswordSignUp = findViewById(R.id.editTextPasswordSignUp);
        editTextAge = findViewById(R.id.editTextAge);
        editTextName = findViewById(R.id.editTextName);
        FemaleRadioButton = findViewById(R.id.FemaleRadioButton);
        MaleRadioButton = findViewById(R.id.MaleRadioButton);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        pass_1 = findViewById(R.id.pass_1);
        pass_2 = findViewById(R.id.pass_2);
        pass_3 = findViewById(R.id.pass_3);
        pass_4 = findViewById(R.id.pass_4);
        validEmail = findViewById(R.id.validEmail);

    }

    public void submit(View view){
        Intent intent = new Intent(this,MainActivity.class);
        exist = true;
        if(!Patterns.EMAIL_ADDRESS.matcher(editTextEmailSignUp.getText().toString()).find()){
            validEmail.setText("Invalid Email");
            validEmail.setTextColor(Color.RED);
        }
        else{
            validEmail.setText("valid Email");
            validEmail.setTextColor(Color.GREEN);
        }
        if(editTextAge.getText().toString().equals("") || editTextName.getText().toString().equals("")){
            exist = false;
        }
        if(exist && !editTextEmailSignUp.getText().toString().equals("") && validatePassword1(editTextPasswordSignUp.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(editTextEmailSignUp.getText().toString()).find()){
            intent.putExtra("email",editTextEmailSignUp.getText().toString());
            startActivity(intent);
        }
    }

    public boolean validatePassword1(String str){
        Pattern upperCase = Pattern.compile("[A-Z]");
        Pattern lowerCase = Pattern.compile("[a-z]");
        Pattern number = Pattern.compile("[0-9]");
        if(!lowerCase.matcher(str).find()){
            pass_1.setTextColor(Color.RED);
            return false;
        }
        else
        {
            pass_1.setTextColor(Color.GREEN);
        }

        if(!upperCase.matcher(str).find()){
            pass_2.setTextColor(Color.RED);
            return false;
        }
        else
        {
            pass_2.setTextColor(Color.GREEN);
        }

        if(!number.matcher(str).find()){
            pass_3.setTextColor(Color.RED);
            return false ;
        }
        else
        {
            pass_3.setTextColor(Color.GREEN);
        }

        if(str.length()<8){
            pass_4.setTextColor(Color.RED);
            return false;
        }
        else
        {
            pass_4.setTextColor(Color.GREEN);

        }
        return true;
    }

}