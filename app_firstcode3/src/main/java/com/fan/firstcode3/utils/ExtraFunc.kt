package com.fan.firstcode3.utils

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.contentValuesOf
import androidx.core.content.edit

// sp自定义扩展函数, 第7章高阶函数
fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.block()
    editor.apply()
}
//  sp自定义扩展函数在androidx.core:core-ktx:xx 已经有了
fun testKtxSp(context:Context){
    context.getSharedPreferences("data",Context.MODE_PRIVATE).edit {
        putString("name","jack")
        putInt("age",14)
    }
}

fun testCvOf(){
    // 自定义cv扩展函数
    val values1 = cvOf("name" to "Game of Thrones", "author" to "George Martin",
        "pages" to 720, "price" to 20.85)
    // 自定义cv扩展函数在androidx.core:core-ktx:xx 已经有了
    val values2 = contentValuesOf("name" to "Game of Thrones", "author" to "George Martin",
        "pages" to 720, "price" to 20.85)
}

fun cvOf(vararg pairs: Pair<String, Any?>): ContentValues {
    val cv = ContentValues()
    for (pair in pairs) {
        val key = pair.first
        val value = pair.second
        when (value) {
            is Int -> cv.put(key, value)
            is Long -> cv.put(key, value)
            is Short -> cv.put(key, value)
            is Float -> cv.put(key, value)
            is Double -> cv.put(key, value)
            is Boolean -> cv.put(key, value)
            is String -> cv.put(key, value)
            is Byte -> cv.put(key, value)
            is ByteArray -> cv.put(key, value)
            null -> cv.putNull(key)
        }
    }
    return cv
}
