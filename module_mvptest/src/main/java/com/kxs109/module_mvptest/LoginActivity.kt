package com.kxs109.module_mvptest

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import com.kxs109.commonlib.config.utils.ext.shortToast
import com.kxs109.commonlib.config.utils.ext.showToast
import com.kxs109.lib_mvpbase.base.BaseView
import com.kxs109.module_mvptest.bean.UserInfo

class LoginActivity : BaseView<LoginPresenter, LoginContract.View<UserInfo>>() {
    val dialog  by lazy {ProgressDialog(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun btnLogin(view: View) {
        dialog.show()
        p.contract.requestLogin("kxs109","109kxs")
    }
    override fun getPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun getContract(): LoginContract.View<UserInfo> {
        return LoginContract.View {
            dialog.dismiss()
            if (it != null) {
                shortToast("登录成功")
            } else {
                shortToast("登录失败")
            }
        }
    }

}
