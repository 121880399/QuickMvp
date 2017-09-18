package org.zzy.quickmvp.mvp.model.net.service;

import org.zzy.quickmvp.mvp.model.bean.CurrentWeather;
import org.zzy.quickmvp.mvp.model.bean.ForecastWeather;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/9/15
 */

public interface WeatherService {

    @GET("now")
    Flowable<CurrentWeather> getCurrentTimeWeather(@Query("city") String city, @Query("key") String key);


    @GET("forecast")
    Flowable<ForecastWeather> getForecastWeather(@Query("city") String city, @Query("key") String key);
}
