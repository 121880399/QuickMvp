package org.zzy.quickmvp.mvp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.zzy.quick.image.ImageFactory;
import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.utils.BarUtils;
import com.zzy.quick.utils.TimeUtils;

import org.zzy.quickmvp.R;
import org.zzy.quickmvp.common.AppConfig;
import org.zzy.quickmvp.mvp.model.bean.CurrentWeather;
import org.zzy.quickmvp.mvp.model.bean.ForecastWeather;
import org.zzy.quickmvp.mvp.presenter.WeatherPresenter;

import java.util.List;

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
    @BindView(R.id.tv_airPressure)
    TextView tvAirPressure;
    @BindView(R.id.tv_visibility)
    TextView tvVisibility;
    @BindView(R.id.tv_rainfall)
    TextView tvRainfall;
    @BindView(R.id.tv_humidity)
    TextView tvHumidity;
    @BindView(R.id.iv_todayWeatherImg)
    ImageView ivTodayWeatherImg;
    @BindView(R.id.iv_tomorrowWeatherImg)
    ImageView ivTomorrowWeatherImg;
    @BindView(R.id.iv_afterDayWeatherImg)
    ImageView ivAfterDayWeatherImg;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        getPresenter().getCurrentWeather("北京");
        getPresenter().getForecastWeather("北京");
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
        tvAirPressure.setText(now.getPres());
        tvVisibility.setText(now.getVis());
        tvRainfall.setText(now.getPcpn());
        tvHumidity.setText(now.getHum());
    }


    /**
     * 更新3天天气
     */
    public void updateForecastWeather(ForecastWeather forecastWeather) {
        List<ForecastWeather.HeWeather5Bean.DailyForecastBean> forecastWeatherList = forecastWeather.getHeWeather5().get(0).getDaily_forecast();
        ForecastWeather.HeWeather5Bean.DailyForecastBean today = forecastWeatherList.get(0);
        ForecastWeather.HeWeather5Bean.DailyForecastBean tomorrow = forecastWeatherList.get(1);
        ForecastWeather.HeWeather5Bean.DailyForecastBean afterDay = forecastWeatherList.get(2);

        tvTodayHighTemp.setText(today.getTmp().getMax());
        tvTodaylowTemp.setText(today.getTmp().getMin());
        setWeatherAndImg(tvTodayWeather,ivTodayWeatherImg,today);

        tvTomorrowHighTemp.setText(tomorrow.getTmp().getMax());
        tvTomorrowLowTemp.setText(tomorrow.getTmp().getMin());
        setWeatherAndImg(tvTomorrowWeather,ivTomorrowWeatherImg,tomorrow);

        tvAfterDayHighTemp.setText(afterDay.getTmp().getMax());
        tvAfterDayLowTemp.setText(afterDay.getTmp().getMin());
        setWeatherAndImg(tvAfterDayWeather,ivAfterDayWeatherImg,afterDay);


    }

    /**
     * 根据当前是否是白天来设置天气情况和图片
     * */
    private void setWeatherAndImg(TextView weather,ImageView weatherImg,ForecastWeather.HeWeather5Bean.DailyForecastBean day){
        if (TimeUtils.isDaytime()) {
            weather.setText(day.getCond().getTxt_d());
            ImageFactory.getImageLoader().loadNet(AppConfig.API_ICON_URL + day.getCond().getCode_d() + ".png", weatherImg);
        } else {
            weather.setText(day.getCond().getTxt_n());
            ImageFactory.getImageLoader().loadNet(AppConfig.API_ICON_URL + day.getCond().getCode_n() + ".png", weatherImg);
        }
    }

}
