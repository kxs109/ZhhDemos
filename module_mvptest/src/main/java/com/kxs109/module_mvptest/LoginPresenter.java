package com.kxs109.module_mvptest;

import com.kxs109.lib_mvpbase.base.BasePresenter;
import com.kxs109.module_mvptest.bean.UserInfo;

import java.util.Random;

public class LoginPresenter extends BasePresenter<LoginActivity, LoginModel, LoginContract.Presenter> {
    @Override
    public LoginContract.Presenter getContract() {
        return new LoginContract.Presenter<UserInfo>() {

            @Override
            public void requestLogin(String name, String pwd) {
                try {
                    //1、直接用model操作
                    // m.getContract().executeLogin(name, pwd);

                    //2、设置一个辅助类操作
                    // new HttpEngine<LoginPresenter>(LoginPresenter.this).login(name,pwd);
//3、直接用presenter操作
                    if (new Random().nextBoolean()) {
                        getContract().responseResult(new UserInfo(name,pwd));
                    }else{
                       getContract().responseResult(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void responseResult(UserInfo userInfo) {
                getView().getContract().handlerResult(userInfo);
            }
        };
    }

    @Override
    public LoginModel getModel() {
        return new LoginModel(this);
    }
}
