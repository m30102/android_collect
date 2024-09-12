 package com.fan.collect.java.jni;

import java.io.File;

public class JniTest {

    public native String get();

    public native void set(String var1);

    static {
        // javac -h ./ JniDemo.java
//        System.loadLibrary("jni-test-c");
//        System.loadLibrary("jni-test-c");
        // 打印当前目录加载dll
        File file = new File(".");
        System.out.println("----------");
        System.out.println(file.getAbsolutePath());
        System.out.println("----------");
        // as run
//        System.loadLibrary("modulesJavalib/src/main/java/com/fan/collect/java/jni/Jnitest-c");
        System.loadLibrary("com/fan/collect/java/jni/Jnitest-cpp");
//        System.loadLibrary("Jnitest-c");

        // command:cd modulesJavalib\src\main\java>java javac com/fan/collect/java/jni/JniTest.java java com/fan/collect/java/jni/JniTest
//         System.loadLibrary("com/fan/collect/java/jni/Jnitest-c");
    }

    public static void main(String[] args) {
        JniTest var1 = new JniTest();
        String s = var1.get();
        System.out.println(s);
         var1.set(" new str from java");
         String s1 = var1.get();
         System.out.println(s1);
        /**
         invoke get from C++
         default str from C++!
         invoke set from C++,  new str from java
         invoke get from C++
         new str from java
         */
    }
}
