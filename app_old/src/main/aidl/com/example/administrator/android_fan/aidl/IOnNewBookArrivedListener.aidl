package com.example.administrator.android_fan.aidl;

import com.example.administrator.android_fan.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
