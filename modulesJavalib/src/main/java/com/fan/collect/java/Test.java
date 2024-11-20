package com.fan.collect.java;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
=======
import java.util.concurrent.Semaphore;
>>>>>>> master

public class Test {

    private static final Semaphore semaphore = new Semaphore(1);
    public static void main(String[] args) {
<<<<<<< HEAD
        float a = 100;
         float b = 22f;
        float c = a/ b;
        System.out.println(checkPhone("1399992221"));
=======
>>>>>>> master

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
<<<<<<< HEAD

    public static boolean checkPhone(String phone) {
        Pattern pattern = Pattern.compile("^[1-5][23456789]\\d{7}$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
}
=======
}
>>>>>>> master
