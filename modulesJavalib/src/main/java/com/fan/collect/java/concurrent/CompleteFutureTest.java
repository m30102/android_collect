package com.fan.collect.java.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompleteFutureTest {

//    Future  的get 可以让线程阻塞
    // CountDownLatch 可以

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
//        test4();
    }


    private static void test4()  {
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            return "111";
        });
        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            return "222";
        });
        CompletableFuture<String> future = futureA.thenCombine(futureB, (s, s2) -> {
            return s + s2;
        });
        try {
            String s = future.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


    }
    private static void test3(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Supplier<String> taskA = () ->{
            System.out.println(System.currentTimeMillis()+" A执行线程:"+Thread.currentThread().getId());
            return "qwe";
        };

        Function<String,String> taskB = s->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(System.currentTimeMillis()+" B执行线程:"+Thread.currentThread().getId() +" s:"+s);
            return s+"__123";
        };


//        CompletableFuture<String> future = CompletableFuture.supplyAsync(taskA, executorService).thenApply(taskB);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(taskA, executorService);
        /*try {
            Thread.sleep(1000);// 保证A 先执行完毕
        } catch (Exception e) {
        }*/
//        boolean done = future.isDone();
//        System.out.println("done:"+done);
        // taskA执行完毕才会执行taskB, 如果睡眠1s 这里会在主线程执行，没问题，因为A已经执行完了，这里交给主线程执行没毛病。
        //    如果不睡眠 会马上和A同一个线程执行，当A 执行完毕后再执行B
        CompletableFuture<String> futureb = future.thenApply(taskB);// 这里调用future和上面链式调用future 有区别的
//        future.thenApplyAsync(taskB);// taskA执行完毕才会执行taskB ,异步线程池
//        future.thenApplyAsync(taskB,executorService);// taskA执行完毕才会执行taskB
        try {
            System.out.println(System.currentTimeMillis()+" jion1:"+Thread.currentThread().getId());
            String s = futureb.join();//CompletionException
            System.out.println(System.currentTimeMillis()+" jion2:"+Thread.currentThread().getId());
//            String s = futureb.get();
            System.out.println(s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        future.thenAccept()// 和thenApply区别是无返回值, 如果前一个任务没有结果或者不需要前一个任务的结果就用thenAccept
//        future.thenRun()// 没有入参，没有返回值

//        then的api表示前面执行完之后再执行后面的任务



    }
    private static void test2(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<Void> task = CompletableFuture.runAsync(() -> {
            System.out.println(System.currentTimeMillis()+" 执行线程1:"+Thread.currentThread().getId());
            try {
                Thread.sleep(3000);
                System.out.println(System.currentTimeMillis()+" 执行线程2:"+Thread.currentThread().getId());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, executorService);

        System.out.println(System.currentTimeMillis()+" 当前线程1:"+Thread.currentThread().getId());
        try {
            task.get();
            System.out.println(System.currentTimeMillis()+" 当前线程2:"+Thread.currentThread().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void test1() {

        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<String> task = CompletableFuture.supplyAsync(() -> {
            System.out.println(System.currentTimeMillis()+" 执行线程:"+Thread.currentThread().getId());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "qwe";
        },executorService);
        try {
            System.out.println(System.currentTimeMillis()+" 当前线程1:"+Thread.currentThread().getId());
            String s = task.get();// 这里会阻塞
            System.out.println(System.currentTimeMillis()+" 当前线程2:"+Thread.currentThread().getId());
            System.out.println(s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
