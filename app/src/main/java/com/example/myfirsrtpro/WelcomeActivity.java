package com.example.myfirsrtpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        //String email= getIntent().getStringExtra("email");
    }


    public void goTosignUp(View view){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    public void goTologin(View view) {

        Intent intent2 = new Intent(this, LoginActivity.class);
        startActivity(intent2);
    }

}