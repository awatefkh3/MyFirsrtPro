package com.example.myfirsrtpro.ui.reminder;

import android.app.DatePickerDialog;
import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReminderViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReminderViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Select Date : ");


    }



    public LiveData<String> getText() {
        return mText;
    }
}
