package com.kxs109.sometest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.kxs109.commonlib.config.SomeTestMain
import com.kxs109.sometest.util.LocationUtil

@Route(path = SomeTestMain)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.some_test_activity_main)
        lifecycle.addObserver(LocationUtil.getInstance())
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}
