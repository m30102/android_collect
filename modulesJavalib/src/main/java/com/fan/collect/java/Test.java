package com.fan.collect.java;


import com.google.gson.JsonObject;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {



    public static void dealPreCard() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(2147483647123l);
        System.out.println(format);
        System.out.println(System.currentTimeMillis());
        String format2 = dateFormat.format(System.currentTimeMillis());
        System.out.println(format2);
        System.out.println(Integer.MAX_VALUE);
        int a = (int) 2147483647123l;
        System.out.println(a);
        System.out.println(Long.MAX_VALUE);

        //        2147483647
        //        2306071459
//        9223372036854775807

    }

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static void find(Map<String, Integer> map,int target,List<Integer> result,List<List<Integer>> result2){

        System.out.println("target:"+target);
        List<Integer> values = new ArrayList<>(map.values());
        Map<String, Integer> mapNext = new HashMap<>();
        mapNext.putAll(map);
        for (Map.Entry<String,Integer> entry:map.entrySet()){

            String key = entry.getKey();
            int value = entry.getValue();
            int remain = target - value;
            int index = values.indexOf(remain);
            if(index > 0 ){
                System.out.println("key:"+key +" value:"+value+" remain:"+remain+" target:"+target);
                result.add(value);
                result.add(remain);
                result2.add(new ArrayList<>(result));
                result.clear();
            }else{
                result.add(value);
                System.out.println("key:"+key +" value:"+value+" remain:"+remain+" target:"+target);
                mapNext.remove(entry.getKey());
                find(mapNext,remain,result,result2);
            }
        }
    }

    public static void main(String[] args) throws SocketException {

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        map.put("E", 5);
        map.put("F", 6);
        map.put("G", 2);
        map.put("H", 8);

        int target = 8;
        ArrayList<Integer> objects = new ArrayList<>();
        List<List<Integer>> objects2 = new ArrayList<>();
        find(map,target,objects,objects2);
        System.out.println(objects2);
        // 打印结果
    }
    public static void test(Map<String, Integer> map,int target,List<Integer> result,List<List<Integer>> result2){

        for(Map.Entry<String,Integer> entry:map.entrySet()){
            map.remove(entry.getKey());
        }
    }

    public static String getLocalIpV4(final boolean useIPv4) {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            LinkedList<InetAddress> adds = new LinkedList<>();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                // To prevent phone of xiaomi return "10.0.2.15"
                if (!ni.isUp() || ni.isLoopback()) continue;
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    adds.addFirst(addresses.nextElement());
                }
            }
            for (InetAddress add : adds) {
                if (!add.isLoopbackAddress()) {
                    String hostAddress = add.getHostAddress();
                    boolean isIPv4 = hostAddress.indexOf(':') < 0;
                    if (useIPv4) {
                        if (isIPv4) {
                            return hostAddress;
                        }
                    } else {
                        if (!isIPv4) {
                            int index = hostAddress.indexOf('%');
                            return index < 0
                                    ? hostAddress.toUpperCase()
                                    : hostAddress.substring(0, index).toUpperCase();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void getIp() throws SocketException {
        Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
        while (n.hasMoreElements()) { //for each interface
            System.out.println("----------------------------------------------------");
            NetworkInterface e = n.nextElement();
            //name of the interface
            System.out.println("Interface Name:" + e.getName());
    /* A interface may be binded to many IP addresses like IPv4 and IPv6
        hence getting the Enumeration of list of IP addresses  */
            Enumeration<InetAddress> a = e.getInetAddresses();
            while (a.hasMoreElements()) {
                InetAddress addr = a.nextElement();
                String add = addr.getHostAddress().toString();
                if (add.length() < 17)
                    System.out.println("IPv4 Address:" + add);
                else
                    System.out.println("IPv6 Address:" + add);
            }
            if (e.getHardwareAddress() != null) {
                // getting the mac address of the particular network interface
                byte[] mac = e.getHardwareAddress();
                // properly formatting the mac address
                StringBuilder macAddress = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    macAddress.append(String.format("%03X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                System.out.println("Hardware adrress:" + macAddress.toString());
            }
            System.out.println("----------------------------------------------------");
        }
    }


    public static String listToJson(List<?> list) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                json.append(objectToJson(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }

    public static String objectToJson(Object object) {
        StringBuilder json = new StringBuilder();
        if (object == null) {
            json.append("\"\"");
        } else if (object instanceof String || object instanceof Integer) {
            json.append("\"").append(object.toString()).append("\"");
        }
        return json.toString();
    }

}
