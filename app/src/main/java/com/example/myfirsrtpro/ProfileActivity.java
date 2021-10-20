package com.example.myfirsrtpro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    //request for camera for activity result
    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_REQUEST = 1;

    //attributes
    private ImageView imageProfileView;
    private Button buttonCamera,buttonGallery;

    //for picture of camera
    private Bitmap picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //gets reference for the design components
        buttonCamera = findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(this);

        buttonGallery = findViewById(R.id.buttonGallery);
        buttonGallery.setOnClickListener(this);

        imageProfileView = findViewById(R.id.imageViewProfile);
    }

    @Override
    //will be called for any button , in the code the method decides what to do according to which button
    public void onClick(View view) {
        if(view.getId() == R.id.buttonCamera){
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, CAMERA_REQUEST);
        }else{
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, GALLERY_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST){
            //RESULT_OK --> the camera managed to take a picture
            if(resultCode == RESULT_OK){
                picture = (Bitmap) data.getExtras().get("data");
                //set image captured to be the new image
                imageProfileView.setImageBitmap(picture);
            }
        }
        else
        {
            if(resultCode == RESULT_OK){
                Uri targetUri = data.getData();//the file location
                try{
                    //Decode an input stream into bitmap
                    picture = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    imageProfileView.setImageBitmap(picture);
                }
                //catch - don't kill the application if the file didn't open --> print the error
                catch(FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
    }
}