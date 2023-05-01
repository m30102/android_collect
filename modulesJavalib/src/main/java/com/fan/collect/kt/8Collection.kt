package com.fan.collect.kt

import java.util.ArrayList
import java.util.Collections

val list = listOf<String>("a","b","c","d")// 只能读取
val list2 = mutableListOf("Apple", "Banana", "Orange", "Pear", "Grape")// 可变
val set = setOf("a",1)
val map = HashMap<String,Int>()
val map2 = mapOf("Apple" to 1, "Banana" to 2, "Orange" to 3, "Pear" to 4, "Grape" to 5)
fun main() {
    list2.add("")
    println()
    list.forEach { s: String ->
        print(s+",")
    }
    println()
    map["Apple"] = 1
    map["Banana"] = 2
    map["Orange"] = 3
    map["Pear"] = 4
    map["Grape"] = 5
    println(map)
    for((f,n) in map){
        println("fruit is " + f + ", number is " + n)
    }
}