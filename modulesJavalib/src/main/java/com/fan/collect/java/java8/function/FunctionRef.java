package com.fan.collect.java.java8.function;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
        静态方法引用	类名::staticMethod	(args) -> 类名.staticMethod(args)
        实例方法引用	inst::instMethod	(args) -> inst.instMethod(args)
        对象方法引用	类名::instMethod	(inst,args) -> 类名.instMethod(args)
        构建方法引用	类名::new	(args) -> new 类名(args)
 */
public class FunctionRef {


    public static void main(String[] args) {

        Function<Integer,Integer> f = x-> x + 1;
        Function<Integer,Integer> g = x-> x * 2;
//        f.andThen(g)//g(f(x))
//        f.compose(g)//f(g(x))
        System.out.println(f.andThen(g).apply(1));//4
        System.out.println(f.andThen(g).apply(2));//6
        System.out.println(f.andThen(g).apply(3));//8
        System.out.println(f.compose(g).apply(1));//3
        System.out.println(f.compose(g).apply(2));//5
        System.out.println(f.compose(g).apply(3));//7


        Function<Integer, Integer> identity = Function.identity();
        Integer apply = identity.apply(1);
        System.out.println(apply);

        Map<String, String> collect = Arrays.asList("a", "b", "c")
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(), // <-- And this,
                        str -> str));// <-- is the same as this.
        System.out.println(collect.toString());// {a=a, b=b, c=c}
        Map<String, String> collect2 = Arrays.asList("a", "b", "c")
                .stream()
                .collect(Collectors.toMap(
                        str -> "name:"+str, // <-- And this,
                        str -> "value:"+str));// <-- is the same as this.
        System.out.println(collect2.toString());// {name:c=value:c, name:b=value:b, name:a=value:a}

    }


}
 class Person {

    public String name;

     public Integer age;

    public static int compareByAge(Person a, Person b) {
        return a.age.compareTo(b.age);
    }

     /*   Person[] rosterAsArray = new Person[30];
        Arrays.sort(rosterAsArray, (a, b) -> a.age.compareTo(b.age));
        Arrays.sort(rosterAsArray, Comparator.comparing(a -> a.age));
        Arrays.sort(rosterAsArray,Person::compareByAge);*/
}
