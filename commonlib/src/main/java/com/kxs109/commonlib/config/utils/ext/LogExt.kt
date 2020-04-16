package com.kxs109.commonlib.config.utils.ext

import android.util.Log
import com.kxs109.commonlib.BuildConfig


const val TAG = "zhh"
var showLog = BuildConfig.DEBUG

private enum class LogLevel {
    V, D, I, W, E
}

private fun log(level: LogLevel, tag: String, content: String) {
    if (!showLog) return
    when (level) {
        LogLevel.V -> {
            Log.v(tag, content)
        }
        LogLevel.D -> {
            Log.d(tag, content)

        }
        LogLevel.I -> {
            Log.i(tag, content)

        }
        LogLevel.E -> {
            Log.e(tag, content)

        }
        LogLevel.W -> {
            Log.w(tag, content)

        }
    }

}

fun String?.logV(tag: String = TAG) {
    log(LogLevel.V, tag, this?:"null")
}

fun String?.logD(tag: String = TAG) {
    log(LogLevel.D, tag, this?:"null")

}

fun String?.logI(tag: String = TAG) {
    log(LogLevel.I, tag, this?:"null")

}

fun String?.logW(tag: String = TAG) {
    log(LogLevel.W, tag, this?:"null")

}

fun String?.logE(tag: String = TAG) {
    log(LogLevel.E, tag, this?:"null")

}

