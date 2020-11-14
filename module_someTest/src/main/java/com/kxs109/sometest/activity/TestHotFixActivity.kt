package com.kxs109.sometest.activity

import android.widget.Toast
import com.kxs109.mvvmbase.BaseActivity
import com.kxs109.sometest.R
import kotlinx.android.synthetic.main.some_test_activity_hot_fix.*

class TestHotFixActivity:BaseActivity() {
    override fun initData() {
    }

    override fun initView() {
        btn_throw_exception.setOnClickListener {
            runException()
        }
    }

    private fun runException() {
        throw Exception("程序崩溃了。。。。")
//        Toast.makeText(this,"ok",Toast.LENGTH_LONG).show()
    }

    override fun getResLayout(): Int = R.layout.some_test_activity_hot_fix
}