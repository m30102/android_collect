package com.fan.collect.base.utils;
public class LogHelper {


    private static boolean IS_DEBUG = true;

    public static void d(String subTag, String msg) {
        if (!IS_DEBUG) {
            return;
        }
        Logger.d("[" + subTag + "]: " + msg);
    }

    public static void i(String subTag, String msg) {
        if (!IS_DEBUG) {
            return;
        }
        Logger.i("[" + subTag + "]: " + msg);
    }

    public static void e(String subTag, String msg) {
        if (!IS_DEBUG) {
            return;
        }
        Logger.e("[" + subTag + "]: " + msg);
    }
    public static void setDebug(boolean debug) {
        IS_DEBUG = debug;
    }
}
