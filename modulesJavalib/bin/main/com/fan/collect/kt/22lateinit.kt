package com.fan.collect.kt

class Test2 {

    lateinit var s: String;

    fun func1() {
        s = "123"
    }

    fun func2() {
        if (!::s.isInitialized) {
            s = "234";
        }
    }

}

fun main() {
    val test = Test2()
//    println(test.s)
    test.func2()
//    test.func1()
    println(test.s)
}