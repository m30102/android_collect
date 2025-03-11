package com.fan.collect.java.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import kotlinx.coroutines.internal.ThreadSafeHeap;

public class CallableTest {

    static Callable<String> callable = new Callable(){

        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            System.out.println("return"+"_id_"+Thread.currentThread().getId());
            return "123qwe";
        }
    };

    public static void main(String[] args)  {
        try {
//            futureTest2();
            completable();
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

    public static void futureTest2()throws ExecutionException, InterruptedException{
        ExecutorService executor = Executors.newSingleThreadExecutor();
        System.out.println(System.currentTimeMillis()+"_id_"+Thread.currentThread().getId());
        Future<String> future = executor.submit(callable);
        System.out.println(System.currentTimeMillis()+"_id_"+Thread.currentThread().getId());
        try {
            String s = future.get(1, TimeUnit.SECONDS);
            System.out.println(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()+"_id_"+Thread.currentThread().getId());
    }

    public static void completableFutureTest(){

    }

    public static void futureTest()throws ExecutionException, InterruptedException{
        System.out.println(System.currentTimeMillis()+"_id_"+Thread.currentThread().getId());
        FutureTask<String> stringFutureTask = new FutureTask<>(callable);
        new Thread(stringFutureTask).start();
        System.out.println(System.currentTimeMillis()+"_id_"+Thread.currentThread().getId());
        String s = stringFutureTask.get();
        System.out.println(System.currentTimeMillis()+"_id_"+Thread.currentThread().getId());
        System.out.println(s);
    }

}
