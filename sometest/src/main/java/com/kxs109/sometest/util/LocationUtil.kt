package com.kxs109.sometest.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.kxs109.commonlib.config.utils.ext.showToast

class LocationUtil :LifecycleObserver{
    companion object{
        fun getInstance():LocationUtil{
            return LocationUtil()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startLocation(){
        "开始定位".showToast()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopLocation(){
        "停止定位".showToast()
    }
}