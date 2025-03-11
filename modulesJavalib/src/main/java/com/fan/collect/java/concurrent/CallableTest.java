package com.fan.collect.java.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
// https://article.juejin.cn/post/7244800185023561784
public class CallableTest {

    static Callable<String> callable = new Callable(){

        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            System.out.println("in callable "+"_id_"+Thread.currentThread().getId());
            return "123qwe";
        }
    };

    public static void main(String[] args)  {
        try {
            futureTest1();
//            completable();
        }catch (Exception e){}
    }

    public static void completable() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new
                CompletableFuture<>();
        System.out.println(System.currentTimeMillis()+"_0_id_"+Thread.currentThread().getId());
        new Thread(() -> {
            try {
                System.out.println(System.currentTimeMillis()+" _1_id_"+Thread.currentThread().getId());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(System.currentTimeMillis()+"_2_id_"+Thread.currentThread().getId());
            future.complete("1111");
            System.out.println(System.currentTimeMillis()+"_3_id_"+Thread.currentThread().getId());
        }).start();
        System.out.println(System.currentTimeMillis()+"_4_id_"+Thread.currentThread().getId());
        String string = future.get();
        System.out.println(System.currentTimeMillis()+"_5_id_"+Thread.currentThread().getId());

    }

    public static void futureTest3()throws ExecutionException, InterruptedException{
        ExecutorService executor = Executors.newSingleThreadExecutor();
        System.out.println(System.currentTimeMillis()+"_id_"+Thread.currentThread().getId());
        Future<String> future = executor.submit(callable);
        System.out.println(System.currentTimeMillis()+"_id_"+Thread.currentThread().getId());
        try {
//            future.get()
            String s = future.get(1, TimeUnit.SECONDS);
            System.out.println(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()+"_id_"+Thread.currentThread().getId());
    }

    public static void completableFutureTest(){

    }
    public static void futureTest1(){
        ExecutorService executor = Executors.newCachedThreadPool();
//        Future future = new FutureTask<String>(callable);
        FutureTask<String> future1 = new FutureTask<String>(() -> {
            for(int i=0;i<1000;i++){
                System.out.println(Thread.currentThread().getId()+" 线程运行:"+i);
            }
            return  "qwe";
        });
        FutureTask<String> future2 = new FutureTask<String>(() -> {
            for(int i=0;i<1000;i++){
                System.out.println(Thread.currentThread().getId()+" 线程运行:"+i);
            }
            return  "asd";
        });
        executor.submit(future1);
        executor.submit(future2);
        try {
            String result1 = future1.get();// 这里会阻塞主线程,等子线程执行完毕
            String result2 = future2.get();
            System.out.println("result1:"+result1);
            System.out.println("result2:"+result2);
        } catch (Exception e) {
           e.printStackTrace();
        }
        for(int i=0;i<1000;i++){
            System.out.println(Thread.currentThread().getId()+" 线程运行:"+i);
        }
        System.out.println(Thread.currentThread().getId()+" 线程运行完毕");
    }
    public static void futureTest2()throws ExecutionException, InterruptedException{
        System.out.println(System.currentTimeMillis()+"_id_"+Thread.currentThread().getId());
        FutureTask<String> stringFutureTask = new FutureTask<>(callable);
        new Thread(stringFutureTask).start();
        System.out.println(System.currentTimeMillis()+"_id_"+Thread.currentThread().getId());
        String s = stringFutureTask.get();
        System.out.println(System.currentTimeMillis()+"_id_"+Thread.currentThread().getId());
        System.out.println(s);
    }

}
