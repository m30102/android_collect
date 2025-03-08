package com.fan.frame.viewpager2;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.administrator.android_fan.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class ViewPager2Activity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private TabLayoutMediator tabLayoutMediator;
    private TabLayout tabLayout;
    // https://www.jianshu.com/p/27ef1428ab8a

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager2);
        viewPager2 = findViewById(R.id.viewpager2);
        tabLayout = findViewById(R.id.tablayout);


        final String[] tabs = new String[]{"关注", "推荐", "最新"};
        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return BlankFragment.getInstances("fragment:"+position);
            }

            @Override
            public int getItemCount() {
                return tabs.length;
            }
        });
        viewPager2.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);// 预加载


        viewPager2.registerOnPageChangeCallback(changeCallback);
        tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            //这里可以自定义TabView
            TextView tabView = new TextView(ViewPager2Activity.this);

            int[][] states = new int[2][];
            states[0] = new int[]{android.R.attr.state_selected};
            states[1] = new int[]{};
            int[] colors = new int[]{ContextCompat.getColor(ViewPager2Activity.this, android.R.color.holo_red_dark), ContextCompat.getColor(ViewPager2Activity.this, android.R.color.darker_gray)};
            ColorStateList colorStateList = new ColorStateList(states, colors);
            tabView.setText(tabs[position]);
            tabView.setTextSize(16);
            tabView.setGravity(Gravity.CENTER);
            tabView.setTextColor(colorStateList);
            tab.setCustomView(tabView);
        });
        tabLayoutMediator.attach();
    }
    ViewPager2.OnPageChangeCallback changeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            //可以来设置选中时tab的大小
            int tabCount = tabLayout.getTabCount();
            for (int i = 0; i < tabCount; i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                TextView tabView = (TextView) tab.getCustomView();
                if (tab.getPosition() == position) {
                    tabView.setTextSize(22);
                    tabView.setTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    tabView.setTextSize(22);
                    tabView.setTypeface(Typeface.DEFAULT);
                }
            }
        }
    };
    @Override
    protected void onDestroy() {
        tabLayoutMediator.detach();
        viewPager2.unregisterOnPageChangeCallback(changeCallback);
        super.onDestroy();

    }
}
