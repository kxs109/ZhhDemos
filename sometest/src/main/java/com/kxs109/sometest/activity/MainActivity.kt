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
        _adapter.setOnItemClickListener { adapter, view, position ->
            goActivity()
        }
        main_rcv.layoutManager = LinearLayoutManager(this)
        main_rcv.adapter = _adapter
    }

    override fun getResLayout(): Int = R.layout.some_test_activity_main


    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}
