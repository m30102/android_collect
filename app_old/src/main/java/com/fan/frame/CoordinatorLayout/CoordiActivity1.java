package com.fan.frame.CoordinatorLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.android_fan.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
//https://www.jianshu.com/p/eec5a397ce79

/**
 * 在CoordinatorLayout内部，每个child都必须带一个Behavior（其实不携带也行，不携带就不能被协调） AppBarLayout默认有Behavior。
 *  携带后才有LinearLayout 垂直线性效果，不然只有类似FrameLayout叠加效果
 * 五种ScrollFlags https://www.jianshu.com/p/7caa5f4f49bd
 */
public class CoordiActivity1 extends AppCompatActivity {


    private List<String> mDatas;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordi1);
        // 让Activity布局显示在状态栏上面
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        // 状态栏变为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        initData();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);

        // 子View触发startNestedScroll-> parent:CoordinatorLayout.onStartNestedScroll->Behavior.onStartNestedScroll
    }

    private void initData() {

        mDatas = new ArrayList<String>();
        for (int i = '0'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyHolder>{


        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = new TextView(parent.getContext());
            return new MyHolder(textView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            ((TextView)holder.itemView).setText("position:"+position +" _ "+mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }


    class MyHolder extends RecyclerView.ViewHolder{

        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
