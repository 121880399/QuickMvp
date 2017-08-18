package com.zzy.quick.mvp.presenter;



/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/17
 */

public interface IPresenter<V> {
    void attachView(V view);
    void detachView();

}
