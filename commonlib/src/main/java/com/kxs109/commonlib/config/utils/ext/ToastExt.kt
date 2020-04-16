package com.kxs109.commonlib.config.utils.ext

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes


fun Context.longToast(content: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, content, duration).show()
}

fun Context.longToast(@StringRes stringResId: Int, duration: Int = Toast.LENGTH_LONG) {
    longToast(resources.getString(stringResId))
}


fun Context.shortToast(content: String) {
    longToast(content, Toast.LENGTH_SHORT)
}
fun Context.shortToast(stringResId: Int) {
    longToast(resources.getString(stringResId), Toast.LENGTH_SHORT)
}