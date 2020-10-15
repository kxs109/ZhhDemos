package com.kxs109.sometest.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.view.View
import com.kxs109.mvvmbase.BaseActivity
import com.kxs109.sometest.R
import com.kxs109.sometest.service.MixService

class TestServiceActivity : BaseActivity() {
    private lateinit var conn: MyConn
    var b: MixService.MyBinder? = null
    override fun initData() {

    }

    override fun initView() {
        val mixServiceIntent = Intent(this, MixService::class.java)
        conn = MyConn()
        bindService(mixServiceIntent, conn, Context.BIND_AUTO_CREATE)
        startService(mixServiceIntent)
    }

    override fun onDestroy() {
        unbindService(conn)
        super.onDestroy()
    }

    inner class MyConn : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            b = service as MixService.MyBinder
        }

    }

    override fun getResLayout(): Int = R.layout.some_test_service_activity
    fun prev(view: View) {
        b?.callPrev()
    }
    fun next(view: View) {
        b?.callNext()
    }
    fun play(view: View) {
        b?.callPlay()
    }
    fun pause(view: View) {
        b?.callPause()
    }
}