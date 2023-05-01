package com.fan.collect.kt
/*
    public final class Money {
        private final int value;

        public Money(int value) {
            this.value = value;
        }

        public final int getValue() {
            return this.value;
        }

        @NotNull
        public final Money plus(@NotNull Money money) {
            Intrinsics.checkNotNullParameter(money, "money");
            int sum = this.value + money.value;
            return new Money(sum);
        }

        @NotNull
        public final Money plus(int newValue) {
            int sum = this.value + newValue;
            return new Money(sum);
        }
    }
*/
// https://www.jianshu.com/p/190cbdb4f880
// <out T>、<in T>分别指代<? extends T>及<? super T>，简单通俗的讲，<out T>只能作为返回值，返回T或其子类，而<in T>可以作为参数传递，传递T及其子类。
class Money(val value: Int) {

    operator fun plus(money: Money): Money {
        val sum = value + money.value
        return Money(sum)
    }
    operator fun plus(newValue: Int): Money {
        val sum = value + newValue
        return Money(sum)
    }
    operator fun invoke() {
        println("$value...")
    }
}



fun main() {
    val m1 = Money(12);
    m1()
    val m2 = Money(1);
    println((m1+m2).value)//System.out.println(m1.plus(m2).getValue());
    println((m1+2).value)
    println("asd".repeat(2))
    println("asd" * 3)
}

//plus times 运算符对应的函数名必须对
/*operator fun String.times(n: Int):String{
    val builder = StringBuilder();
    repeat(n){
        builder.append(this)
    }

    return builder.toString()
}*/
operator fun String.times(n: Int) = repeat(n)

/*

public static final String times(@NotNull String $this$times, int n) {
    Intrinsics.checkNotNullParameter($this$times, "<this>");
    return StringsKt.repeat($this$times, n);
}

*/
