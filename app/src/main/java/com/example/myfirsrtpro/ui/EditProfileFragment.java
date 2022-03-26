package com.example.myfirsrtpro.ui;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myfirsrtpro.FireBaseEvent;
import com.example.myfirsrtpro.LoginActivity;
import com.example.myfirsrtpro.R;
import com.example.myfirsrtpro.SignUpActivity;
import com.example.myfirsrtpro.User;
import com.example.myfirsrtpro.nav_menu1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class EditProfileFragment extends Fragment implements View.OnClickListener{

    //gallery and camera
    private static final int GALLERY_REQUEST = 1;
    private static final int CAMERA_REQUEST = 0;
    //for picture of camera
    private Bitmap picture;


    private ArrayList<User> userslist;

    private ImageView profilepic;
    private EditText editemail,editname,editage,editpassword;
    private Button updateBtn,setGallery,setCamera;
    private String pic = "";

    private RadioButton FemaleRadioButtonEdit,MaleRadioButtonEdit;

    //authentication
    private static final String TAG = "FIREBASE";
    private FirebaseAuth mAuth;


    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://myfirsrtpro-default-rtdb.europe-west1.firebasedatabase.app/");

    String UID = mFirebaseAuth.getUid();
    //build a ref for user related data in real time DataBase using user id
    DatabaseReference myRef = database.getReference("users/" + UID);




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editprofile,container,false);


        editemail = root.findViewById(R.id.edituseremail);
        editname = root.findViewById(R.id.editusername);
        editage = root.findViewById(R.id.edituserage);
        editpassword = root.findViewById(R.id.edituserpass);
        updateBtn = root.findViewById(R.id.buttonupdate);
        FemaleRadioButtonEdit = root.findViewById(R.id.FemaleRadioButtonEdit);
        MaleRadioButtonEdit = root.findViewById(R.id.MaleRadioButtonEdit);
        setCamera = root.findViewById(R.id.setCamera);
        setGallery = root.findViewById(R.id.setGallery);
        profilepic = root.findViewById(R.id.editprofilepic);

        updateBtn.setOnClickListener(this);
        setGallery.setOnClickListener(this);
        setCamera.setOnClickListener(this);

        userslist = new ArrayList<>();//each UID has only one user !


        mAuth = FirebaseAuth.getInstance();



           /* public void onClick(View view) {
                *//*Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);*//*
            }
*/



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.child("").getValue(User.class);
                    userslist.add(user);
                    showUserProfile(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*if(myRef.child(userslist.get(0).getKey()).child("image")==null || myRef.child(userslist.get(0).getKey()).child("image").equals("")){
            profilepic = root.findViewById(R.id.editprofilepic);
        }
        else{
            Bitmap myBit;
            String profilepicSTR = myRef.child(userslist.get(0).getKey()).child("image").toString();
            try{
                byte[] encodeByte = Base64.decode(profilepicSTR,Base64.DEFAULT);
                InputStream inputStream = new ByteArrayInputStream(encodeByte);
                myBit = BitmapFactory.decodeStream(inputStream);
            }
            catch (Exception e){
                e.getMessage();
                myBit = null;
            }

            profilepic.setImageBitmap(myBit);

        }
*/

       /* updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserProfile();
            }
        });*/




        return root;
    }


    private void showUserProfile(User user){

        editemail.setText(user.getEmail());
        editname.setText(user.getName());
        editage.setText(user.getAge()+"");

        pic = user.getImage();
        if(pic!=null){
            picture = StringToBitMap(pic);
            profilepic.setImageBitmap(picture);
        }

    }

    private void updateUserProfile(){

        String name1 = editname.getText().toString();
        int age1 = Integer.parseInt(editage.getText().toString());
        boolean female1 = true;
        if(FemaleRadioButtonEdit.isChecked()){
            female1 = true;
        }
        else if(MaleRadioButtonEdit.isChecked()){
            female1 = false;
        }
        String email1 = editemail.getText().toString();
        String pass1 = editpassword.getText().toString();

        if(validatePassword1(pass1)){
            myRef.child(userslist.get(0).getKey()).child("password").setValue(pass1);
            myRef.child(userslist.get(0).getKey()).child("age").setValue(age1);
            myRef.child(userslist.get(0).getKey()).child("email").setValue(email1);
            myRef.child(userslist.get(0).getKey()).child("name").setValue(name1);
            myRef.child(userslist.get(0).getKey()).child("female").setValue(female1);

            Toast.makeText(getActivity(), "your profile is updated.",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(getActivity(), "invalid password .",Toast.LENGTH_SHORT).show();
        }


        /*String oldPass = myRef.child(userslist.get(0).getKey()).child("password").get().toString();

        AuthCredential credential = EmailAuthProvider.getCredential(email1,oldPass);

        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    user.updatePassword(pass1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Password updated");
                            } else {
                                Log.d(TAG, "Error password not updated");
                            }
                        }
                    });
                } else {
                    Log.d(TAG, "Error auth failed");
                }
            }
        });*/
    }
    public void mUpdate(String email1,String pass1){
        //mAuth.getCurrentUser().delete();
        mAuth.createUserWithEmailAndPassword(email1,pass1)
                .addOnCompleteListener(this.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            String name1 = editname.getText().toString();
                            int age1 = Integer.parseInt(editage.getText().toString());
                            boolean female1 = true;
                            if(FemaleRadioButtonEdit.isChecked()){
                                female1 = true;
                            }
                            else if(MaleRadioButtonEdit.isChecked()){
                                female1 = false;
                            }

                            myRef.child(userslist.get(0).getKey()).child("age").setValue(age1);
                            myRef.child(userslist.get(0).getKey()).child("email").setValue(email1);
                            myRef.child(userslist.get(0).getKey()).child("password").setValue(pass1);
                            myRef.child(userslist.get(0).getKey()).child("name").setValue(name1);
                            myRef.child(userslist.get(0).getKey()).child("female").setValue(female1);
                            myRef.child(userslist.get(0).getKey()).child("image").setValue(pic);

                            updateUserImage();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",Toast.LENGTH_SHORT).show();

                        }
                    }

                });

    }



    public void onClick(View view) {
        if(view.getId() == R.id.setCamera){
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, CAMERA_REQUEST);
        }else if(view.getId() == R.id.setGallery){
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, GALLERY_REQUEST);
        }
        else if(view.getId() == R.id.buttonupdate){
            updateUserProfile();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST){
            //RESULT_OK --> the camera managed to take a picture
            if(resultCode == RESULT_OK){
                picture = (Bitmap) data.getExtras().get("data");
                //set image captured to be the new image
                profilepic.setImageBitmap(picture);
            }
        }
        else
        {
            if(resultCode == RESULT_OK){
                Uri targetUri = data.getData();//the file location
                try{
                    //Decode an input stream into bitmap
                    picture = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(targetUri));
                    profilepic.setImageBitmap(picture);
                }
                //catch - don't kill the application if the file didn't open --> print the error
                catch(FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }

        profilepic.buildDrawingCache();
        Bitmap bmap = profilepic.getDrawingCache();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] arr = baos.toByteArray();
        this.pic = Base64.encodeToString(arr,Base64.DEFAULT);
        myRef.child(userslist.get(0).getKey()).child("image").setValue(pic);


    }


    public  void updateUserImage() {
        Bitmap myBit;
        String profilepicSTR = myRef.child(userslist.get(0).getKey()).child("image").toString();
        try {
            byte[] encodeByte = Base64.decode(profilepicSTR, Base64.DEFAULT);
            InputStream inputStream = new ByteArrayInputStream(encodeByte);
            myBit = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.getMessage();
            myBit = null;
        }
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


    public boolean validatePassword1(String str){
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

       /* profilepic.setImageBitmap(myBit);

        profilepic.buildDrawingCache();
        Bitmap bmap = profilepic.getDrawingCache();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] arr = baos.toByteArray();
        this.pic = Base64.encodeToString(arr,Base64.DEFAULT);

        myRef.child(userslist.get(0).getKey()).child("image").setValue(pic);
*/





}
