package com.kxs109.sometest.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.kxs109.commonlib.config.SomeTestMain
import com.kxs109.mvvmbase.BaseActivity
import com.kxs109.sometest.R
import com.kxs109.sometest.adapter.ModuleAdapter
import kotlinx.android.synthetic.main.some_test_activity_main.*

@Route(path = SomeTestMain)
class MainActivity : BaseActivity() {
    override fun initData() {
    }

    override fun initView() {
        val _adapter = ModuleAdapter()
        _adapter.setNewInstance(getData())
        _adapter.setOnItemClickListener { adapter, view, position ->
            if (adapter.data[position]!! == "测试"){
            }
        }
        main_rcv.layoutManager = LinearLayoutManager(this)
        main_rcv.adapter = _adapter
    }

    private fun getData(): MutableList<String>? {
        val list = mutableListOf<String>()
        list.add("测试")
        return list
    }

    override fun getResLayout(): Int = R.layout.some_test_activity_main


    /**
     * 显示选择地区弹框
     */
    private fun showAreaSelectPop() {
        val areaSelectPop =
            AreaSelectPop(this) { province, province2, province3 ->
                province?.provinceName.logD()
            }
        areaSelectPop.setStyle(true)//设置老是或者学生style
        areaSelectPop.showPopupWindow()
    }
}
