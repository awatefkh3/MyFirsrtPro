package com.example.myfirsrtpro.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myfirsrtpro.R;
import com.example.myfirsrtpro.databinding.FragmentCameraBinding;


public class CameraFragment extends Fragment implements View.OnClickListener {

    private static final int CAMERA_REQUEST = 0;

    private Button buttonCamera;

    private Bitmap picture;

    private ImageView imageProfileView;

    private FragmentCameraBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCameraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        buttonCamera = root.findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(this);

        imageProfileView = root.findViewById(R.id.imageViewProfile);


        return root;
    }

    public void onClick(View view) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, CAMERA_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST){
            //RESULT_OK --> the camera managed to take a picture
            if(resultCode == getActivity().RESULT_OK){
                picture = (Bitmap) data.getExtras().get("data");
                //set image captured to be the new image
                imageProfileView.setImageBitmap(picture);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
