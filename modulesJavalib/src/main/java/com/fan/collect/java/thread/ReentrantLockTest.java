package com.fan.collect.java.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {


    static class RcSyncPrinter implements Runnable {

        private static final int PRINT_COUNT = 10;
        private final Lock reentrantLock;
        private final Condition thisCondition;
        private final Condition nextCondition;
        private final char printChar;

        public RcSyncPrinter(Lock reentrantLock, Condition thisCondition, Condition nextCondition,
                             char printChar) {
            this.reentrantLock = reentrantLock;
            this.nextCondition = nextCondition;
            this.thisCondition = thisCondition;
            this.printChar = printChar;
        }

        @Override
        public void run() {
            reentrantLock.lock();
            try {
                for (int i = 0; i < PRINT_COUNT; i++) {
                    nextCondition.signal();
                    if (i < PRINT_COUNT - 1) {
                        try {
                            thisCondition.await();
                            System.out.print(printChar);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } finally {
                reentrantLock.unlock();
            }
        }
    }


    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        Thread printerA = new Thread(new RcSyncPrinter(lock, conditionA, conditionB, 'A'));
        Thread printerB = new Thread(new RcSyncPrinter(lock, conditionB, conditionC, 'B'));
        Thread printerC = new Thread(new RcSyncPrinter(lock, conditionC, conditionA, 'C'));
        printerA.start();
        printerB.start();
        printerC.start();

    }

}
