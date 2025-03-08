package com.fan.frame;

import android.os.HandlerThread;
import android.widget.ListView;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class User {
    public String name;
    public String age;
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
