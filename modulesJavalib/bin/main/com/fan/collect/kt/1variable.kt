package com.fan.collect.kt

val PI = 3.14
fun main() {
    val a:Int = 1
    val b = 2
    val c:Int
    c = 3
    var x = 5
    x ++
    println(x)
    println(PI +1)
    println(PI)
    var a1:Int = 11;
    var a2:Byte = 12;
    println(a1.toByte() == a2)

    println(5/2.toDouble() == 2.5)

    val intArray:Array<Int> = arrayOf(2, 3);

    val array = Array(5) { it ->
        it * it;
    }
    array.forEach { print(it.toString()+" ") }
}