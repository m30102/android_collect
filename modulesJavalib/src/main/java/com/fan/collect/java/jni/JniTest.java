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
        // cd  ../../../../../
        // command:cd modulesJavalib\src\main\java>java javac com/fan/collect/java/jni/JniTest.java java com/fan/collect/java/jni/JniTest
//         System.loadLibrary("com/fan/collect/java/jni/Jnitest-c");
    }


        /*

         javac -h  ./ JniDemo.java  产生头文件
         javac -encoding UTF-8 -h  ./ JniTest.java
        * https://blog.51cto.com/u_11495341/3040148
        * 若是c++编译器实现h,那么extern c 表示告诉c++编译器 {}里面的内容是以c的形式编译.
        * 若c实现这个h，那么通过c++编译器编译.c后，成为c的obj格式文件，c++直接引入h可直接调用
        * 若c++实现这个h，那么cpp的实现也要加上extern "C", 通过c++编译器编译.cpp后, 成为c的obj格式文件， c可以直接引入h调用
        *
        * .h文件都要这样写，只不过.c不需要在函数中extern "C"，cpp需要。
        *
        * JNIEXPORT 是宏定义，表示一个函数需要暴露给共享库外部使用时JNIEXPORT 在 Window 和 Linux 上有不同的定义：
        * JNICALL 表示一个函数是 JNI 函数
        * 
        * cmd 执行 而不是 powershell 执行
        * gcc -I %JAVA_HOME%\include -I %JAVA_HOME%\include\win32  -fPIC -shared -o Jnitest-c.dll   test.c
        * g++ -I %JAVA_HOME%\include -I %JAVA_HOME%\include\win32  -fPIC -shared -o Jnitest-cpp.dll   test.cpp
        * 
        * 
        *
        */
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
