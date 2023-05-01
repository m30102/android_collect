package com.fan.collect.kt

import kotlinx.coroutines.*
// 协程可以用单线程模拟多线程
fun main() {
//    test0()
    test11()
//    test2()
//    test3()
//    test4()
//    test5()
//    printDot2()//Suspend function 'printDot2' should be called only from a coroutine or another suspend function
//    test6()
//    test7()
//    test8()
}






fun test0(){
    // 顶层协程会在main结束后一块结束,所以这里来不及打印
    GlobalScope.launch {
        println("aaaaa")
    }
}
private fun test11(){
    test1()
    test1()
}

var a = 0
private fun test1() {
    // runBlocking阻塞线程的协程,直到代码和子协程的代码执行完毕才释放. 测试环境使用

    runBlocking {
        println("a="+a)
        println(" in "+Thread.currentThread().id)// 1
//        Thread.sleep(1000)
        delay(1000)
        println(" finish!")
        a += 1
    }
    println("a:"+a)
    // in finish 打印后才打印out
    println(" out " + Thread.currentThread().id)// 1
}
/**
runBlocking end!
launch1 1
launch2 1
launch1 finished
launch2 finished
runBlocking finished!

launch创建子协程，如果外层协程结束了，那么子协程也会结束
 */
fun test2(){
    runBlocking {
        launch {
            println("launch1 "+Thread.currentThread().id)
            delay(1000)
            println("launch1 finished")
        }
        launch {
            println("launch2 "+Thread.currentThread().id)
            delay(1000)
            println("launch2 finished")
        }
        println("runBlocking end!")
    }
    println("runBlocking finished!")
}
// 可以开启10W个协程，但不能开启10W个线程
fun test3(){
    val start  = System.currentTimeMillis()
    runBlocking {
        repeat(100000){
            launch {
                println(".")
            }
        }
    }
    val end  = System.currentTimeMillis()
    println( end - start)
}

fun test4(){
    runBlocking {
        launch {
            printDot()
            printDot2()
        }
        println("test4")
    }
    println(" test4 end")
}
//suspend 声明 挂起函数，可以调用 delay。 但是不是协程作用域，无法再调用launch
suspend fun printDot(){
    println("printDot")
    delay(1000)
}
// coroutineScope 继承外部作用域,创建协程作用域，能在挂起函数和协程作用域中调用.launch只能在协程作用域中调用
suspend fun printDot2(){
    // 可以调用launch,会阻塞当前协程(执行完作用域和子协程代码)，但不阻塞线程。runBlocking会阻塞当前线程.
    coroutineScope {
        launch {
            print(".")
            delay(1000)
            print(",")
        }
        println("///")
    }
    println("__")
}
fun test5(){
    val job = Job()
    val coroutineScope = CoroutineScope(job)// CoroutineScope是一个函数
    coroutineScope.launch {

    }
    coroutineScope.launch {

    }
    // 调用一次cancel
    coroutineScope.cancel()
}
// launch不能获得执行结果,返回值为job对象,换async. async也需要在协程作用域中调用
/**
 *  Thread:1
    b------
    Thread:1
    result is 21
    cost 2025 ms.
 */
fun test6(){
    runBlocking {
        val start = System.currentTimeMillis()

        val result1 = async {
            println("Thread:"+Thread.currentThread().id)
            delay(1000)
            5+5
        }.await()// await阻塞当前协程
        println("b------")
        val result2 = async {
            println("Thread:"+Thread.currentThread().id)
            delay(1000)
            5+6
        }.await()
        println("result is ${result1 + result2}")
        val end = System.currentTimeMillis()
        println("cost ${end - start} ms.")

    }
}

/**
    cost1 6 ms.
    Thread:1
    Thread:1
    result is 20
    cost2 1018 ms.
 */
fun test7(){
    runBlocking {
        val start = System.currentTimeMillis()

        val result1 = async {
            println("Thread:"+Thread.currentThread().id)
            delay(1000)
            5+5
        }
        val result2 = async {
            println("Thread:"+Thread.currentThread().id)
            delay(1000)
            5+5
        }
        val end1 = System.currentTimeMillis()
        println("cost1 ${end1 - start} ms.")
        println("result is ${result1.await() + result2.await()}")
        val end2 = System.currentTimeMillis()
        println("cost2 ${end2 - start} ms.")

    }
}
// withContext相当于async
fun test8(){
    runBlocking {
        var result = withContext(Dispatchers.IO){
            2+3
        }
        println(result)
    }
}

// Dispatchers.IO 高并发  Dispatchers.Default 低并发 高计算


