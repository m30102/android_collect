package com.fan.collect.java.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {


    static int i = 6000;

    Lock lock = new ReentrantLock();

    AtomicInteger a = new AtomicInteger(6000);

    public void order2(){
        a.decrementAndGet();
    }
    public void order(){
        lock.lock();
        try {
            i --;
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        for(int i=0;i<6;i++){
            new Thread(() -> {
                for(int j=0;j<1000;j++){
                    reentrantLockTest.order();
                    reentrantLockTest.order2();
                }
            }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("i="+i);
        System.out.println("a="+reentrantLockTest.a.get());
    }

}
