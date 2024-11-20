package com.fan.collect.kt
val range = 0..10  // 闭区间 包含两端点
val range1 = 0 until 10// 左闭右开区间
//    for (i in 0..10) println(i)
// for (i in 0 until 10 step 2) 0 2 4 6 8
// for (i in 10 downTo 1) 10 9 8 ..1
fun main() {
    for (i in 0..10) println(i)
}