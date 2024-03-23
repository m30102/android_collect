package com.example.broadcastbestpractice

fun highFunc(){
    val num1 = 100
    val num2 = 80
    val result1 = num1AndNum2(num1, num2) { n1, n2 ->
        n1 + n2
    }
    val result2 = num1AndNum2(num1, num2) { n1, n2 ->
        n1 - n2
    }
    val result3 = num1AndNum2(num1,num2,{
        n1, n2 -> n1 * n2
    })
    val result4 = num1AndNum2(num1,num2,operation = {
        n1,n2 -> 3
    })
    println("result1 is $result1")
    println("result2 is $result2")
    println("result3 is $result3")
    println("result4 is $result4")
}

fun plus(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int {
    return num1 - num2
}
fun test(){
    highFunc()
    var num1 = 2
    var num2 = 3
    val result5 = num1AndNum2(num1 = num1,num2 =num2, operation = ::plus)
    val result6 = num1AndNum2(num1,num2, ::minus)
    println("result5 is $result5")
    println("result6 is $result6")
}
private fun test2() {
    val stringBuilder = StringBuilder()
    stringBuilder.build(StringBuilder::aaaa)// 相当于stringBuilder这个对象调用了aaaa()
    println(stringBuilder.toString())
//    stringBuilder.build(StringBuilder::bbb) 报错, 方法签名不对
    println("--------")
    println(StringBuilder().build {
        append(1)
        aaaa()// 新建的StringBuilder 这个对象调用(非定义)了build方法，{}这里是个匿名扩展函数, 这个方法接收一个函数对象，这个函数对象是定义在StringBuilder中(所以可以执行append(1)),传递了这个函数对象之后，通过block()执行了append(1)和aaaa, 把 这个StringBuilder对象 传给了aaaa函数.
    }.toString())
    /**
            上面相当于这样
            stringBuilder.build(StringBuilder::xxxx)
            fun StringBuilder.xxxx(){
                append(1)
                aaaa()
            }
     */

    println("--------")
    println(StringBuilder().build2{ param1->
        append("3")
        append(param1 +2 )
    }.toString())// 通过block(12)执行, 12就是param1
    println(StringBuilder().build2({
        append(it)
    }).toString())// 通过block(12)执行, 12就是it
    println("!--------!")
    println(StringBuilder().build {
        println("===")
        aaaa()
        println("___")
    }.toString())
    println(StringBuilder().build {
        println("1111")
    })

    println(StringBuilder().build {
        append("xxx")
    }.toString())

    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val result = StringBuilder().build {
        append("Start eating fruits.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
    }
    println(result.toString())
    fun StringBuilder.ccc() {
        list.forEach {
            append(it).append("-")
        }
    }
    println(StringBuilder().build(StringBuilder::ccc))
}



// 将参数函数内联
inline fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result
}
/*  对应的java
    public static final int num1AndNum2(int num1, int num2, @NotNull Function2 operation) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        int $i$f$num1AndNum2 = 0, result = ((Number)operation.invoke(Integer.valueOf(num1), Integer.valueOf(num2))).intValue();
        return result;
    }
*/

// StringBuilder增加扩展函数build,函数体为右边的{},可以使用StringBuilder上下文.  build是个高阶函数. 相当于apply
// StringBuilder. 代表build函数接收的参数为定义在StringBuilder类中的函数其返回值为void,这个闭包也可以拥有上下文，如上面的ccc和下面的aaaa函数和append("xxx")
// stringBuilder.build{  } 代表stringBuilder对象执行{}的内容,然后return this,{}的内容相当于定义在StringBuilder类中无参无返回的函数,也就是这个block
fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
    block()
    return this
}
fun StringBuilder.build2(block: StringBuilder.(param1:Int) -> Unit): StringBuilder {
    block(12)
    return this
}

/*
    public static final void aaaa(@NotNull StringBuilder $this$aaaa) {
        Intrinsics.checkNotNullParameter($this$aaaa, "<this>");
        $this$aaaa.append("aaaa.");
    }
*/

fun StringBuilder.aaaa(){
    append("aaaa.")
}
fun StringBuilder.bbb(a:Int){
    append(a)
}

// block2可以作为参数传递,block1不行

inline fun noinlineTest(block1:(Int,Int)->Int,noinline  block2:(Int,Int)->Int){
    println(block1(1,2))
    println(block2(1,2))
//    noinlineTest2(block1)// 报错
    noinlineTest2(block2)
}
fun noinlineTest2(block:(Int,Int)->Int){
    println(block(2,3).toString()+"zxc")
}
/*
public static final void noinlineTest2(@NotNull Function2 block) {
    Intrinsics.checkNotNullParameter(block, "block");
    System.out.println(((Number)block.invoke(Integer.valueOf(2), Integer.valueOf(3))).intValue() + "zxc");
}
 */

fun inlineTest(){
    noinlineTest({ n1,n2->
        n1+n2
    }, { n1,n2->
        n1-n2
    })
}
/*
    public static final void inlineTest() {
        int $i$f$noinlineTest = 0;
        byte b1 = 2, b2 = 1;
        PrintStream printStream = System.out;
        int $i$a$-noinlineTest-_16AdvancedFunctionKt$inlineTest$1 = 0;
        int i = b2 + b1;
        printStream.println(i);
        System.out.println(((Number)_16AdvancedFunctionKt$inlineTest$2.INSTANCE.invoke(Integer.valueOf(1), Integer.valueOf(2))).intValue());
        noinlineTest2(block2$iv);
    }
    static final class _16AdvancedFunctionKt$inlineTest$2 extends Lambda implements Function2<Integer, Integer, Integer> {
        public static final _16AdvancedFunctionKt$inlineTest$2 INSTANCE = new _16AdvancedFunctionKt$inlineTest$2();
        @NotNull
        public final Integer invoke(int n1, int n2) {
            return Integer.valueOf(n1 - n2);
        }
    }
    public static final void noinlineTest2(@NotNull Function2 block) {
        System.out.println(((Number)block.invoke(Integer.valueOf(2), Integer.valueOf(3))).intValue() + "zxc");
    }

 */


// 内联函数的函数类型参数是没有参数属性的，但可以传递给其他inline函数,如果没有加noline则不能用于传递给非内联函数,只是在编译时将函数类型参数的代码替换到调用内联函数的地方。
inline fun fun1(  op:()->Unit){
    fun2(op)
}
inline fun fun2(op: () -> Unit){

}

private fun pintStringTest() {
    println("main start")
    val str = ""
    printString(str) { s ->
        println("printString lambda start")
        if (s.isEmpty()) return // 若是return@printString(printString内联和非内联都行) 则只是return出block取消打印"printString lambda end",其他继续打印. 若是return(printString必须声明为内联) main end都不打印
        println(s)
        println("printString lambda end")
    }
    println("main end")
}


inline fun printString(str: String, block: (String) -> Unit) {
    println("printString begin")
    block(str)
    println("printString end")
}


// 一般都要将高阶函数声明为内联函数
fun runRunnable0( block: () -> Unit) {
    val runnable = Runnable {
        block()
        print("1")
    }
    runnable.run()
}





inline fun runRunnable1( block: () -> Unit) {
    block()// 无报错
    val runnable = Runnable {
        print("1")
//        block()// 报错. 因为这里创建的Runable是个对象，不是函数. 这里return不能完全终止内联函数runRunnable1,和调用runRunnable1处的函数。
//        内联函数所引用的block是可以被return的,并且完全终止内联函数,和调用内联函数的函数.
    //    匿名对象和内联函数特性冲突
    }
    runnable.run()

}

// 内联函数的lambda(就是这个block)允许使用return,return调内联和lambda的后面流程，但在匿名类runnable中不允许使用return. crossinline表示block一定不会使用return.才可以在runnable中使用,也可以return runnable
    inline fun runRunnable(crossinline block: () -> Unit) {
    val runnable = Runnable {
        block()
        return@Runnable
    }
    runnable.run()
    println("end..")

}

fun main() {
//    test()
//    test2()
//    outterFun()
//    inlineTest()
//    doOutterFun()
//    pintStringTest()
    testRunnable()
    busy()
}

inline fun innerReturn(){
    println("innerReturn1")
        return
    println("innerReturn2")
}


private fun testRunnable() {
    runRunnable0 {  println("0") }


    runRunnable1 {  println("1") }
    runRunnable {
        var a = 2;
        println("a :$a")
    }
}


fun doOutterFun(){
    println("doOutter fun1")// 正常打印
    outterFun()
    println("doOutter fun2")// 正常打印
}

//一个函数中，如果存在一个lambda表达式，在该lambda中不支持直接进行return退出该函数，比如：
fun outterFun() {
    innerFun(3) {
        println(it)// 通过f(m) 把3传到这里来了
//        return  //错误，不支持直接return
        //只支持通过标签，返回innerFun.innerFun继续执行 outterfun继续执行
        println("in inner lamb1")
        if(true)
            return@innerFun 1
        println("in inner lamb2")
        return@innerFun 2
    }
    println("in outter fun1")
    //如果是匿名或者具名函数，则支持直接return. outterfuun继续执行
    var f = fun(){
        println("in outter fun2")
        return
    }
    f()
    println("in outter fun3")
    inlineInnerFun {
        println("in inlineInner lamb1")
        return // 退出inlineInnerFun和outterfun
        println("in inlineInner lamb2")
    }
    println("in outter fun4")
}
inline fun inlineInnerFun(f:()->Unit){
    println("in inlineInnerFun1")
    f()
    println("in inlineInnerFun2")
}
fun innerFun(m:Int,f: (a:Int) -> Int) {
    println("in innerFun 1")
    println(f(m))
    println("in innerFun 2")
}

/*
public static final void inlineInnerFun(@NotNull Function0 f) {
    Intrinsics.checkNotNullParameter(f, "f");
    int $i$f$inlineInnerFun = 0;
    System.out.println("in inlineInnerFun1");
    f.invoke();
    System.out.println("in inlineInnerFun2");
}

public static final void innerFun(int m, @NotNull Function1 f) {
    Intrinsics.checkNotNullParameter(f, "f");
    System.out.println("in innerFun 1");
    System.out.println(((Number)f.invoke(Integer.valueOf(m))).intValue());
    System.out.println("in innerFun 2");
}
 */




fun busy(){
    check("qwe"){
        return
    }
    println("busy")
}

 inline fun check(s:String,b:()->Unit){
     if(s =="qwe"){
         b()
     }
 }
