package com.example.myfirsrtpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class WelcomeActivity extends AppCompatActivity implements View.OnLongClickListener{

    //declaring all components
    //private static final int NOTIFICATION_REMINDER_NIGHT = 1;
    private static final String TAG = "FIREBASE";
    private EditText editTextPassword,editTextEmail;
    private Button buttonLogin;
    private TextView invalidEmailOrPass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

      /* //pending intent waiting for the right time
        Intent notifyIntent = new Intent(this,NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000 * 60 * 60 * 24, pendingIntent);

*/

        //returns a reference to the instance of the project Firebase
        mAuth = FirebaseAuth.getInstance();

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

        //String email= getIntent().getStringExtra("email");
    }

    //clears the email and password input on long click by user
    @Override
    public boolean onLongClick(View view) {
        editTextPassword.setText("");
        editTextEmail.setText("");
        return true;
    }

    public void login(View view){

        //Intent intent = new Intent(this,MainActivity.class);
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
            //startActivity(intent);
            //intent.putExtra("email",editTextEmail.getText().toString());
            login(editTextEmail.getText().toString(),editTextPassword.getText().toString());


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




    public void login(String email,String password){
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(WelcomeActivity.this, nav_menu1.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(WelcomeActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    public void goTosignUp(View view){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }




}
//todo delete this