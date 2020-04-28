package com.kxs109.sometest.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.kxs109.sometest.R
import com.kxs109.sometest.databinding.SomeTestItemModuleBinding

/**
 * @Description:  跳转到各个模块的adapter
 * @Author:         zhh
 * @CreateDate:     2020/4/28 11:17
 */
class ModuleAdapter(layoutResId: Int = R.layout.some_test_item_module) :
    BaseQuickAdapter<String, BaseDataBindingHolder<SomeTestItemModuleBinding>>(layoutResId) {
    override fun convert(holder: BaseDataBindingHolder<SomeTestItemModuleBinding>, item: String) {
        val binding = holder.dataBinding
        binding?.item = item
    }

}