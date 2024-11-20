package com.fan.interview;

import android.util.Log;
import com.google.android.material.tabs.TabLayout.TabGravity;

//https://www.bilibili.com/video/BV1LE421u7LD
public class JniDemo {
    private static final String TAG = "JniDemo";


    public static native int[] intArray(int[] arr);

    public static native String[] strArray(String[] arr);

    public static native void callStaticMethod();

    private static void testFun1(){
        Log.e(TAG,"private func testFun1 ");
    }
    private static String testFun2(){
        Log.e(TAG,"public func tetFun2 ");
        return "res_testFun2";
    }
    public static void testFun3(String name,int age,double height){
        Log.e(TAG,"call testFun3 name:"+name+" age:"+age+" height:"+height);
    }

    public static native void testStaticField();


    public static native User createUser();

}
