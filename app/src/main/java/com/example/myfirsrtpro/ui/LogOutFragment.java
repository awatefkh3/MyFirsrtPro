package com.example.myfirsrtpro.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.myfirsrtpro.CalendarUtils;
import com.example.myfirsrtpro.FireBaseEventAdapter;
import com.example.myfirsrtpro.MusicService;
import com.example.myfirsrtpro.R;
import com.example.myfirsrtpro.databinding.FragmentLogoutBinding;
import com.google.firebase.database.DatabaseReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.time.LocalDate;
import java.util.ArrayList;

public class LogOutFragment extends Fragment {


    private FragmentLogoutBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        binding = FragmentLogoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;

    }


}
