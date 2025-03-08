package com.fan.firstcode3;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

public class TestJava {


    public class Frag implements LifecycleOwner{

        @NonNull
        @Override
        public Lifecycle getLifecycle() {
            return null;
        }
    }
}
