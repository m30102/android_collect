package com.fan.collect.study.coroutines

import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.annotation.StringRes
import com.fan.collect.base.BaseVBActivity
import com.fan.collect.base.utils.LogHelper
import com.fan.collect.databinding.ActivityCoroutinesDemoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutinesActivity: BaseVBActivity<ActivityCoroutinesDemoBinding>() {
    override fun initData() {

        testXc0()
//        testXc1()
    }

    override fun initView() {
        Log.e(TAG,"etCorEditClass "+binding.etCor.javaClass.name)
        binding.etCor.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                LogHelper.d(TAG, "beforeTextChanged:$s");
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                LogHelper.d(TAG, "onTextChanged:$s");
            }

            override fun afterTextChanged(s: Editable?) {
                LogHelper.d(TAG, "onTextChanged:"+s.toString());
            }

        })
    }

    override fun getViewBinding(): ActivityCoroutinesDemoBinding {
        return ActivityCoroutinesDemoBinding.inflate(layoutInflater)
    }

    fun testXc0(){
        LogHelper.d(TAG,"Main threadId1:"+Looper.getMainLooper().thread.id)//2
        GlobalScope.launch(Dispatchers.Main) {
            // 如果是Dispatchers.Main，那么会2,176,2. 如果不加Main，默认Default，那么可能三个值都相同
            LogHelper.d(TAG,"threadId1:"+Thread.currentThread().id)//2
            val result = withContext(Dispatchers.IO){
                LogHelper.d(TAG,"threadId2:"+Thread.currentThread().id)//176
                "qwe" + "123"
            }
            LogHelper.d(TAG,"result:$result threadId:"+Thread.currentThread().id)// result:qwe123 threadId:2
        }
        LogHelper.d(TAG,"Main threadId2:"+Looper.getMainLooper().thread.id)//2
    }
    data class User(var name:String,var age:Int)


    fun testXc1(){
        GlobalScope.launch(Dispatchers.Main){
            getUser()
        }
    }
    private suspend fun getUser(){
        val user = get()
        show(user)
    }

    private fun show(user:User){
        Log.e(TAG, "user:$user")
    }

    private suspend fun get() = withContext(Dispatchers.IO){
        User("haha",12)
    }

}