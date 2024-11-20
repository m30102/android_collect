package com.fan.collect.java.jvm;

public class VolatileTest {


    private static  boolean flag = false;// 主内存(堆)共享变量
    //volatile强制主内存读取而不是在线程缓存里读取,总线嗅探器通知所有线程副本变量更新,这就称为可见性MESI缓存一致性协议

    public static void test()throws Exception{
        new Thread(() -> {
            System.out.println("Thread1 -- start");
            while (!flag){
                //Thread.sleep(100);  可以刷新flag值
            }
            System.out.println("Thread1 -- end");
        }).start();

        Thread.sleep(100);
        new Thread(() -> {
            System.out.println("Thread2 -- start");
            flag = true;
            System.out.println("Thread2 -- end");
        }).start();
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
