package com.fan.collect.java;

import java.util.concurrent.Semaphore;

public class Test {

    private static final Semaphore semaphore = new Semaphore(1);
    public static void main(String[] args) {

     /*   new Thread(() -> {
            System.out.println("sub 1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("sub 2");
            semaphore.release();
        }).start();*/
        System.out.println("main 1");
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main 2");
    }
}