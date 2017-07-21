package org.zzy.quickmvp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.utils.log.LogFactory;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.donutProgress)
    DonutProgress donutProgress;
    @BindView(R.id.iv_pic)
    ImageView ivPic;

    public static final String IMAGE_URL = "http://dynamic-image.yesky.com/740x-/uploadImages/2017/188/11/01V71708HW3X.jpg";
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
        super.initView();
    }

    @Override
    public void initData() {
        //在APP启动的时候就要设置这两个参数。
        LogFactory.getLogUtil().isLogAble(true);
        LogFactory.getLogUtil().setGlobalTag("QuickMvp");
    }

    @Override
    public void setListener() {
        ProgressManager.getInstance().addResponseListener(IMAGE_URL,getGlideListener());
    }


    @Override
    public Object newPresenter() {
        return null;
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @OnClick(R.id.btn_start)
    public void startShowPic(){
        donutProgress.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load(IMAGE_URL)
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(ivPic);
    }

    private ProgressListener getGlideListener(){
        return new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                LogFactory.getLogUtil().d(" "+progressInfo.getPercent());
                donutProgress.setProgress(progressInfo.getPercent());
                if(progressInfo.isFinish()){
                    LogFactory.getLogUtil().d("Finish");
                    donutProgress.setVisibility(View.GONE);
                    ivPic.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(long id, Exception e) {

            }
        };
    }
}
