package com.fan.collect.kt

fun main() {

}


fun largerNumber(num1: Int, num2: Int): Int {
    var value = 0
    if (num1 > num2) {
        value = num1
    } else {
        value = num2
    }
    return value
}
fun largerNumber2(num1: Int, num2: Int): Int {
    val value = if (num1 > num2) {
        num1// 条件中最后一行作为返回值
    } else {
        num2
    }
    return value
}
fun largerNumber3(num1: Int, num2: Int): Int {
    return if (num1 > num2) {
        num1
    } else {
        num2
    }
}
// 省略函数体 if语句只算一行
fun largerNumber4(num1: Int, num2: Int) = if (num1 > num2) {
    num1
} else {
    num2
}
fun largerNumber5(num1: Int, num2: Int) = if (num1 > num2) num1 else num2

fun getScore(name: String) = if (name == "Tom") {
    86
} else if (name == "Jim") {
    77
} else if (name == "Jack") {
    95
} else if (name == "Lily") {
    100
} else {
    0
}


fun getScore2(name: String) = when (name) {
    "Tom" -> 86
    "Jim" -> 77
    "Jack" -> 95
    "Lily" -> 100
    else -> 0
}
fun getScore3(name: String) = when {
    name == "Tom" -> 86
    name == "Jim" -> 77
    name == "Jack" -> 95
    name == "Lily" -> 100
    else -> 0
}
fun getScore4(name: String) = when {
    name.startsWith("Tom") -> 86
    name == "Jim" -> 77
    name == "Jack" -> 95
    name == "Lily" -> 100
    else -> 0
}
fun checkNumber(num: Number) {
    when (num) {
        is Int -> println("number is Int")
        is Double -> println("number is Double")
        else -> println("number not support")
    }
}