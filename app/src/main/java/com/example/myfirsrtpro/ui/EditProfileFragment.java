package com.example.myfirsrtpro.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myfirsrtpro.R;

public class EditProfileFragment extends Fragment {

    private ImageView profilepic;
    private EditText editemail,editname,editage,editpassword;
    private Button updateBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editprofile,container,false);

        profilepic = root.findViewById(R.id.editprofilepic);
        editemail = root.findViewById(R.id.edituseremail);
        editname = root.findViewById(R.id.editusername);
        editage = root.findViewById(R.id.edituserage);
        editpassword = root.findViewById(R.id.edituserpass);
        updateBtn = root.findViewById(R.id.buttonupdate);

        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });

    updateBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            updateUserProfile();
        }
    });


        return root;
    }

    private void updateUserProfile(){

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(data.getData() != null){
            Uri profileUri = data.getData();
            profilepic.setImageURI(profileUri);


        }
    }
}
