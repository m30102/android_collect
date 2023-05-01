package com.fan.collect.kt

import java.util.Locale


fun main() {
//    val a:Study? = null;
//    doStudy(a);// 编译报错
//    doStudy(null)// 编译报错
}
//Kotlin默认所有的参数和变量都不可为空，所以这里传入的Study参数也一定不会为空 //    doStudy(null);// 编译报错
fun doStudy(study: Study) {
    study.readBooks()
    study.doHomework()
}
// 可空类型.参数可以空
fun doStudy2(study: Study?) {
//    study.readBooks() 编译报错
    study?.readBooks()// ?. 对象不为空时正常调用.为空时什么都不做
    study?.doHomework()
}
fun doStudy3(study:Study?){
    // 将不为空的study传入到右边的study
    study?.let { study ->
        study.readBooks()
        study.doHomework()
    }
}
fun doStudy4(study:Study?){
    study?.let {
        it.doHomework()
        it.readBooks()
    }
}

var study:Study? = null;
fun doStudy5(){
    if(study !=null){
//        study.readBooks() //报错。多线程修改问题
    }
    study?.let {
        it.readBooks() // OK
    }
}
// ?.  ?: 类似三元  左右两边表达式 为空则返回0 否则返回length
fun getTextLength(text: String?) = text?.length ?: 0

val content:String? = "213";

fun printUpperCase() {
    // 风险写法 断言不为null
    val upperCase = content!!.uppercase(Locale.getDefault())
    println(upperCase)
}


