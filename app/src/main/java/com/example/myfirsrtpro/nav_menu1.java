package com.example.myfirsrtpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.myfirsrtpro.databinding.FragmentDailyBinding;
import com.example.myfirsrtpro.ui.WeeklyFragment;
import com.example.myfirsrtpro.ui.monthly.MonthlyFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirsrtpro.databinding.ActivityNavMenu1Binding;

public class nav_menu1 extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavMenu1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavMenu1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavMenu1.toolbar);
        binding.appBarNavMenu1.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //todo rearrange this
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                //,R.id.reminder_menu,R.id.nav_reminders,R.id.alarm_menu,R.id.nav_editAlarm
                R.id.nav_camera, R.id.nav_gallery,R.id.logOut_menu,R.id.camera_menu,R.id.nav_logout,R.id.gallery_menu,R.id.about_menu,R.id.nav_monthlyCal,R.id.nav_weeklyCal,R.id.nav_dailyCal,R.id.nav_eventsList)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_menu1);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //in order to move between fragments
        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //fragmentTransaction.add(R.id.nav_view,new MonthlyFragment());
        //fragmentTransaction.commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this, "Settings Menu Was Chosen", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_menu1);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}