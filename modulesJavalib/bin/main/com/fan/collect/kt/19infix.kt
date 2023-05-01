package com.fan.collect.kt

fun aaa(){
}

// infix不能是顶层函数,必须是类的成员函数且只能接受一个参数
infix fun String.beginsWith(prefix: String) = startsWith(prefix)
/*
    public static final boolean beginsWith(@NotNull String $this$beginsWith, @NotNull String prefix) {
        return StringsKt.startsWith$default($this$beginsWith, prefix, false, 2, null);
    }
*/

infix fun <T> Collection<T>.has(element: T) = contains(element)

/*
    public static final <T> boolean has(@NotNull Collection $this$has, Object element) {
        return $this$has.contains(element);
    }
*/

// mapOf  A to b 相当于A.to(B)
infix fun <A, B> A.with(that: B): Pair<A, B> = Pair(this, that)
infix fun <A, B> A.to  (that: B): Pair<A, B> = Pair(this, that)

/*
    public static final <A, B> Pair<A, B> with(Object $this$with, Object that) {
        return new Pair($this$with, that);
    }
    public static final <A, B> Pair<A, B> to(Object $this$to, Object that) {
        return new Pair($this$to, that);
    }
*/

fun main() {
    if("aaabbb" beginsWith "aaa"){  // if (beginsWith("aaabbb", "aaa")
        println("yes")
    }else{
        println("no")
    }
    var list  = listOf(1,2,3,4)
    println(list has 3)
    mapOf("Apple" with  1, "Banana" to 2)
}
/*
    public static final void main() {
        if (beginsWith("aaabbb", "aaa")) {
            System.out
                    .println("yes");
        } else {
            System.out

                    .println("no");
        }
        Integer[] arrayOfInteger = new Integer[4];
        arrayOfInteger[0] = Integer.valueOf(1);
        arrayOfInteger[1] = Integer.valueOf(2);
        arrayOfInteger[2] = Integer.valueOf(3);
        arrayOfInteger[3] = Integer.valueOf(4);
        List<? extends Integer> list = CollectionsKt.listOf((Object[])arrayOfInteger);
        System.out
                .println(has(list, Integer.valueOf(3)));
        Pair[] arrayOfPair = new Pair[2];
        arrayOfPair[0] = with("Apple", Integer.valueOf(1));
        arrayOfPair[1] = to("Banana", Integer.valueOf(2));
        MapsKt.mapOf(arrayOfPair);
    }
*/
