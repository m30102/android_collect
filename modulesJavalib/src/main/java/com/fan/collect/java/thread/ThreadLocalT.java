package com.fan.collect.java.thread;

public class ThreadLocalT {

    public static void main(String[] args) {
            ThreadLocal threadLocal = new ThreadLocal();
            ThreadLocal threadLocal2 = new ThreadLocal();
            threadLocal.set("a");
            threadLocal.set("c");
            threadLocal2.set("b");
        System.out.println(threadLocal.get());
        System.out.println(threadLocal2.get());
        new Thread(() -> {
            System.out.println(threadLocal.get());
            System.out.println(threadLocal2.get());

        }).start();


    }
}
