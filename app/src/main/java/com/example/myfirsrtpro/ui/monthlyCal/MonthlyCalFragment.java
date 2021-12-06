package com.example.myfirsrtpro.ui.monthlyCal;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfirsrtpro.databinding.FragmentMonthlycalBinding;

public class MonthlyCalFragment extends Fragment {
    private MonthlyCalViewModel monthlyCalViewModel;
    private FragmentMonthlycalBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        monthlyCalViewModel =
                new ViewModelProvider(this).get(MonthlyCalViewModel.class);

        binding = FragmentMonthlycalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textAbout;
//        aboutViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}
