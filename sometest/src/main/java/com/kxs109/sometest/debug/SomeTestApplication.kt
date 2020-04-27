package com.kxs109.sometest.debug

import android.app.Application
import com.kxs109.commonlib.config.AppConfig

 /**
  * @Description:
  * @Author:         zhh
  * @CreateDate:     2020/4/27 10:21
  */
class SomeTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppConfig.init(this)
    }

}