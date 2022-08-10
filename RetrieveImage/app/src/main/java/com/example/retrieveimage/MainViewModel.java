package com.example.retrieveimage;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    // initialize variables
    MutableLiveData<Message> mutableLiveData=new MutableLiveData<>();

    // create set text method
//    public void setText(String s)
//    {
//        // set value
//        mutableLiveData.setValue(s);
//    }

    // create get text method
    public MutableLiveData<Message> getMessage()
    {
        return mutableLiveData;
    }
}
