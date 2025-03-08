package com.fan.collect.kt

import java.util.Arrays

/*
    public final class HelperKt {
        @NotNull
        public static final String key_loginInfo = "loginInfo";

        public static final void doSomething() {
            System.out.println("doSomething");
            Util.Companion.func1();
        }
    }
*/

// 顶层方法编译为静态方法  编译为java后 HelperKt.doSomething();
fun doSomething(){
    println("doSomething")
    // kt调用kt companion
    Util.func1()
    // kt调用kt companion JvmStatic
    Util.func2()
    // kt调用kt 非companion
    val u  = Util()
    u.func1()
    //kt 调用kt单例 方法1
    ConstantsMt.func1()
}

class KtClass(){
    fun funckt(){
        println("funckt")
    }
}

const val key_loginInfo = "loginInfo"

object ConstantsMt{
    const  val img_dir = "asd"

    fun func1():String{
        return "qwe"
    }
}

/**  编译后
 * public final class ConstantsMt {
    @NotNull
    public static final ConstantsMt INSTANCE = new ConstantsMt();

    @NotNull
    public static final String img_dir = "asd";

    @NotNull
    public final String func1() {
        return "qwe";
    }
}
 */



class Util{

    fun func1(){

    }
    companion object{

        fun func1(){}

        @kotlin.jvm.JvmField
        val asd  = "asd"

        // 注解只能加载单例类和companion object上面.
        @JvmStatic
        fun func2(){
            println(" func 2")
        }
    }
}
/**
 编译后
    public final class Util {
        public final void func1() {}

        @Metadata(mv = {1, 6, 0}, k = 1, xi = 48, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\002\n\002\b\002\b\003\030\0002\0020\001B\007\b\002\006\002\020\002J\006\020\005\032\0020\006J\b\020\007\032\0020\006H\007R\020\020\003\032\0020\0048\006X\006\002\n\000\006\b"}, d2 = {"Lcom/fan/collect/kt/Util$Companion;", "", "()V", "asd", "", "func1", "", "func2", "modulesJavalib"})
        public static final class Companion {

            private Companion() {}

            public final void func1() {}

            @JvmStatic
            public final void func2() {
                System.out.println(" func 2");
            }
        }

        @NotNull
        public static final Companion Companion = new Companion(null);

        @JvmField
        @NotNull
        public static final String asd = "asd";

        @JvmStatic
        public static final void func2() {
            Companion.func2();
        }
    }

 */

fun main(){
    println(Arrays.toString(listOf(1, 2, 3, 4, 5).toTypedArray()))
}