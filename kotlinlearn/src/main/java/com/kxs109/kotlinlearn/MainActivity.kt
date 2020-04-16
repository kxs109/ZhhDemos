package com.kxs109.kotlinlearn

import com.alibaba.android.arouter.facade.annotation.Route
import com.kxs109.commonlib.config.KotlinLearnMain
import com.kxs109.commonlib.config.utils.ext.logD
import com.kxs109.mvvmbase.BaseActivity


@Route(path = KotlinLearnMain)
class MainActivity : BaseActivity() {
    override fun initData() {
    }

    override fun getResLayout(): Int =R.layout.kotlin_learn_activity_main

    override fun initView() {
        intent.getStringExtra("key1").logD()
    }

}
