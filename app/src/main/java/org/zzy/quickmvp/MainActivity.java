package org.zzy.quickmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.utils.log.LogFactory;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public void setBeforeLayout() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        //在APP启动的时候就要设置这两个参数。
        LogFactory.getLogUtil().isLogAble(true);
        LogFactory.getLogUtil().setGlobalTag("QuickMvp");
    }

    @Override
    public void setListener() {

    }


    @Override
    public Object newPresenter() {
        return null;
    }

    @Override
    public boolean useEventBus() {
        LogFactory.getLogUtil().d("MainActivity");
        return false;
    }
}
