package com.zzy.quick.mvp.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.zzy.quick.mvp.presenter.IPresenter;
import com.zzy.quick.utils.ButterKnifeUtil;

import org.simple.eventbus.EventBus;

import butterknife.Unbinder;

/**
 * 项目名称: PlusOneLivePush
 * 创建人: 周正一
 * 创建时间：2017/8/2
 * 不带懒加载的Fragment基类
 */

public abstract class BaseFragment<P extends IPresenter> extends RxFragment implements IView<P>{

    protected LayoutInflater layoutInflater;
    private View rootView;
    private Unbinder unbinder;
    protected Activity mContext;
    private RxPermissions rxPermissions;
    private P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutInflater=inflater;
        if(rootView ==null){
            if( getLayoutId() >0) {
                rootView = inflater.inflate(getLayoutId(), null);
                initView(rootView);
            }
        }else{
            ViewGroup viewGroup= (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(useEventBus()){
            EventBus.getDefault().register(this);
        }
        initData();
        setListener();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.mContext= (Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext=null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(useEventBus()){
            EventBus.getDefault().unregister(this);
        }

        if (getPresenter() != null) {
            getPresenter().detachView();
        }

        mPresenter=null;

    }

    protected P getPresenter(){
        if(mPresenter==null){
            mPresenter=newPresenter();
            if(mPresenter!=null){
                mPresenter.attachView(this);
            }
        }
        return mPresenter;
    }

    protected RxPermissions getRxPermissions(){
        rxPermissions=new RxPermissions(getActivity());
        rxPermissions.setLogging(true);
        return rxPermissions;
    }


    /**
     * 得到布局id
     * */
    public abstract int getLayoutId();

    /**
     * 初始化View
     * */
    public  void initView(View view){
        //注册ButterKnife
        unbinder= ButterKnifeUtil.bind(this,rootView);
    }

    /**
     * 是否使用EventBus
     * */
    public  boolean useEventBus(){
        return false;
    }

    /**
     * 初始化数据
     * */
    public abstract void initData();

    /**
     * 设置监听
     * */
    public abstract void setListener();

    /**
     * 显示错误信息
     * */
    public abstract  void showError(String msg);

}
