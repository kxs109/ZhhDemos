package com.kxs109.lib_mvpbase.base;
/**     
  * 
  * @Author: zhh 
  * @CreateDate: 2020/11/19 3:13 PM
  * @Description:    
 */
public abstract class BaseModel<P extends BasePresenter, CONTRACT> {
    protected P p;

    public BaseModel(P p) {
        this.p = p;
    }

    public abstract CONTRACT getContract();
}
