package com.fan.collect.kt

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

// https://www.bilibili.com/video/BV1uo4y1y7ZF
// https://www.bilibili.com/video/BV1KJ41137E9
fun main() {
//    testXc0()
    testXc1()


}
fun testXc0(){
// GlobalScope.launch(Dispatchers.Main) { // android中才能用
 GlobalScope.launch {
     println("threadId1:"+Thread.currentThread().id)
     val result = withContext(Dispatchers.IO){
         println("threadId2:"+Thread.currentThread().id)
         "qwe" + "123"
     }
     println("result:$result threadId:"+Thread.currentThread().id)
 }
    Thread.sleep(1000)
}

fun testXc1(){
    // 协程(launch{} 的代码 )在执行有suspend标记的函数的时候，这个协程会被挂起（这个协程所在的线程，不在运行这个协程了，其实真正被挂起不是因为关键字，而是withContext内部更细的代码） 这个协程会在指定线程执行，执行完之后会返回之前的线程
    // 比如这个协程(launch{} 的代码 )执行到 getUser，（这个协程所在的线程，不在运行这个协程了），getUser 会在指定的线程(Dispatchers.IO)执行，执行完之后才在原线程执行下面的println
    // 挂起 就相当于暂时切走，用指定线程执行 等会切换回来
    //  suspend 意义在于提醒调用者 在协程里调用这个函数
    GlobalScope.launch{
        getUser()
        println("getuser")
    }
    Thread.sleep(1000)
}
private suspend fun getUser(){
    val user = get()
    show(user)
}
data class User(var name:String,var age:Int)
private fun show(user:User){
    println( "user:$user")
}

private suspend fun get() = withContext(Dispatchers.IO){
    User("haha",12)
}

