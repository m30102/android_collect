package com.fan.collect.kt
import kotlin.jvm.internal.Intrinsics
import kotlin.reflect.KProperty

// 委托类  将MySet1的实现委托给HashSet去完成
class MySet1<T>(val helpSet:HashSet<T>) :Set<T> by helpSet{
    fun hello ()= println("asd")
    override fun isEmpty() = false
}
/*
public final class MySet1<T> implements Set<T>, KMappedMarker {
    private final HashSet<T> helpSet;
    public MySet1(@NotNull HashSet<T> helpSet) {
        this.helpSet = helpSet;
    }
    public final HashSet<T> getHelpSet() {
        return this.helpSet;
    }
    public final void hello() {
        System.out.println("asd");
    }
    public boolean isEmpty() {
        return false;
    }
    public int getSize() {
        return this.helpSet.size();
    }
*/


// 委托属性  将属性的具体实现给另一个类完成
class MyClassMenber{
    var p by Delegate()
}
class Delegate{

    var propValue:Any? = 111
    // 委托功能只能在MyClassMenber类中使用
    operator  fun getValue(myClass: MyClassMenber,p:KProperty<*>):Any?{
        return propValue
    }
    // 若MyClassMenber中的p属性声明为val 则可以不用实现setValue
    operator  fun setValue(myClass: MyClassMenber,p:KProperty<*>,value:Any?){
        propValue = value
    }
}

/*
    public final class Delegate {
        @Nullable
        private Object propValue = Integer.valueOf(111);
        @Nullable
        public final Object getPropValue() {
            return this.propValue;
        }
        public final void setPropValue(@Nullable Object <set-?>) {
            this.propValue = <set-?>;
        }
        @Nullable
        public final Object getValue(@NotNull MyClassMenber myClass, @NotNull KProperty p) {
            return this.propValue;
        }

        public final void setValue(@NotNull MyClassMenber myClass, @NotNull KProperty p, @Nullable Object value) {
            this.propValue = value;
        }
    }
*/




fun main() {
    val hs= HashSet<String>()
    val a = MySet1(hs)
    hs.add("ads")
    hs.contains("")

    println(MyClassMenber().p)
    var d = D()
    println(d.p.get(0))// in lazy string的第0个
    println(d.q)
    println(d.q)

}

class D{
    val p by lazy{
        val a = "in lazy string";
        a
    }
    val q by later {
        println("in later lambda")// 一次
        var a :String = "ads";
        a
    }
}


class Later<T>(val block: () -> T) {

    var value: Any? = null

    operator fun getValue(any: Any?, prop: KProperty<*>): T {
        if (value == null) {
            println("value == null")
            value = block()
        }
        println("return value") // 每次都会
        return value as T
    }

}

fun <T> later(block: () -> T) = Later(block)