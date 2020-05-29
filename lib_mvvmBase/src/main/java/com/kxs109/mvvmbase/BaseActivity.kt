package com.kxs109.mvvmbase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getResLayout())
        initView()
        initData()
    }

    abstract fun initData()

    abstract fun initView()

    abstract fun getResLayout(): Int


    fun goActivity(b: Bundle?, c: Class<*>) {
        Intent().run {
            setClass(this@BaseActivity, c)
            putExtra("data", b)
            startActivity(this)
        }
    }

    fun <T> goActivity(c: Class<T>) {
        Intent().run {
            setClass(this@BaseActivity, c)
            startActivity(this)
        }
    }
}