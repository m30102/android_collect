package com.fan.collect.java;

import java.util.concurrent.Semaphore;

public class Test {

   int qwe;
   
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