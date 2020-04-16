package com.kxs109.mvvmbase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getResLayout())
        initView()
        initData()
    }

    abstract fun initData()

    abstract fun initView()

    abstract fun getResLayout(): Int

}