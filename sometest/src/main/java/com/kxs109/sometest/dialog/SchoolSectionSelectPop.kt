package com.kxs109.sometest.dialog

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.eaglesoul.commonlibrary.utils.ext.screenHeight
import com.eaglesoul.commonlibrary.utils.ext.screenWidth
import com.eaglesoul.commonview.R
import com.kxs109.sometest.adapter.SchoolSectionSelectAdapter
import com.kxs109.sometest.model.SchoolSection
import com.kxs109.sometest.model.SchoolSectionChild
import razerdp.basepopup.BasePopupWindow
import java.util.ArrayList

/**
  * @Description:    学段选择弹框
  * @Author:         zhh
  * @CreateDate:     2020/4/29 13:53
  */
class SchoolSectionSelectPop(val context: Context, clickAreaSelect: ()-> Unit) : BasePopupWindow(context) {

    init {
        popupGravity = Gravity.CENTER
        setBackground(0)
        width = context.screenWidth*0.85.toInt()
        height = context.screenHeight * 0.75.toInt()
        setOutSideDismiss(true)
        isOutSideTouchable = true
        setBackPressEnable(true)
        setBackgroundColor(Color.parseColor("#aa000000"))
        val btnSelectArea = findViewById<FrameLayout>(R.id.btn_select_area)
        btnSelectArea.setOnClickListener { clickAreaSelect() }
        val rcv = findViewById<RecyclerView>(R.id.rcv_school_section)
        rcv.layoutManager=GridLayoutManager(context, 3)
        val _schoolSectionSelectAdapter = SchoolSectionSelectAdapter()
        // 顶部header
        rcv.adapter = _schoolSectionSelectAdapter
        _schoolSectionSelectAdapter.setList(getEntity())
        rcv.scheduleLayoutAnimation()
    }

     private fun getEntity(): Collection<BaseNode>? {
         val list = ArrayList<BaseNode>()
         for (i in 0..4){
             val itemEntity1 = SchoolSectionChild("一年级")
             val itemEntity2 = SchoolSectionChild("二年级")
             val itemEntity3 = SchoolSectionChild("三年级")
             val itemEntity4 = SchoolSectionChild("四年级")
             val itemEntity5 = SchoolSectionChild("五年级")
             val listChild= mutableListOf<BaseNode>()
             listChild.add(itemEntity1)
             listChild.add(itemEntity2)
             listChild.add(itemEntity3)
             listChild.add(itemEntity4)
             listChild.add(itemEntity5)
             val entity = SchoolSection(listChild, "小学")
             list.add(entity)
         }
         return list
     }

     override fun onCreateContentView(): View = createPopupById(R.layout.pop_school_section_select)
}