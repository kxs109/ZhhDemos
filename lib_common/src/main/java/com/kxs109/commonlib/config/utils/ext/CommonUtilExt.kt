package com.kxs109.commonlib.config.utils.ext

import android.content.Context
 /**
  * @Description:   通用工具类
  * @Author:         zhh
  * @CreateDate:     2020/4/28 17:22
  */
val Context.screenWidth
    get() = resources.displayMetrics.widthPixels
val Context.screenHeight
    get() = resources.displayMetrics.heightPixels