package com.fan.collect.kt

import java.util.Locale

fun main() {
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")



    val lambda  = { fruit:String -> fruit.length }
    val maxLengthFruit = list.maxByOrNull  (lambda)
    //list.maxBy({fruit:String -> fruit.length})
    //当Lambda参数是函数的最后一个参数时，可以将Lambda表达式移到函数括号的外面
    list.maxByOrNull(){fruit:String -> fruit.length}
    //如果Lambda参数是函数的唯一一个参数的话，还可以将函数的括号省略：
    list.maxByOrNull{fruit:String -> fruit.length}
    //Lambda表达式中的参数列表其实在大多数情况下不必声明参数类型
    list.maxByOrNull { fruit-> fruit.length}
    //当Lambda表达式的参数列表中只有一个参数时，也不必声明参数名，而是可以使用it关键字来代替
    list.maxByOrNull { it.length }//{}内最后一行代码作为lambda表达式的返回值.

    println("max length fruit is " + maxLengthFruit)

    //集合中的map函数是最常用的一种函数式API，它用于将集合中的每个元素都映射成一个另外的值，映射的规则在Lambda表达式中指定，最终生成一个新的集合。
    val list2 = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val newList = list2.filter { it.length <= 5 }
            .map { it.toUpperCase(Locale.getDefault()) }
    for (fruit in newList) {
        println(fruit)
    }
    //any函数用于判断集合中是否至少存在一个元素满足指定条件
    val anyResult = list.any { it.length <= 5 }
    //all函数用于判断集合中是否所有元素都满足指定条件
    val allResult = list.all { it.length <= 5 }
    println("anyResult is " + anyResult + ", allResult is " + allResult)

    // 调用java

    // 版本1  object 代表匿名类
  /*  Thread(object :Runnable{
        override fun run() {
            TODO("Not yet implemented")
        }
    }).start()*/
    // 版本2
    /*Thread(Runnable {
        println("sssss")
    }).start()*/
    /*Thread({

    }).start()
    Thread(){

    }.start()*/

    Thread{ println("sssss")}.start()
}