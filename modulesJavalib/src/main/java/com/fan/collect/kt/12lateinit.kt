package com.fan.collect.kt
/*
public final class Test {
    public String s;

    @NotNull
    public final String getS() {
        String str = this.s;
        if (str != null)
            return str;
        Intrinsics.throwUninitializedPropertyAccessException("s");
        return null;
    }

    public final void setS(@NotNull String <set-?>) {
        Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
        this.s = <set-?>;
    }

    public final void func1() {
        setS("123");
    }

    public final void func2() {
        if (this.s == null)
            setS("234");
    }
}
*/

class Test{

    lateinit var s:String;

    fun func1(){
        s = "123";
    }
    fun func2(){
        if(!::s.isInitialized){
            s = "234";
        }
    }

}

fun main() {
    val test = Test()
    println(test.s)
    test.func2()
    test.func1()
    println(test.s)

    println(System.currentTimeMillis())
}