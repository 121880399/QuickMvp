package com.zzy.quick.mvp.presenter;

import com.zzy.quick.mvp.ui.IView;

import java.lang.ref.WeakReference;


/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/17
 */

public abstract class BasePresenter<V extends IView> implements IPresenter<V> {

    private WeakReference<V> mView;

    @Override
    public void attachView(V view) {
        mView=new WeakReference<V>(view);
    }

    @Override
    public void detachView() {
        if(mView!=null){
            mView.clear();
            mView=null;
        }
    }


    protected  V getView(){
        if(mView==null){
            throw new IllegalStateException("view can not be null");
        }
        return mView.get();
    }


}
