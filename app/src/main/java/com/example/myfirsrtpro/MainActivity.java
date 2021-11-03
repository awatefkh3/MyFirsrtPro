package com.example.myfirsrtpro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements  DialogInterface.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == dialogInterface.BUTTON_POSITIVE){
            super.onBackPressed();
            dialogInterface.cancel();
        }
        if(i == dialogInterface.BUTTON_POSITIVE){
            dialogInterface.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure ? this will log you out");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", this);
        builder.setNegativeButton("No",this);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    //inflates the design of the required menu on top of the activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.reminder_menu:
                break;
            case R.id.alarm_menu:
                break;
            case R.id.about_menu:
                //Toast.makeText(MainActivity.this,"About Us",Toast.LENGTH_LONG).show();//a toast is a message that appears on the screen and disappears
                startActivity(new Intent(this,AboutActivity.class));
                break;
            case R.id.camera_menu:
                startActivity(new Intent(this,ProfileActivity.class));
            case R.id.gallery_menu:
                startActivity(new Intent(this,ProfileActivity.class));
            case R.id.logOut_menu:
                //logOut();
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}