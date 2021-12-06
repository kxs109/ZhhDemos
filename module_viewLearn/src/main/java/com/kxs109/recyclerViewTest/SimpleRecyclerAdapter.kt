package com.kxs109.recyclerViewTest

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kxs109.viewlearn.R
import com.kxs109.viewlearn.databinding.ViewLearnRecyclerItemStringBinding

/*
 * @Author zhh
 * @Date 2021/11/23
 * @Des   recyclerView适配器
 */
class SimpleRecyclerAdapter constructor(var context: Context, private var dataList: List<String>) :
    RecyclerView.Adapter<SimpleRecyclerAdapter.VH>() {

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val tvTitle: TextView = itemView.findViewById(R.id.tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        Log.d("SimpleRecyclerViewTest", "onCreateViewHolder")
        /*return VH(
            LayoutInflater.from(context).inflate(R.layout.recycler_item_string, parent, false)
        )*/
        return VH(
            DataBindingUtil.inflate<ViewLearnRecyclerItemStringBinding>(
                LayoutInflater.from(context),
                R.layout.view_learn_recycler_item_string, parent, false
            ).root
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        Log.d("SimpleRecyclerViewTest", "onBindViewHolder${position}")
//        holder.tvTitle.text = String.format("Sample", position)
        DataBindingUtil.getBinding<ViewLearnRecyclerItemStringBinding>(holder.itemView)?.tvTitle?.text = dataList[position]
    }


    override fun getItemCount(): Int = dataList.size
}