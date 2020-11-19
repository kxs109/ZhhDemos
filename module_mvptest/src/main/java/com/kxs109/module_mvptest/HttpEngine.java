package com.kxs109.module_mvptest;

import com.kxs109.module_mvptest.bean.UserInfo;

import java.util.Random;

public class HttpEngine<P extends LoginPresenter> {
    private P p;
    public HttpEngine(P p){
        this.p = p;
    }

    public void login(String name,String pwd){
        if (new Random().nextBoolean()) {
            p.getContract().responseResult(new UserInfo(name,pwd));
        }else{
            p.getContract().responseResult(null);
        }
    }
}
