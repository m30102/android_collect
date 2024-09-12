package com.fan.collect.java;



import org.apache.commons.codec.binary.Base64;


public class Test {

    public static void main(String[] args) {

        byte[] bytes = Base64.encodeBase64("12321eqedqwdxe123333333333333333333333asd333szxc阿萨大大阿萨德阿wdxe123333333333333333333333asd333szxc阿萨大大阿萨德阿萨德asddadasd".getBytes(),true);
        java.lang.String s = new java.lang.String(bytes);
        System.out.println(s);
    }
}
