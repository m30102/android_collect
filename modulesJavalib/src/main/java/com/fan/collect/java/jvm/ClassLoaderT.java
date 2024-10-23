package com.fan.collect.java.jvm;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;

public class ClassLoaderT {
    void run(){
        ClassLoader classLoader = ClassLoaderT.class.getClassLoader();
        while(classLoader !=null){
            System.out.println("classLoader:"+classLoader);
            classLoader = classLoader.getParent();
        }
        /**
             classLoader:jdk.internal.loader.ClassLoaders$AppClassLoader@2328c243
             classLoader:jdk.internal.loader.ClassLoaders$PlatformClassLoader@368102c8
         */
    }


    /**
     * android:
         classLoader:dalvik.system.PathClassLoader[DexPathList[[zip file "/system/framework/org.apache.http.legacy.boot.jar", zip file "/d
         classLoader:java.lang.BootClassLoader@325eb08
     */
    public static void main(String[] args) {
        new ClassLoaderT().run();
    }
}
