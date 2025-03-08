package com.fan.frame.recycle.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.android_fan.R;
import com.fan.frame.utils.FLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//https://blog.csdn.net/lmj623565791/article/details/45059587


// 图片错位https://blog.csdn.net/xyq046463/article/details/51800095?locationNum=3
public class RecycleBaseActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Looper.prepare();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_recyclerview);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//线性
        HomeAdapter homeAdapter = new HomeAdapter();

        EditText edit = findViewById(R.id.pos);
        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos =Integer.parseInt(edit.getText().toString());
                mDatas.remove(pos);
                homeAdapter.notifyItemRemoved(pos+1);
                homeAdapter.notifyItemRangeChanged(pos+1,mDatas.size());
                FLogger.d(mDatas.toString());
            }
        });
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));//网格
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));// 瀑布流
        homeAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecycleBaseActivity.this,((TextView)view).getText(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        mRecyclerView.setAdapter(homeAdapter);
//        mAdapter.notifyItemInserted();
//        mAdapter.notifyItemRemoved();

    }

    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'G'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }


    interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {


        private OnItemClickLitener mOnItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
        {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {

            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    RecycleBaseActivity.this).inflate(R.layout.activity_base_recycle_item, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.tv.setText("position:"+position +" _ "+mDatas.get(position));
            if(mOnItemClickLitener != null){
                holder.tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickLitener.onItemClick(holder.tv,position);
                    }
                });
            }

        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
}
