package com.fan.collect.java.thread;

public class 交替打印 {

    public static void main(String[] args) {
        Business business = new Business();
        System.out.println("ccccc");
        new Thread(() -> {
            for (int i = 1; i <= 50; i++) {
                business.sub(i);
            }
        }).start();

        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }

    }
}

class Business {

    private boolean bsub = true;

    public synchronized void sub(int i) {
        while (!bsub) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 10; j++) {
            System.out.println("sub thread sequence of" + j + ",loop of" + i);
        }
        bsub = false;
        notify();
    }

    public synchronized void main(int i) {
        while (bsub) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 10; j++) {
            System.out.println("main thread sequence of" + j + ",loop of" + i);
        }
        bsub = true;
        notify();
    }

}
