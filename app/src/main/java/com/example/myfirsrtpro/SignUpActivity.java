package com.example.myfirsrtpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements  DialogInterface.OnClickListener{

    private static final String TAG = "FIREBASE";
    private EditText editTextEmailSignUp,editTextName,editTextAge,editTextPasswordSignUp;
    private RadioButton FemaleRadioButton,MaleRadioButton;
    private Button buttonSubmit;
    private TextView pass_1,pass_2,pass_3,pass_4,validEmail;
    private boolean exist;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
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
            signUp(editTextEmailSignUp.getText().toString(),editTextPasswordSignUp.getText().toString());



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

    public void signUp(String email,String password){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //gets instance of authentication project in FB console
                            FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
                            //gets the root of the real time DataBase in the FB console
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://myfirsrtpro-default-rtdb.europe-west1.firebasedatabase.app/");
                            String UID = mFirebaseAuth.getUid();
                            //build a ref for user related data in real time DataBase using user id
                            //getReference returns root - the path is users / all (for me )
                            DatabaseReference myRef = database.getReference("users/" + UID);

                            String email1 =String.valueOf(editTextEmailSignUp.getText());
                            String password1 =String.valueOf(editTextPasswordSignUp.getText());
                            String name1 =String.valueOf(editTextName.getText());
                            int age1 = Integer.parseInt(String.valueOf(editTextAge.getText()));
                            boolean female1 = true;
                            if(FemaleRadioButton.isChecked()){
                                female1 = true;
                            }
                            else if(MaleRadioButton.isChecked()){
                                female1 = false;
                            }
                            User user1 = new User(email1,password1,name1,age1,female1);
                            String key = myRef.push().getKey();
                            myRef = database.getReference("users/" + UID + "/" + key);
                            user1.setKey(key);

                            myRef.setValue(user1); //push the object to the firebase data set

                            Intent i = new Intent(SignUpActivity.this, nav_menu.class);
                            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                });

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == dialogInterface.BUTTON_POSITIVE){
            super.onBackPressed();
            dialogInterface.cancel();
        }
        if(i == dialogInterface.BUTTON_NEGATIVE){
            dialogInterface.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure ? this will Discard the changes");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", this);
        builder.setNegativeButton("No",this);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}