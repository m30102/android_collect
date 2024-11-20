package com.fan.collect.kt

fun cvof(vararg  pair: Pair<String,Any?>) = HashMap<String,Int>().apply{
    for (p in pair){
        val key = p.first
        val value = p.second
        when(value){
            is Int ->
                put(key,value);
        }
    }
}

fun main() {
    val map = cvof("a" to 3,"b" to 4,"c" to "5")
    println(map)
}