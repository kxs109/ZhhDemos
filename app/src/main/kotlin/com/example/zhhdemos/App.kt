package com.example.zhhdemos

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.kxs109.commonlib.config.AppConfig
import com.kxs109.commonlib.config.utils.ext.logD

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppConfig.init(this)
        initRouter()
    }

    private fun initRouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


    private val mActivityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            ("onCreated: " + activity.componentName.className).logD()
        }

        override fun onActivityStarted(activity: Activity) {
            ("onStart: " + activity.componentName.className).logD()
        }

        override fun onActivityResumed(activity: Activity) {
            ("onResume: " + activity.componentName.className).logD()
        }

        override fun onActivityPaused(activity: Activity) {
            ("onPause: " + activity.componentName.className).logD()
        }

        override fun onActivityStopped(activity: Activity) {
            ("onStop: " + activity.componentName.className).logD()
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            ("onSaveInstanceState: " + activity.componentName.className).logD()
        }

        override fun onActivityDestroyed(activity: Activity) {
            ("onDestroy: " + activity.componentName.className).logD()
        }
    }
}