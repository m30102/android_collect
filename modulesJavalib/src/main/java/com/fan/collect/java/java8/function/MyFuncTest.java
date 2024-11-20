package com.fan.collect.java.java8.function;

import java.util.Objects;

public class MyFuncTest {

	public static void main(String[] args) {

		// 输入类型为string ,返回类型为integer
		MyFunction<String, Integer> intvalue = Integer::valueOf;
		// 输入类型为integer , 返回类型为string
		MyFunction<Integer, String> stringvalue = String::valueOf;
		// 整合 输入类型为string , 返回类型为string
		MyFunction<String, String> me1 = intvalue.andThen(stringvalue);
		// 整合 输入类型为string , 返回类型为boolean
		MyFunction<String, Boolean> me2 = intvalue.andThen(MyFuncTest::bValue);

		System.out.println(me1.apply("123"));
		System.out.println(me2.apply("-123"));

		MyFunction<Integer, Integer> times2 = i -> i * 2;
		MyFunction<Integer, Integer> squared = i -> i * i;

		System.out.println(times2.apply(4));
		System.out.println(squared.apply(4));

		System.out.println(times2.compose(squared).apply(4)); // 32 先4×4然后16×2
		System.out.println(times2.andThen(squared).apply(4)); // 64 先4×2,然后8×8
		
		System.out.println(MyFunction.identity().apply("asd")); // asd
		System.out.println(MyFunction.identity().apply(123));// 123
		MyFunction<String, String> identity1 = MyFunction.identity();
		identity1.apply("123");//只能是string
		MyFunction<Integer, Integer> identity2 = MyFunction.identity();
		identity2.apply(4);// 只能是Integer
	}

	// 输入类型为integer , 返回类型为boolean
	public static boolean bValue(int i) {
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	@FunctionalInterface
	public interface MyFunction<T, R> {
		
		R apply(T t);
		
		default <V> MyFunction<V, R> compose(MyFunction<? super V, ? extends T> before) {
			Objects.requireNonNull(before);
			return (V v) -> apply(before.apply(v));
		}

		default <V> MyFunction<T, V> andThen(MyFunction<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			// 参数 -> 表达式 的写法代表一个方法.(也可以看做一个实现了单抽象方法的接口).然后通过接口变量名.方法名 调用
			MyFunction<T, R> functemp = t -> apply(t);// 生成一个实现 MyFunction<T, R>接口apply方法 的匿名类对象. 输入参数为t ,返回值类型为R
			MyFunction<T, V> funcFinal = t -> after.apply(functemp.apply(t));// 先传入参数,用上面这个匿名类对象的方法求值,求值结果为after匿名对象的参数(R类型).
//			return funcFinal;																	// 生成一个最终的匿名对象
																				// String.valueOf(Integer.valueOf(i));

			// funcFinal 等同于下面 funcFinal2 写法
			MyFunction<T, V> funcFinal2 = (T t) -> {
				R r = apply(t);
				V v = after.apply(r);
				return v;
			};

			return funcFinal2;
			// 上面三句等同于下面
			// return (T t) -> after.apply(apply(t));// 去掉参数类型也行 这里是具体实现 必须要return。
			// 外部实现interface时才可以不用return
		}

		//

		// 返回一个执行了apply()方法之后只会返回输入参数的函数对象
		static <T> MyFunction<T, T> identity() {
//			return t -> t; // 等于下面这种写法.
//			 MyFunction<String, String> identity1 = MyFunction.identity();
			//如果最左边声明为 MyFunction<String, String> 那么apply函数原型为 String apply(String t);
			//如果最左边声明为 MyFunction<Integer, Integer> 那么apply函数原型为 Integer apply(Integer t);
			return t -> { return t ;};
		}
		

	}
	
	
	
	
	// Ide<String,Integer> ide = t -> t; //error
	// Ide ide = t -> t;//yes
	// System.out.println(i.get(12));// 12
	// System.out.println(i.get("vvvv"));// vvvv
	@FunctionalInterface
	interface Ide<I,O>{
		 I get(I i);
	}
	
	static <T> Ide<T,T> getOri(){
		Ide ide = t -> t;
		return ide;
	}
	static <T> Ide<T,T> getOri2(){
		return t -> t;
	}
	
	void testide() {
		Ide<String, String> ori1 = getOri();
		Ide<Integer, Integer> or2 = getOri();
		Ide<String, String> ori3 = getOri2();
		Ide<Integer, Integer> or4 = getOri2();
	}
	

}
