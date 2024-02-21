package com.fan.collect.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseVBActivity<VB : ViewBinding>() : AppCompatActivity() {
    val TAG:String = javaClass.simpleName + "TAG"

    protected lateinit var binding:VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBeforeSetContentView()
        binding = getViewBinding()
        setContentView(binding.root)
        initActivity()
    }

    open fun initBeforeSetContentView(){

    }

    protected abstract fun initData()

    protected abstract fun initView()

    protected abstract fun  getViewBinding():VB


    private fun initActivity(){
        initSoftKeyboard()
        initView()
        initData()
    }

    override fun finish() {
        super.finish()
        hideKeyboard(currentFocus)
    }

    protected open fun initSoftKeyboard() {
        // 点击外部隐藏软键盘，提升用户体验
        findViewById<View>(Window.ID_ANDROID_CONTENT)?.setOnClickListener {
            // 隐藏软键，避免内存泄漏
            hideKeyboard(currentFocus)
        }
    }

    /**
     * 隐藏软键盘
     */
    fun hideKeyboard(view: View?) {
        if (view == null) {
            return
        }
        val manager: InputMethodManager = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager? ?: return
        manager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

}