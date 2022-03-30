package com.example.myfirsrtpro.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfirsrtpro.MusicService;
import com.example.myfirsrtpro.databinding.FragmentAboutBinding;


public class AboutFragment extends Fragment {
    private FragmentAboutBinding binding;
    private Intent musicIntent;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAboutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //this will start the service which will turn on the music
        musicIntent = new Intent(this.getContext(), MusicService.class);
        getContext().startService(musicIntent);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
