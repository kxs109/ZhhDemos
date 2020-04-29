package com.kxs109.sometest.model

import com.chad.library.adapter.base.entity.node.BaseNode
 /**
  * @Description:    学段选择 子类bean
  * @Author:         zhh
  * @CreateDate:     2020/4/29 15:02
  */
class SchoolSectionChild(
    val name:String?=null
) : BaseNode() {
    override val childNode: MutableList<BaseNode>?
        get() = null
}
