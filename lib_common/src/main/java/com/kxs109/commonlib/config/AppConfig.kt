package com.kxs109.commonlib.config

import android.app.Application

/**
  *
  * @Author: zhh
  * @CreateDate: 2020-03-14 16:55
  * @Description:  application类初始化配置
 */
object AppConfig {

    const val TAG = "ZhhDemos"

    var debug = false

    private var application: Application? = null

    /**
     * Init, it must be call before used .
     */
    fun init(application: Application) {
        AppConfig.application = application
    }

    fun getApplication(): Application {
        if (application == null) {
            throw RuntimeException("Please init in Application#onCreate first.")
        }
        return application!!
    }

    fun openDebug() {
        debug = true
    }

}