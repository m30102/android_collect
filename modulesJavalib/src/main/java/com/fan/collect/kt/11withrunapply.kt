package com.fan.collect.kt


fun main() {
//    withTest()
//    runTest()
    applyTest()
}




fun applyTest() {
    // apply函数不能指定返回值,只能返回调用对象本身
    val list = listOf("apple", "Banna", "Orange", "Pear", "Grape")
    val apply = StringBuilder().apply {
        append("Start eating fruits.\n")
        list.forEach {
            append(it).append("\n");
        }
        append("Ate all fruits.")
    }

    println(apply.toString())
}


fun runTest(){

    val list = listOf("apple", "Banna", "Orange", "Pear", "Grape")
    val result = StringBuffer().run {
        append("Start eating fruits.\n")
        list.forEach {
            append(it).append("\n");
        }
        append("Ate all fruits.")
        toString()// 最后一行作为返回值
    }
    println(result)
}
private fun withTest() {
    val list = listOf("apple", "Banna", "Orange", "Pear", "Grape")
    val builder = StringBuilder();
    builder.append("Start eating fruits.\n")
    list.forEach {
        builder.append(it).append("\n");
    }
    builder.append("Ate all fruits.")
    println(builder.toString())

    /**
     * val result = with(obj){
     *  // obj的上下文
     *  "value"// with函数的返回值
     * }
     */

    val result = with(StringBuffer()) {
        append("Start eating fruits.\n")
        list.forEach {
            append(it).append("\n");
        }
        append("Ate all fruits.")
        toString()// 最后一行作为返回值
    }

    println(result)
}


