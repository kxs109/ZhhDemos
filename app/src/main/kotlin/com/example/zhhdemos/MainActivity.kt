package com.example.zhhdemos

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kxs109.commonlib.config.AppMain
import com.kxs109.commonlib.config.KotlinLearnMain
import com.kxs109.mvvmbase.BaseActivity
import kotlinx.android.synthetic.main.app_activity_main.*
@Route(path = AppMain)
class MainActivity: BaseActivity() {
    override fun initData() {
    }

    override fun getResLayout(): Int =R.layout.app_activity_main

    override fun initView() {
        kotlinLearnBtn.setOnClickListener {
            ARouter.getInstance().build(KotlinLearnMain) // 目标页面
                .withString("key1", "test_key1")  // 参数
                .navigation()
        }
    }



}