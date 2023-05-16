package com.fan.collect

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.annotation.DimenRes
import androidx.fragment.app.DialogFragment
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ScreenUtils
import com.fan.collect.base.constance.NaviConst
import com.fan.collect.databinding.ActivityMainBinding

// 底部导航BottomNavigationView

class LaunchActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    // https://www.jianshu.com/p/e02cd88ae062
    private fun modifyDensity() {
        val displayMetrics = application.resources.displayMetrics
        val density = displayMetrics.density
        val scaledDensity = displayMetrics.scaledDensity
        Log.e("TestActivity", "density:$density")
        Log.e("TestActivity", "scaledDensity:$scaledDensity")
        displayMetrics.density = 2.5f
        displayMetrics.scaledDensity = 2.5f
        val displayMetrics2 = application.resources.displayMetrics
        Log.e("TestActivity", "density2:"+displayMetrics2.density)
        Log.e("TestActivity", "scaledDensity2:"+displayMetrics2.scaledDensity)


    }

    fun resDp2px(@DimenRes dpId: Int): Int {
        return resources.getDimensionPixelSize(dpId)
    }


    fun getDimenResIdByValue(dp: Int): Int {
        val name = "dp_$dp"
        return resources.getIdentifier(name, "dimen", packageName)
    }

    fun adapt(dp: Int): Int {
        val dimenResIdByValue = getDimenResIdByValue(dp)
        val i = resDp2px(dimenResIdByValue)
        val a = (i / resources.displayMetrics.density).toInt()
        return a
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tvAs = binding.tvAs


        val displayMetrics = application.resources.displayMetrics
        displayMetrics.density = 3f

        val screenDensity = ScreenUtils.getScreenDensity()
        val screenWidth = ScreenUtils.getScreenWidth()
        val screenHeight = ScreenUtils.getScreenHeight()
        val appScreenWidth = ScreenUtils.getAppScreenWidth()
        val appScreenHeight = ScreenUtils.getAppScreenHeight()
        val screenDensityDpi = ScreenUtils.getScreenDensityDpi()
        val screenXDpi = ScreenUtils.getScreenXDpi();
        val screenYDpi = ScreenUtils.getScreenYDpi();
        val i: Float = resources.getDimension(R.dimen.dp_330)
        val a = (i / resources.displayMetrics.density).toDouble()
        val adapt = adapt(330);
        val p =  "screenDensity:"+screenDensity+" screenDensityDpi:"+
                screenDensityDpi+"\nscreenWidth:"+screenWidth+" screenHeight:"+screenHeight+"\nappScreenWidth:"+appScreenWidth+
                " appScreenHeight:"+appScreenHeight+
                "\n px330:"+i+" a:"+a+
                "\n adapt:"+adapt
        tvAs.text = p



        tvAs.setOnClickListener {
            ARouter.getInstance().build(NaviConst.ACTIVITY_MAIN)
                    .navigation()
        }


        Log.e("LaunchActivityTAG","onCreate onCreate onCreate");
    }

    override fun onResume() {
        super.onResume()
        Log.e("LaunchActivityTAG","onResume onResume onResume");
    }

    override fun onStop() {
        super.onStop()
        Log.e("LaunchActivityTAG","onstop onstop onstop");

    }

    override fun onDestroy() {
        Log.e("LaunchActivityTAG","onDestroyonDestroyonDestroy");
        super.onDestroy()
    }
}