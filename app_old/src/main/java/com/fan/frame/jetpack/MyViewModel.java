package com.fan.frame.jetpack;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

// viewmodel需要新建类
public class MyViewModel extends ViewModel {


    MutableLiveData<String> data = new MutableLiveData<>();



    int age;
    public MutableLiveData<String> getName() {
        return data;
    }
    public void setName(String str) {
        this.data.setValue(str);
    }

}
