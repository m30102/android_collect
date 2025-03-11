package com.fan.collect.java.callable;

import java.util.concurrent.Callable;
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
            futureTest2();
        }catch (Exception e){}
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
