package com.fan.collect.kt

data class Cellphone(var brand: String, val price: Double)

class DemoClass{
    var age:Int = 12
        get() = field +1
        set(value) {
            field= value
        }
    var aaa:Int = 3
        private set
}



fun main() {
    var c  = Cellphone("aa",1.1);
    println(c.brand)
    c.brand = "bb";
    println(c.brand)
    println(c.price)
    println(c)

    var d = Cellphone("aa",1.1)
    println(c == d)//fasle
    println(c.equals(d))//false
    Singleton.test()
    val demoClass = DemoClass()
    println(demoClass.age)
    demoClass.age = 13
//    demoClass.aaa = 44
    println(demoClass.age)
}
// 单例
object Singleton{
    fun  test(){
        println("called")
    }
}
/*
    public final class Singleton {
        @NotNull
        public static final Singleton INSTANCE = new Singleton();

        public final void test() {
            System.out.println("called");
        }
    }
*/
