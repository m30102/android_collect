package com.fan.collect.java;



import org.apache.commons.codec.binary.Base64;


public class Test {
    public void test(){
        System.out.println("-----");
    }
    public boolean isA(){
        return true;
    }

    public static void main(String[] args) {
        Test test = new Test();
        test = null;
        if(test == null && test.isA()){
            System.out.println("qqqq");
        }else{
            System.out.println("ccccc");
        }
    }
}
