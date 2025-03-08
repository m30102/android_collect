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
        // 只有顶层方法和JvmStatic修饰才能是静态方法

        // java调用kt顶层静态
        HelperKt.doSomething();
        // java调用kt companion JvmStatic静态
        Util.func2();
        // java调用companion 方法1
        Util.Companion.func1();
        // java调用companion 方法2
        Util.Companion.func2();
        // java调用非companion 方法1
        new Util().func1();

        // java调用kt单例 方法1
        ConstantsMt.INSTANCE.func1();
        // java调用kt companion 静态属性
        String a = Util.asd;
        // java调用kt 单例静态属性
        String b = ConstantsMt.img_dir;
        // java调用kt 顶层静态属性
        String c = HelperKt.key_loginInfo;

        //kt 调用kt 参考 HelperKt.doSomething
    }
}
