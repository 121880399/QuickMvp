package org.zzy.quickmvp.mvp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.utils.BarUtils;

import org.zzy.quickmvp.R;
import org.zzy.quickmvp.mvp.model.bean.CurrentWeather;
import org.zzy.quickmvp.mvp.presenter.WeatherPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity<WeatherPresenter> {


    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_divide)
    TextView tvDivide;
    @BindView(R.id.tv_weather)
    TextView tvWeather;
    @BindView(R.id.iv_wetherIcon)
    ImageView ivWetherIcon;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.view_place)
    View viewPlace;
    @BindView(R.id.tv_todayHighTemp)
    TextView tvTodayHighTemp;
    @BindView(R.id.tv_todaylowTemp)
    TextView tvTodaylowTemp;
    @BindView(R.id.tv_todayWeather)
    TextView tvTodayWeather;
    @BindView(R.id.tv_tomorrowHighTemp)
    TextView tvTomorrowHighTemp;
    @BindView(R.id.tv_tomorrowLowTemp)
    TextView tvTomorrowLowTemp;
    @BindView(R.id.tv_tomorrowWeather)
    TextView tvTomorrowWeather;
    @BindView(R.id.tv_afterDayHighTemp)
    TextView tvAfterDayHighTemp;
    @BindView(R.id.tv_afterDayLowTemp)
    TextView tvAfterDayLowTemp;
    @BindView(R.id.tv_afterDayWeather)
    TextView tvAfterDayWeather;
    @BindView(R.id.tv_windDirection)
    TextView tvWindDirection;
    @BindView(R.id.tv_windSpeed)
    TextView tvWindSpeed;
    @BindView(R.id.tv_windPower)
    TextView tvWindPower;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        getPresenter().getCurrentWeather("北京");
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
    public WeatherPresenter newPresenter() {
        return new WeatherPresenter();
    }


    /***
     * 更新当前天气
     */
    public void updataCurrentWeather(CurrentWeather currentWeather) {
        CurrentWeather.HeWeather5Bean.NowBean now = currentWeather.getHeWeather5().get(0).getNow();
        tvCity.setText(now.getCond().getTxt());
        tvTemperature.setText(now.getTmp());
        tvWindDirection.setText(now.getWind().getDir());
        tvWindSpeed.setText(now.getWind().getSpd());
        tvWindPower.setText(now.getWind().getSc());

    }


}
