package com.kxs109.lib_mvpbase.base;

import java.lang.ref.WeakReference;
/**
  *
  * @Author: zhh
  * @CreateDate: 2020/11/19 2:40 PM
  * @Description:    Presenter层应该持有View层，model层，还有契约
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel, CONTRACT> {
    private WeakReference<V> vWeakReference;
    protected M m;

    public BasePresenter() {
        m = getModel();
    }

    public void bindView(V view) {
        vWeakReference = new WeakReference<V>(view);
    }

    public void unBindView() {
        if (vWeakReference != null) {
            vWeakReference.clear();
            vWeakReference = null;
            System.gc();
        }
    }

    public V getView() {
        if (vWeakReference != null) {
            return vWeakReference.get();
        }
        return null;
    }

    public abstract CONTRACT getContract();

    public abstract M getModel();

}
