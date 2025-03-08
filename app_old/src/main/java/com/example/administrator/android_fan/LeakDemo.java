package com.example.administrator.android_fan;

import android.app.Activity;

public class LeakDemo {

    public interface LeakInterface{
        void leak();
    }
    public static LeakInterface leakInterface;

    public static void testLeak(Activity activity){

        LeakInterface ll = new LeakInterface() {
            @Override
            public void leak() {
            }
        };

        leakInterface =ll;
    }
}
