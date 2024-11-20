package com.fan.collect.kt
fun main() {
    val p = Person("fff",12);
    p.eat()
    val student1 = Student()
    val student2 = Student("Jack", 19)
    val student3 = Student("a123", 5, "Jack", 19)

    val d = Driver("haha",12)
}
/**
in person init...name:fff age:12
fff is eating. He is 12 years old

in person init...name: age:0
    in student init ...name: age:0 sno: grade:0
    in student constructor2 ...
    in student constructor0 ...
in person init...name:Jack age:19
    in student init ...name:Jack age:19 sno: grade:0
    in student constructor2 ...
in person init...name:Jack age:19
    in student init ...name:Jack age:19 sno:a123 grade:5
 */
// 加上open使其可以被继承
open class Person(val name: String, val age: Int) {
    // 主构造函数逻辑
    init {
        println("in person init...name:"+name+" age:"+age)
    }
    fun eat() {
        println(name + " is eating. He is " + age + " years old.")
    }
}
//  加上val或者var 会成为该类的字段,由于person类已经有了val修饰，所以student类不需要加
class Student(val sno: String, val grade: Int, name: String, age: Int) :
        Person(name, age) {
    // 主构造函数逻辑
    init {
        println(" in student init ...name:"+name+" age:"+age+" sno:"+sno+" grade:"+grade)
    }
    // 间接调用主构造函数
    constructor() : this("", 0) {
        println(" in student constructor0 ...")
    }
    // 直接调用主构造函数
    constructor(name: String, age: Int) : this("", 0, name, age){
        println(" in student constructor2 ...")
    }

}
class Student2(val sno: String = "", val grade: Int = 0, name: String = "", age: Int = 0) :
        Person(name, age) {
}

// Driver没有主构造函数，继承的时候不需要括号.
class Driver :Person{
    // 没有主构造，所以只能调用super
    constructor(name:String,age:Int):super(name,age){
        println(" in Driver constructor ...")
    }
}