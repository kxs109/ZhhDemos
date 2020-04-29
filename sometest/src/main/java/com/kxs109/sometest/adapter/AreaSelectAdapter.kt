package com.kxs109.sometest.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.kxs109.sometest.R
import com.kxs109.sometest.model.Province

/**
 * @Description:  地区筛选弹框adapter
 * @Author:         zhh
 * @CreateDate:     2020/4/28 18:31
 */
class AreaSelectAdapter(layoutResId: Int = R.layout.item_area_select) :
    BaseQuickAdapter<Province, BaseViewHolder>(layoutResId) {
    var mSelectPosition = 0
    fun setSelect(position: Int) {
        mSelectPosition = position
        notifyDataSetChanged()
    }

    override fun convert(holder: BaseViewHolder, item: Province) {
        when {
            item.level == 1 -> holder.setText(R.id.tv_item_area, item.provinceName)
            item.level == 2 -> holder.setText(R.id.tv_item_area, item.cityName)
            else -> holder.setText(R.id.tv_item_area, item.areaName)
        }
        if (mSelectPosition == holder.layoutPosition) {
            holder.setGone(R.id.iv_item_area, false)
        } else {
            holder.setGone(R.id.iv_item_area, true)
        }

    }


}