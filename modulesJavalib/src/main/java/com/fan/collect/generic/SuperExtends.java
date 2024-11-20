package com.fan.collect.generic;

import java.util.ArrayList;
import java.util.List;


/**
 * PECS 生产 <? extends T> 上界通配符 读取类型T的数据，并且不能写入 消费 <? Super T> 下届通配符 写入类型T的数据，并且不需要读取
 * 如果既要存又要取，那么就不要使用任何通配符。 https://www.zhihu.com/question/20400700
 * 
 * 类型限定，能存入限定类型及其的子类，但取出来则可用限定类型或者限定类型的超类标识.
 * 
 * 类型限定越高，等存入的子类越多.
 * 
 * List<T> list = new ArrayList<>();// 实际类型限定为T , 能存T或者T的子类, 取出来可用T或者T的超类表示。
 * List<? extends T> list= new ArrayList<>(); // 实际类型限定为T的子类 , 具体什么子类不知道,但取出来可用T或者T的超类表示。 所以能取不能存
 * List<? super T> list = new ArrayList<>();//实际类型限定为T或者T的超类 , 具体什么超类不知道，但存T或者T的子类。 所以能存不能取,取都是object
 * 
 * 不管是extends super 还是不用。容器实际拥有或者可添加的一定是T或者T的子类
 * 
 */
public class SuperExtends {

	class Food {
	}

	class Fruit extends Food {

	}
	class Meat extends Food{

	}

	class Apple extends Fruit {

	}
	class Banana extends Fruit{

	}

	class Plate<T> {

		private T item;

		public Plate() {

		}

		public void set(T t) {
			item = t;
		}

		public T get() {
			return item;
		}

	}

	/**
	 * <? extends Fruit> 里面能取出的元素类型都是Fruit, 由于子类多样不确定性，所以只让取不让存. <? super Fruit>
	 * 里面能存入的最大类型为Fruit(不能为object),
	 */
	public void test3() {
		// 上界 extends
//		 Plate<Apple> plate1 = new Plate<Apple>();// yes
//		 Plate<Fruit> plate2 = new Plate<Apple>();// error

		Plate<? extends Fruit> plate3 = new Plate<>();// yes
		Plate<? extends Fruit> plate4 = new Plate<Apple>();// yes 等同于上面. 编译后就是 new Plate<>();
		Plate<? extends Fruit> plate5 = new Plate<Banana>();// yes 等同于上面
		plate4 = plate5;

		// 以左边变量类型为准, 由于继承的分支可能很多，插入可能会被破坏，所以不能存入任何元素,但可以存入null
//		 plate3.set(new Fruit()); // Error
//		 plate3.set(new Apple()); // Error

		// 因为有了统一的根Fruit, 所以读取出来的东西只能存放在Fruit或它的基类里。
		 Fruit newFruit1=plate3.get();// yes.
		Object newFruit2=plate3.get();// yes.
//		 Apple newFruit3 = plate3.get(); //Error 只知道容器内是Fruit或者它的派生类，但具体是什么类型不知道,
		// 所以这样编译不过

		// 下界 super ,限定右边实际类型最小为Fruit。但只能存Fruit及子类.
		// 右边虽然知道是Fruit的基类 但也不知道是往上数多少级的基类
		Plate<? super Fruit> p1 = new Plate<Fruit>();// yes 编译后new Plate<>()
		Plate<? super Fruit> p = new Plate<>();// yes 编译后new Plate<>()
		Plate<? super Fruit> pf = new Plate<Food>();// yes 编译后new Plate<>()
//		 Plate<? super Fruit> p2 = new Plate<Apple>(); // error
		p.set(new Fruit());// yes
		p.set(new Apple());// yes


//		 p.set(new Object());
//		 p.set(new Food());// error
		// 不用通配符则可以传入子类
		Plate<Fruit> plate = new Plate<>();
		plate.set(new Apple());
		plate.set(new Fruit());

		Object object = p.get();// 直接是object
		System.out.println(object);
		// 存入元素正常. 因为需要存入的元素的基类至少是Fruit(多态), 所以任何Friut及其子类都可以存入

		// 只知道是Fruit的超类，但不知道具体是哪个，所以读取出来的东西只能存放在Object类里，无法使用. 所以只能 插入操作。
		// Apple newFruit1=p.get(); //Error
		// Fruit newFruit2 =p.get(); //Error
		// Object newFruit3 = p.get();

	}

	public static void main(String[] args) {
		new SuperExtends().test();
	}
	
	//PECS

	void test() {
		List<Mammal> list = new ArrayList<>();
		list.add(new Cat());
		list.add(new Dog());
		list.add(new Mammal());
//		list.add(new Animal());// error
//		list.add(new Fish());// error
		
		List<? extends Animal> list2 = new ArrayList<>();
//		list2.add(new Animal());// error
//		list2.add(new Dog());// error
//		list2.add(new Fish());// error
//		list2.add(null);// ok ,但无意义
		
		Animal animal = list2.get(0);//ok  但上面说了不存入,get的数据从何而来？
		
		
		
		
		List<? super Animal> list3 = new ArrayList<>();
		List<Animal> a = new ArrayList<>();// 能存Animal及其子类
		List<Life> b = new ArrayList<>();// 能存Life及其子类
		List<Object> c = new ArrayList<>();// 能存Object及其子类
//		list3.add(new Object());//但具体是上面情况的哪一种不知道, 如果限定为Animal或者Life,是肯定不能存object的.所以报错,  但无论是上面那种情况肯定能存Animal及其子类. 
		list3.add(new Fish());
		list3.add(new Animal());
		list3.add(new Mammal());
		list3.add(new Bird());
		list3.add(new Dog());
		
//		feed(list3);// error
		feed2(list3);// 可以但, 不合理
		
		// 如何合理使用 List<? super Mammal> ?  . 明确构造泛型类型在Mammal及其之上的集合.
		
		
		List<Mammal> list4 = new ArrayList<>();
		list4.add(new Dog());
		
		leaveHome(list4);
		play(list4);
		feed(list4);
		
		
		ArrayList<Animal> list5 = new ArrayList<>();
		list5.add(new Animal());
		list5.add(new Fish());
		leaveHome(list5);
//		play(list5);// 无法奔跑玩耍
		feed(list5);// 可以喂食
		
		
		List<Life> arrayList = new ArrayList<>();
		feed2(arrayList);// ok
		
	}
	// 喂食
	public void feed(List<? extends Animal> list3) {
		for(int i=0; i<list3.size();i++) {
			list3.get(i).eat();
		}
	}
	// 
	public void feed2(List<? super Animal> list) {
		for(int i=0; i<list.size();i++) {
			Object object = list.get(i);
			//只知道限定符为Animal以上，但不知道具体类型, 所以取出来为Object.
		}
	}
	
	// 奔跑玩耍    生产 (取出可使用的数据)
	public void play(List<? extends Mammal> list) {
		for(int i=0; i<list.size();i++) {
			list.get(i).run();
		}
	}
	
	//  消费(插入可使用的数据)
	public void leaveHome(List<? super Mammal> list) {
		 list.add(new Cat()); // yes 捡到一只猫.  
	}
	


	class Life {}
	
	class Animal extends Life{
		void eat() {}
	}

	class Mammal extends Animal {
		void run() {}
	}

	class Dog extends Mammal {}

	class Cat extends Mammal {}

	class Bird extends Animal {
		void flying() {};
	}
	class Fish extends Animal {
		void swimning() {};
	}
}
