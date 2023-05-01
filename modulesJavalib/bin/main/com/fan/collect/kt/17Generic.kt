package com.fan.collect.kt

import java.util.Collections

// https://zhuanlan.zhihu.com/p/371232419
fun <T> method(param:T):T{
    return param
}
fun <T:Number> method2(param:T):T{
    return param
}
/*
    public static final <T extends Number> T method2(@NotNull Number param) {
        Intrinsics.checkNotNullParameter(param, "param");
        return (T)param;
    }
*/


fun <T> T.build2(block:T.()->Unit):T{
    block()
    return this
}
/*
    public static final <T> T build2(Object $this$build2, @NotNull Function1 block) {
        block.invoke($this$build2);
        return (T)$this$build2;
    }
*/

// 泛型实化 inline + reified
inline  fun <reified T> getGenericType() = T::class.java

fun main() {
    println(method<String>("12"))
    val result1 = getGenericType<String>()// 编译后 Class<String> result1 = String.class;
    val result2 = getGenericType<Int>()// Class<Integer> result2 = Integer.class;
    println("result1 is $result1")
    println("result2 is $result2")







}
// 协变:假设有一个MyClass<T>,A是B子类型，同时MyClass<A> 是MyClass<B>的子类型，那么MyClass可被成为在T这个泛型上是协变的, 泛型数据类型只可读 则协变
// T只能出现在out位置上, 不能出现在in位置上，所以不能用set，构造也必须private var或者val
//为了处理为 SimpleData<Student> 是SimpleData<Person>的子类
class SimpleData<out T>(private var data:T?){
    fun get():T?{
        return data
    }
    /*fun set(t:T?){
    }*/

}

/*
    public final class SimpleData<T> {
        private T data;
        public SimpleData(@Nullable Object data) {
            this.data = (T)data;
        }
        public final T get() {
            return this.data;
        }
    }
*/


class SimpleData2<out T>(val data:T?){
    fun get():T?{
        return data
    }
}
/*
    public final class SimpleData2<T> {
        @Nullable
        private final T data;

        public SimpleData2(@Nullable Object data) {
            this.data = (T)data;
        }

        @Nullable
        public final T getData() {
            return this.data;
        }

        @Nullable
        public final T get() {
            return this.data;
        }
    }
*/



// 逆变:变:假设有一个MyClass<T>,A是B子类型，同时MyClass<B> 是MyClass<A>的子类型，加上in则可通过下面方法传递. T 只能出现在in位置上
// 为了处理为 Comparable<Person> 是Comparable<Student> 的子类
interface Tras<in T>{
    fun tras(t:T):String
}
interface Tras2<T>{
    fun tras(t:T):String
}
open class Person1(val name:String,val age:Int)
class Student1(name:String,age: Int):Person1(name,age)
class Teacher(name:String,age: Int):Person1(name,age)


fun handleTrans(trans:Tras2<Student1>){

}



/**

inline fun <reified T> startActivity(context: Context) {
    val intent = Intent(context, T::class.java)
    context.startActivity(intent)
}

inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    block(intent)
    context.startActivity(intent)
}

 */