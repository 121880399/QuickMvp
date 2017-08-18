package com.zzy.quick.mvp.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zzy.quick.R;
import com.zzy.quick.mvp.presenter.IPresenter;
import com.zzy.quick.utils.ButterKnifeUtil;
import com.zzy.quick.widget.ActionBarView;

import org.simple.eventbus.EventBus;

import butterknife.Unbinder;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/17
 */

public abstract class BaseActivity<P extends IPresenter> extends RxAppCompatActivity implements IView<P>{
        private P mPresenter;
        private Context mContext;
        private Unbinder unbinder;
        private RxPermissions rxPermissions;

        protected ActionBarView topbarView;
        //App是否退出
        public static boolean isExit=false;

        /**
         * 双击退出的消息处理
         */
        public Handler mHandlerExit = new Handler() {

                @Override
                public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        isExit = false;
                }
        };


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
                //butterKnife解除绑定
                if(unbinder!=Unbinder.EMPTY){
                        unbinder.unbind();
                }
                mPresenter=null;
        }

        /**
         * 得到RxPermission对象
         * */
        public RxPermissions getRxPermissions(){
                rxPermissions=new RxPermissions(this);
                rxPermissions.setLogging(true);
                return rxPermissions;
        }

        @Nullable
        public ActionBarView getTopbar() {
                if (topbarView == null) {
                        View view = findViewById(R.id.topbar_view);
                        if (view != null) {
                                topbarView = new ActionBarView(view);
                        }
                }
                return topbarView;
        }


        /**
         * 处理返回事件，如果在首页 连续按两次back键退出APP
         */
        public void dealAppBack() {
                if (!isExit) {
                        isExit = true;
                        Toast.makeText(getApplicationContext(), "再按一次退出程序",
                                Toast.LENGTH_SHORT).show();
                        // 利用handler延迟发送更改状态信息,间隔时间3秒钟，如果3秒内再次按back键，isExit就还为true就会退出APP
                        mHandlerExit.sendEmptyMessageDelayed(
                                0, 3000);
                } else {
                        finish();
                        APPActivityManager.getInstance().finishActivities();
                        System.exit(0);
                }
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
        public void setBeforeLayout(){
                APPActivityManager.getInstance().addActivity(this);
        }
        /**
         * 初始化View
         * */
        public  void initView(){
                //注册ButterKnife
                unbinder=ButterKnifeUtil.bind(this);
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
         * 是否使用EventBus
         * */
        public  boolean useEventBus(){
                return false;
        }

        /**
         * 显示错误信息
         * */
        public abstract  void showError(String msg);

}
