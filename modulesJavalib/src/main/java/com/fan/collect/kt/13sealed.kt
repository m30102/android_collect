package com.fan.collect.kt

import java.lang.Exception
import java.lang.IllegalArgumentException

interface Result{
}

class Success(val msg:String):Result
class Failure(val error:Exception):Result

fun getResultMsg(result:Result) = when(result){
    is Success ->result.msg
    is Failure ->result.error.message
    else -> throw IllegalArgumentException()
}

//密封类及其所有子类只能定义在同一个文件的顶层位置，不能嵌套在其他类中，这是被密封类底层的实现机制所限制的
// 与上面相比 少了else
sealed class Result2
class Success2(val msg:String):Result2() // 继承，所以这里加括号
class Failure2(val error:Exception):Result2()
fun getResultMsg2(result2:Result2) = when(result2){
    is Success2 ->result2.msg
    is Failure2 ->result2.error.message
}