package com.kxs109.kotlinlearn.debug

import android.app.Application
import com.kxs109.commonlib.config.AppConfig

/**
  *
  * @Author: zhh
  * @CreateDate: 2020-03-14 21:17
  * @Description:
 */
class KotlinLearnApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppConfig.init(this)
    }

}