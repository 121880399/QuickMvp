package org.zzy.quickmvp.mvp.ui;

import android.os.Bundle;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.utils.BarUtils;

import org.zzy.quickmvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {


    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void setBeforeLayout() {
        super.setBeforeLayout();
        //把背景图片设置到状态栏
        BarUtils.setStatusBar4Bg(this);
    }

    @Override
    public void initView() {
        super.initView();
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
    }

    @Override
    public void setListener() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public Object newPresenter() {
        return null;
    }

}
