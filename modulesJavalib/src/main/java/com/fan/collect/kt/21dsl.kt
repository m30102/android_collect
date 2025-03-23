package com.fan.collect.kt

class Dependency {

    val libraries = ArrayList<String>()

    fun implementation(lib: String) {
        libraries.add(lib)
    }

}
fun dependencies(block: Dependency.() -> Unit): List<String> {
    val dependency = Dependency()
    dependency.block()
    return dependency.libraries
}
fun main() {
    test1()
}
private fun test1() {
    val libraries = dependencies {
        implementation("com.squareup.retrofit2:retrofit:2.6.1")
        implementation("com.squareup.retrofit2:converter-gson:2.6.1")
    }
    for (lib in libraries) {
        println(lib)
    }
    val libraries2 = dependencies {
        implementation ("aa.bb.cc:2.6.1")
        implementation("qq.ww.ee:2.6.1")
    }
    for (lib in libraries2) {
        println(lib)
    }
}