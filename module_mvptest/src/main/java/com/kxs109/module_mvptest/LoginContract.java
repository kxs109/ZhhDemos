package com.kxs109.module_mvptest;

import com.kxs109.lib_mvpbase.bean.BaseEnterty;

public interface LoginContract {
    interface Model{
        void executeLogin(String name,String pwd) throws Exception;
    }
    interface View<T extends BaseEnterty>{
        void handlerResult(T t);
    }
    interface Presenter<T extends BaseEnterty>{
        void requestLogin(String name,String pwd);
        void responseResult(T t);
    }

}
