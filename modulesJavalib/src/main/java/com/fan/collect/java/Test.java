package com.fan.collect.java;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Test {


    static abstract class A{
        public void getName(){
            System.out.println(getClass().getName());
        }
    }
    static class B extends A{

      /*  public void getName(){
            System.out.println(getClass().getName());
        }*/

    }

    public static void main(String[] args) {
         Map<String,String> supportedAuthProviders = new HashMap<>();
//        supportedAuthProviders.put("qq","11");
        supportedAuthProviders.forEach((s, s2) -> {
            System.out.println("key:"+s+ " value:"+s2);
        });

    }
}