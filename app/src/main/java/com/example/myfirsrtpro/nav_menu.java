package com.example.myfirsrtpro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirsrtpro.databinding.ActivityNavMenuBinding;
import com.example.myfirsrtpro.ui.MonthlyFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class nav_menu extends AppCompatActivity  {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavMenuBinding binding;
    private TextView userNameTV,userEmailTV;
    private ImageView userImage;

    private MenuItem nav_logout;

    private static final String TAG = "FIREBASE";

    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://myfirsrtpro-default-rtdb.europe-west1.firebasedatabase.app/");
    private ArrayList<User> userslist;
    String email = mFirebaseAuth.getCurrentUser().getEmail();
    String UID  = mFirebaseAuth.getUid();
    //build a ref for user related data in real time DataBase using user id
    DatabaseReference myRef = database.getReference("users/" + UID);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userEmailTV = findViewById(R.id.userEmailTV);
        userNameTV = findViewById(R.id.userNameTV);
        userImage = findViewById(R.id.userImage);
        nav_logout = findViewById(R.id.nav_logout);


        binding = ActivityNavMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavMenu.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_camera, R.id.nav_gallery,R.id.nav_logout,R.id.about_menu,R.id.nav_monthlyCal,R.id.nav_weeklyCal,R.id.nav_dailyCal,R.id.nav_eventsList,R.id.nav_propic)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View hView = navigationView.getHeaderView(0);
        TextView nav_user = (TextView) hView.findViewById(R.id.userEmailTV);
        nav_user.setText(email);

        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(menuItem -> {
            logout();
            Intent i = new Intent(this,WelcomeActivity.class);
            startActivity(i);
            return true;
        });



        userslist = new ArrayList<>();//each UID has only one user !

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.child("").getValue(User.class);
                    userslist.add(user);
                    loadImage(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void logout() {
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if (user != null){
            mFirebaseAuth.signOut();
            Toast.makeText(this, user.getEmail()+ " Sign out!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Sign Out failed!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadImage(User user){
        NavigationView navigationView = binding.navView;
        View hView = navigationView.getHeaderView(0);
        if(user.getImage()!=null){
            ImageView nav_img = (ImageView) hView.findViewById(R.id.userImage);
            Bitmap bitmap = StringToBitMap(user.getImage());
            nav_img.setImageBitmap(bitmap);
        }
        TextView nav_name = (TextView) hView.findViewById(R.id.userNameTV);
        nav_name.setText(user.getName());

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public Bitmap StringToBitMap(String image){
        try{
            byte [] encodeByte = Base64.decode(image, Base64.DEFAULT);
            InputStream inputStream = new ByteArrayInputStream(encodeByte);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            return bitmap;
        }
        catch (Exception e){
            e.getMessage();
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_menu, menu);
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
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}