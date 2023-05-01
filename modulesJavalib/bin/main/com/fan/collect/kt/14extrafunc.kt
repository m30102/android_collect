package com.fan.collect.kt

import java.lang.StringBuilder
import kotlin.math.max

// 扩展函数
fun String.lettersCount(): Int {

    var count = 0
    for (char in this) {
        if (char.isLetter()) {
            count++
        }

    }
    return count
}

/*
    public static final int lettersCount(@NotNull String $this$lettersCount) {
        Intrinsics.checkNotNullParameter($this$lettersCount, "<this>");
        int count = 0;
        byte b;
        int i;
        for (b = 0, i = $this$lettersCount.length(); b < i; ) {
            char char = $this$lettersCount.charAt(b);
            b++;
            if (Character.isLetter(char)) {
                int j = count;
                count = j + 1;
            }
        }
        return count;
    }
*/

fun StringBuilder.lettersCount():Int{
    var count = 0
    for (char in this) {
        if (char.isLetter()) {
            count++
        }

    }
    return count
}

fun test(block:StringBuilder.()->Unit){
//    block()// 不能直接调用。因为是定义在StringBuilder上的
    StringBuilder().block()
}



fun test2(o:(Int,Int) -> Int){
}

fun main() {

    var str = "12sad12";

    println(str.lettersCount())
    var str2 = StringBuilder("asdasd")
    println(str2.lettersCount())

    doSomething()
}