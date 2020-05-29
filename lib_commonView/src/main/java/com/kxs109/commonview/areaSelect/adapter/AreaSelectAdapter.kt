package com.kxs109.commonview.areaSelect.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.kxs109.commonview.R
import com.kxs109.commonview.areaSelect.model.Province

/**
 * @Description:  地区筛选弹框adapter
 * @Author:         zhh
 * @CreateDate:     2020/4/28 18:31
 */
class AreaSelectAdapter(layoutResId: Int = R.layout.item_area_select) :
    BaseQuickAdapter<Province, BaseViewHolder>(layoutResId) {
    private var mSelectPosition = 0
    private val STYLE_TEACHER = 0
    private val STYLE_STUDENT = 1
    private var mCurrentStyle = STYLE_TEACHER
    fun setSelect(position: Int) {
        mSelectPosition = position
        notifyDataSetChanged()
    }

    fun setStyle(isStudent: Boolean) {
        if (isStudent) {
            mCurrentStyle = STYLE_STUDENT
        }
    }

    override fun convert(holder: BaseViewHolder, item: Province) {
        when (item.level) {
            1 -> holder.setText(R.id.tv_item_area, item.provinceName)
            2 -> holder.setText(R.id.tv_item_area, item.cityName)
            else -> holder.setText(R.id.tv_item_area, item.areaName)
        }
        if (mSelectPosition == holder.layoutPosition) {
            holder.setGone(R.id.iv_item_area, false)
        } else {
            holder.setGone(R.id.iv_item_area, true)
        }

    }


}