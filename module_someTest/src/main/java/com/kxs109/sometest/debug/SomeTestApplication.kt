package com.kxs109.sometest.debug

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.kxs109.commonlib.config.AppConfig
import com.kxs109.sometest.util.installPatch
import java.io.File

/**
 * @Description:
 * @Author:         zhh
 * @CreateDate:     2020/4/27 10:21
 */
class SomeTestApplication : Application() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate() {
        super.onCreate()
        AppConfig.init(this)
        installPatch(this, File("/sdcard/patch.jar"))
    }

}