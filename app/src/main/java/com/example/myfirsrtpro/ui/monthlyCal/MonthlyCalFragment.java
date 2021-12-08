package com.example.myfirsrtpro.ui.monthlyCal;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfirsrtpro.Item;
import com.example.myfirsrtpro.databinding.FragmentMonthlycalBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MonthlyCalFragment extends Fragment {
    private FragmentMonthlycalBinding binding;
    private FirebaseAuth mFirebaseAuth  = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://myfirsrtpro-default-rtdb.europe-west1.firebasedatabase.app/");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentMonthlycalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textAbout;
//        aboutViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        // Write a message to the database
        String UID  = mFirebaseAuth.getUid();
        DatabaseReference myRef = database.getReference("users/"+UID);
        //getReference returns root - the path is users / all (for me )

        //todo this
        myRef.push().setValue(new Item()); //put the object

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}
