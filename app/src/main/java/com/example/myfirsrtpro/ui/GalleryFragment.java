package com.example.myfirsrtpro.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfirsrtpro.R;
import com.example.myfirsrtpro.databinding.FragmentGalleryBinding;

import java.io.FileNotFoundException;

public class GalleryFragment extends Fragment implements View.OnClickListener {

    private static final int GALLERY_REQUEST = 1;
    private FragmentGalleryBinding binding;


    //attributes
    private ImageView imageProfileView;
    private Button buttonGallery;

    //for picture of camera
    private Bitmap picture;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //gets reference for the design components


        buttonGallery = root.findViewById(R.id.buttonGallery);
        buttonGallery.setOnClickListener(this);

        imageProfileView = root.findViewById(R.id.imageViewProfile);


        return root;
    }

    public void onClick(View view) {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, GALLERY_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == getActivity().RESULT_OK){
            Uri targetUri = data.getData();//the file location
            try{
                //Decode an input stream into bitmap
                picture = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(targetUri));
                imageProfileView.setImageBitmap(picture);
            }
            //catch - don't kill the application if the file didn't open --> print the error
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
