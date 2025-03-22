package com.fan.collect.java;

import java.util.concurrent.Semaphore;

public class Test {
    String a;
    String b;
    String c;
    private static final Semaphore semaphore = new Semaphore(1);
    public static void main(String[] args) {

        Integer a = 100;
        Integer b = 100;
        System.out.println(a == b);

        Integer c = 200;
        Integer d = 200;
        System.out.println(c ==d);

        Integer e = 200;
        int f = 200;
        System.out.println(e == f);
    }
}