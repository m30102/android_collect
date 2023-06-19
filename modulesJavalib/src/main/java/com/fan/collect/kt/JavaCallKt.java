package com.fan.collect.kt;


import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class JavaCallKt {

    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface ni = networkInterfaces.nextElement();
            Enumeration<InetAddress> addresses = ni.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                if (addr instanceof Inet6Address) {
                    System.out.println(addr.getHostAddress());
                }
            }
        }
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String hostAddress = localHost.getHostAddress();
            System.out.println("hostAddress:"+hostAddress);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private static void staticCall() {
        HelperKt.doSomething();
        Util.func2();
        ConstantsMt.INSTANCE.func1();
        String a = Util.asd;
        String b = ConstantsMt.img_dir;
        String c = HelperKt.key_loginInfo;
    }
}
