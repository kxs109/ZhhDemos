package com.kxs109.sometest.activity.aop

import android.content.Intent
import android.view.View
import com.kxs109.commonlib.config.utils.ext.logE
import com.kxs109.mvvmbase.BaseActivity
import com.kxs109.sometest.R
import com.kxs109.sometest.activity.MainActivity
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class AopMainActivity:BaseActivity(),IDbOperation{
    var db :IDbOperation?=null
    override fun initData() {
    }

    override fun initView() {
        db = Proxy.newProxyInstance(IDbOperation::class.java.classLoader, arrayOf(IDbOperation::class.java),HandlerDB(this)) as IDbOperation
    }


    fun jump(v: View){
        db?.delete()
        startActivity(Intent(this,MainActivity::class.java))
    }

    override fun getResLayout(): Int= R.layout.some_test_activity_aop_main
    override fun add() {
        "新增数据".logE()
    }

    override fun delete() {
        "删除数据".logE()
    }

    override fun update() {
        "更新数据".logE()
    }

    override fun query() {
        "查询数据".logE()
    }

    override fun save() {
        "保存数据".logE()
    }

    class HandlerDB(db: IDbOperation) :InvocationHandler{
        private var mDb:IDbOperation?= db
        override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
            if (mDb!=null){
                mDb?.save()
            }
            return method?.invoke(mDb,*args.orEmpty())
        }

    }
}

