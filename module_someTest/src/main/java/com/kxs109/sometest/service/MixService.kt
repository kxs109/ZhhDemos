package com.kxs109.sometest.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MixService : Service() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return MyBinder()
    }

    inner class MyBinder : Binder() {
        fun callNext() {
            next()
        }

        fun callPrev() {
            prev()
        }
        fun callPlay() {
            play()
        }
        fun callPause() {
            pause()
        }
    }

    fun next() {
        println("播放下一首：老男孩")
    }

    fun prev() {
        println("播放上一首：以父之名")
    }

    fun play() {
        println("播放歌曲：秋意浓")
    }

    fun pause() {
        println("暂停播放")
    }
}

