package com.fan.collect.java;


import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;

public class Test {


    interface Action{
        void doAction();
    }
    static Map<Integer, Action> actions = new HashMap();
    static {
        actions.put(1, (Action) () -> System.out.println("doAction1"));
        actions.put(2, (Action) () -> System.out.println("doAction2"));
        actions.put(3, (Action) () -> System.out.println("doAction3"));
    }
    public static void what2do(int actionId){
        Optional.ofNullable(actions.get(actionId))
                .orElse(() -> System.out.println("action default"))
                .doAction();
    }

    boolean needUpdate = true;

    public void checkUpdate(){
        new Thread(() -> {

            
            int currentVersion = 1;
            int serverVerion = 3;
            if(currentVersion < serverVerion){
                needUpdate = true;
            }
        }).start();


    }

     void run(){
        ClassLoader classLoader = Test.class.getClassLoader();
         System.out.println();
        while(classLoader !=null){
            System.out.println("classLoader:"+classLoader);
            classLoader = classLoader.getParent();
        }
    }
    public static void main(String[] args)  {

        new Test().run();
    }

    private static void countdown() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(0);
        task1(value -> {
            countDownLatch.countDown();
            System.out.println("countDownLatch:"+countDownLatch.getCount());
        });
        task2(value -> {
            countDownLatch.countDown();
            System.out.println("countDownLatch:"+countDownLatch.getCount());
        });
        System.out.println("await " + System.currentTimeMillis());
        new Thread(() -> {
            try {
                System.out.println("awaitinner1 " + System.currentTimeMillis());
                countDownLatch.await();
                System.out.println("awaitinner2 " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        System.out.println("await2 " + System.currentTimeMillis());
        task3();
        Thread.sleep(5000);
    }

    public interface CompleteListener{
        void complete(String value);
    }
    public static void task1(CompleteListener completeListener){
         completeListener.complete("1");
    }
    public static void task2(CompleteListener completeListener){
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                completeListener.complete("2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
    public static void task3(){}




    public static class MedalService {

        public String getMedalInfo(long userId) {
            try {
                Thread.sleep(500); //模拟调用耗时
            } catch (Exception e) {
            }
            return "守护勋章";
        }
    }

}
