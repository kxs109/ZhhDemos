package com.kxs109.module_mvptest;

import com.kxs109.lib_mvpbase.base.BaseModel;
import com.kxs109.module_mvptest.bean.UserInfo;

import java.util.Random;

import kotlin.random.RandomKt;

public class LoginModel extends BaseModel<LoginPresenter, LoginContract.Model> {


    public LoginModel(LoginPresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    public LoginContract.Model getContract() {
        return new LoginContract.Model() {
            @Override
            public void executeLogin(String name, String pwd) throws Exception {
                if (new Random().nextBoolean()) {
                  p.getContract().responseResult(new UserInfo(name,pwd));
                }else{
                    p.getContract().responseResult(null);
                }
            }
        };
    }
}
