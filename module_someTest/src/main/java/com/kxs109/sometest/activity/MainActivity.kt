package com.kxs109.sometest.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.kxs109.commonlib.config.SomeTestMain
import com.kxs109.commonlib.config.utils.ext.logD
import com.kxs109.commonlib.config.utils.ext.showToast
import com.kxs109.commonview.dialog.AreaSelectPop
import com.kxs109.mvvmbase.BaseActivity
import com.kxs109.sometest.R
import com.kxs109.sometest.adapter.ModuleAdapter
import kotlinx.android.synthetic.main.some_test_activity_main.*

@Route(path = SomeTestMain)
class MainActivity : BaseActivity() {
    /**
     * 显示选择地区弹框
     */
    private val mAreaSelectPop by lazy {
        AreaSelectPop(this) { province, province2, province3 -> province?.provinceName.logD() }
    }

    override fun initData() {
    }

    override fun initView() {
        val _adapter = ModuleAdapter()
        _adapter.setOnItemChildClickListener { adapter, view, position ->
            if (view.id==R.id.btn_item_module){
                "DDDD1".showToast()
                if (adapter.data[position] == "省市区选择弹框") {
                    mAreaSelectPop.setStyle(false)//设置不同风格
                    mAreaSelectPop.showPopupWindow()
                }
            }else if (view.id==R.id.iv_item_module){
                "DDDD1".showToast()
                if (adapter.data[position] == "省市区选择弹框") {
                    mAreaSelectPop.setStyle(false)//设置不同风格
                    mAreaSelectPop.showPopupWindow()
                }
            }
        }
        _adapter.setOnItemChildLongClickListener { adapter, view, position ->
            "ddddd".showToast()
            true
        }
        _adapter.setNewInstance(getData())
//        _adapter.setOnItemClickListener { adapter, view, position ->
//            if (adapter.data[position] == "省市区选择弹框") {
//                mAreaSelectPop.setStyle(false)//设置不同风格
//                mAreaSelectPop.showPopupWindow()
//            }
//        }
        main_rcv.layoutManager = LinearLayoutManager(this)
        main_rcv.adapter = _adapter

    }

    private fun getData(): MutableList<String>? {
        val list = mutableListOf<String>()
        list.add("省市区选择弹框")
        return list
    }

    override fun getResLayout(): Int = R.layout.some_test_activity_main


}
