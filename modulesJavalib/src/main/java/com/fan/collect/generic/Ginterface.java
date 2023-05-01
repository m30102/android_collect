package com.fan.collect.generic;

/**
 * 泛型接口要注意的细节：
 *  1. 泛型接口上的自定义泛型的具体数据类型是在实现该类的时候确定。 
 *  2. 泛型接口上自定义的泛型如果在实现接口的时候没有指定具体的数据类型，那么默认为Object数据类型。
 *  需求： 泛型接口上的自定义泛型实在实现该接口的时候指定
 *  的，目前我想延迟指定泛型接口上的自定义泛型的具体数据类型，想在创建接口实现类对象的时候再指定。
 *  格式：
 *  class 类名<T> implements 接口名<T>{ 
 *
 *  }
 */
public class Ginterface {

	interface Be<T> {
		void say(T t);
	}
	
	static class C implements Be{
		
		@Override
		public void say(Object t) {
			// TODO Auto-generated method stub
		}
		
	}
	static class D<T> implements Be<T> {

		@Override
		public void say(T t) {
			// TODO Auto-generated method stub
		}
	}
	
	public static void main(String[] args) {
		C c = new C();
		D<String> d = new D<String>();
		
		
	}
}
