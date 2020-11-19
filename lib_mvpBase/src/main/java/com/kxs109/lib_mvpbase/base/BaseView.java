package com.kxs109.lib_mvpbase.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
/**
  *
  * @Author: zhh
  * @CreateDate: 2020/11/19 2:42 PM
  * @Description:    View层应该让Presenter层返回操作结果
 */
public abstract class BaseView<P extends BasePresenter, CONTRACT> extends Activity {
    protected P p;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p = getPresenter();
        p.bindView(this);
    }

    public abstract P getPresenter();

    public abstract CONTRACT getContract();

    @Override
    protected void onDestroy() {
        p.unBindView();
        super.onDestroy();
    }
}
