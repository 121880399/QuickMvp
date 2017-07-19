package com.zzy.quick.mvp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zzy.quick.mvp.presenter.IPresenter;
import com.zzy.quick.utils.log.LogFactory;

import org.simple.eventbus.EventBus;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/17
 */

public abstract class BaseActivity<P extends IPresenter> extends RxAppCompatActivity implements IView<P>{
        private P mPresenter;
        private Context mContext;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setBeforeLayout();
                mContext=this;
                if(getLayoutId()!=0){
                        setContentView(getLayoutId());
                        initView();
                        initData();
                        setListener();
                }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                if(getOptionsMenuId()>0){
                        getMenuInflater().inflate(getOptionsMenuId(),menu);
                }
                return super.onCreateOptionsMenu(menu);
        }

        /**
         * 得到Presenter对象
         * */
        protected P getPresenter(){
                if(mPresenter==null){
                       mPresenter=newPresenter();
                        //因为newPresenter是子Activity实现的，有可能返回Null，所以还需要再判断
                        if(mPresenter!=null){
                                mPresenter.attachView(this);
                        }
                }
                return mPresenter;
        }


        @Override
        protected void onStart() {
                super.onStart();
                //如果使用EventBus，在此处注册
                if(useEventBus()){
                        EventBus.getDefault().register(this);
                }
        }

        @Override
        protected void onResume() {
                super.onResume();
        }

        @Override
        protected void onPause() {
                super.onPause();
        }

        @Override
        protected void onStop() {
                super.onStop();
        }

        @Override
        protected void onDestroy() {
                super.onDestroy();
                //注销EventBus
                if(useEventBus()){
                        EventBus.getDefault().unregister(this);
                }
                //Presenter与activity解绑
                if(getPresenter()!=null){
                        getPresenter().detachView();
                }
                mPresenter=null;
        }



        /**
         * 得到布局id
         * */
        public abstract int getLayoutId();
        /**
         * 得到menuId
         * */
        public  int getOptionsMenuId(){
                return 0;
        }
        /**
         * 在设置布局前进行操作
         * */
        public abstract void setBeforeLayout();
        /**
         * 初始化View
         * */
        public abstract void initView();
        /**
         * 初始化数据
         * */
        public abstract void initData();

        /**
         * 设置监听
         * */
        public abstract void setListener();

        /**
         * 是否使用EventBus
         * */
        public  boolean useEventBus(){
                LogFactory.getLogUtil().d("BaseActivity");
                return false;
        }

}
