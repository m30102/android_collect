package com.fan.collect.java;


import com.google.gson.JsonObject;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    public static void main(String[] args) throws SocketException {
        double v = Double.parseDouble("18.00");
        int a = (int) (v * 100);
        System.out.println(v);
        System.out.println(a);
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
