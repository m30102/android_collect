package com.fan.collect.generic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;

/**
 *  泛型类要注意 的细节：
 *  1. 在类上自定义泛型的具体数据类型是在使用该类创建对象的时候指定的。
 *  2. 如果一个类已经自定义了泛型，在使用该类创建对象 的时候没有指定泛型的具体数据类型，那么默认的数据类型为Object.
 *  3. 静态方法不准使用类上自定义的泛型, 如果需要使用则要在方法上自定义,非静态方法则用的是类上定义的泛型
 */
public class Gclass<T> {
	
	public void print1(T t) {
		System.out.println("print1:" + t.toString());
	}
	
	
	public static <T> void print2(T t) {
		System.out.println("print2:" + t.toString());
	}


	
	public static void main(String[] args) {
		Gclass<Double> gclass = new Gclass<Double>();
		gclass.print1(2.04d);// print1:2.04
		print2(new Date());// print2:Tue Aug 21 14:58:35 CST 2018
		Gclass.print2(System.currentTimeMillis());// print2:1534834832840
//		Gclass<String>.print2("");// 无法通过此种方式调用静态方法


		List<Integer> integers = new ArrayList<>();
		List<Number> numbers = new ArrayList<>();
		System.out.println(integers.getClass()+" "+numbers.getClass());// java.util.ArrayList class java.util.ArrayList
		bbb(numbers);
//		bbb(integers);// 报错，可以改为 List<? extends Number> numbers
//		bbbex(integers);//OK
//		bbbex(numbers);//ok


		// 泛型擦除
		integers.add(1);
		List str = integers;
		str.add("111");
		System.out.println(str);
	}

	public static void bbb(List<Number> numbers){

	}
	public static void bbbex(List<? extends Number> numbers){
//		numbers.add(new Integer(1));// 子类多样性，所以不让存. 存会破坏元素同一性.   能取得最大的为Number
	}
	public static void bbbbsu(List<? super Number> numbers){
//		numbers.add(new Integer(1));// 能存，取都为object
//		numbers.add(new Float(1));// 能存，取都为object
//		numbers.add(new Object());// 能存的最大为Number
	}

	public static <T extends Number> void exP(T a){

	}
	/*public static <T super Integer> void suP(T a){

	}*/
}
