package com.kxs109.sometest.model

import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.entity.node.NodeFooterImp
 /**
  * @Description:    学段选择 父bean
  * @Author:         zhh
  * @CreateDate:     2020/4/29 15:02
  */
data class SchoolSection(
    override val childNode: MutableList<BaseNode>?=null,
    val title: String) : BaseExpandNode()
