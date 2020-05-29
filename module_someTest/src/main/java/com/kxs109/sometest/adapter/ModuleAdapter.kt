package com.kxs109.sometest.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.kxs109.sometest.R

/**
 * @Description:    跳转各个模块的adapter
 * @Author:         zhh
 * @CreateDate:     2020/5/7 17:31
 */
class ModuleAdapter(layoutResId: Int = R.layout.some_test_item_module) :
    BaseQuickAdapter<String, BaseViewHolder>(layoutResId) {
    init {
        addChildClickViewIds(R.id.btn_item_module, R.id.iv_item_module)
        addChildLongClickViewIds(R.id.btn_item_module)
    }
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.btn_item_module, item)
    }
}