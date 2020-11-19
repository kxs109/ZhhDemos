package com.kxs109.module_mvptest.bean;

import com.kxs109.lib_mvpbase.bean.BaseEnterty;

public class UserInfo  extends BaseEnterty {
    private String name;
    private String pwd;

    public UserInfo() {
    }

    public UserInfo(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
