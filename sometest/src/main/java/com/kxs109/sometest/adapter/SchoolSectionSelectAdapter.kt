package com.kxs109.sometest.adapter

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.kxs109.sometest.model.SchoolSection
import com.kxs109.sometest.model.SchoolSectionChild
import com.kxs109.sometest.adapter.itemProvider.SchoolSectionChildSelectProvider
import com.kxs109.sometest.adapter.itemProvider.SchoolSectionSelectProvider

/**
 * @Description:    学段选择适配器
 * @Author:         zhh
 * @CreateDate:     2020/4/29 14:29
 */
class SchoolSectionSelectAdapter :
    BaseNodeAdapter() {
    init{
        addFullSpanNodeProvider(SchoolSectionSelectProvider())
        addNodeProvider(SchoolSectionChildSelectProvider())
    }
    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        return when (data[position]) {
            is SchoolSection -> 0
            is SchoolSectionChild -> 1
            else -> 0
        }
    }


}