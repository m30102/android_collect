package com.example.administrator.android_fan.aidl;

import com.example.administrator.android_fan.aidl.Book;
import com.example.administrator.android_fan.aidl.IOnNewBookArrivedListener;

interface IBookManager {
     List<Book> getBookList();
     void addBook(in Book book);
     void registerListener(IOnNewBookArrivedListener listener);
     void unregisterListener(IOnNewBookArrivedListener listener);
}