package com.kxs109.kotlinlearn

import com.alibaba.android.arouter.facade.annotation.Route
import com.kxs109.commonlib.config.KotlinLearnMain
import com.kxs109.commonlib.config.base.BaseActivity
import com.kxs109.commonlib.config.utils.ext.logD


@Route(path = KotlinLearnMain)
class MainActivity : BaseActivity() {
    override fun initView() {
        intent.getStringExtra("key1").logD()
    }

    override fun attachLayoutRes(): Int = R.layout.kotlin_learn_activity_main
}
