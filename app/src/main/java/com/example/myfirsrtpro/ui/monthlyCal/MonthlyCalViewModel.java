package com.example.myfirsrtpro.ui.monthlyCal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MonthlyCalViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MonthlyCalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is about fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
