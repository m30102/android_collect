package com.fan.collect.kt

import kotlin.math.max


fun sum(a:Int,b:Int):Int{
    return  a+b
}

fun sum2(a:Int,b:Int) :Int = a+b
fun sum3(a:Int,b:Int) = a + b

fun printSum(a:Int,b:Int){
    println("sum of $a and $b is ${ a +b }")
}

fun printParams(num: Int, str: String = "hello") {
    println("num is $num , str is $str")
}

fun main() {
    println("sum: ${sum(4,3)}")
    println("sum2: ${sum2(2,3)}")
    println("sum3: ${sum3(5,3)}")
    printSum(1,2)
    printParams(12)
    printParams(str = "world", num = 123)
    max(1,2);
}