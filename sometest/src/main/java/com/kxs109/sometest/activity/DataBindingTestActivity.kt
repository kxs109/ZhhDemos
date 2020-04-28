package com.kxs109.sometest.activity

import com.kxs109.commonlib.config.utils.ext.logD
import com.kxs109.commonlib.config.utils.ext.showToast
import com.kxs109.mvvmbase.BaseVMActivity
import com.kxs109.sometest.R
import com.kxs109.sometest.module.viewModule.DataBindingTestModule

class DataBindingTestActivity:BaseVMActivity<DataBindingTestModule>() {
    override fun initData() {

    }

    override fun initView() {
    }

    override fun getResLayout(): Int = R.layout.some_test_activity_data_binding_test

    override fun observe() {
        "sss".showToast()
        "dd".logD()
    }

    override fun initViewModel(): DataBindingTestModule =null
}