package com.fan.collect.java;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        float a = 100;
         float b = 22f;
        float c = a/ b;
        System.out.println(checkPhone("1399992221"));

    }

    public static boolean checkPhone(String phone) {
        Pattern pattern = Pattern.compile("^[1-5][23456789]\\d{7}$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
}
