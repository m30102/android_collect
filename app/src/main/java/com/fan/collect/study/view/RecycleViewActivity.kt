package com.fan.collect.study.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fan.collect.R
import com.fan.collect.base.BaseVBActivity
import com.fan.collect.databinding.ActivityRecycleBaseBinding

/**
 * //  https://blog.csdn.net/lmj623565791/article/details/45059587
 * // 图片错位https://blog.csdn.net/xyq046463/article/details/51800095?locationNum=3
 */
class RecycleViewActivity:BaseVBActivity<ActivityRecycleBaseBinding>() {

    override fun initData() {
        var i = 'A'.code
        while (i < 'G'.code) {
            mDatas.add("" + i.toChar())
            i++
        }
    }
    val mDatas = mutableListOf<String>()

    override fun initView() {
        binding.rv.layoutManager = LinearLayoutManager(this)


        val onItemClickLitener = object :OnItemClickLitener{
            override fun onItemClick(view: View?, position: Int) {
                Log.e(TAG," text:"+ (view as TextView).text +" position:"+position)
            }

            override fun onItemLongClick(view: View?, position: Int) {
            }

        }
        val adapter = HomeAdapter(onItemClickLitener,mDatas)
        binding.rv.adapter = adapter

        binding.btnDelete.setOnClickListener {
            val pos = binding.etPos.text.toString().toInt()
            mDatas.removeAt(pos)
            adapter.notifyItemRemoved(pos )
//            adapter.notifyItemRangeChanged(pos + 1, mDatas.size)
        }
    }

    interface OnItemClickLitener {
        fun onItemClick(view: View?, position: Int)
        fun onItemLongClick(view: View?, position: Int)
    }

    class HomeAdapter(private val onItemClickLitener:OnItemClickLitener, private val datas:List<String>):RecyclerView.Adapter<HomeAdapter.MyViewHolder>(){

        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
            var tvId:TextView = view.findViewById(R.id.tv_id)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_recycle_base_item, parent, false)
            val holder = MyViewHolder(view)

            return holder
        }

        override fun getItemCount(): Int {
            return datas.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.tvId.text = "position:" + position + " _ " + datas.get(position)
            holder.itemView.setOnClickListener {
                onItemClickLitener.onItemClick(holder.tvId,position)
            }
        }
    }

    override fun getViewBinding(): ActivityRecycleBaseBinding {
        return ActivityRecycleBaseBinding.inflate(layoutInflater)
    }
}