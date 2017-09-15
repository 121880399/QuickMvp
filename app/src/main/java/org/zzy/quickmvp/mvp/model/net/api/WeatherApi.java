package org.zzy.quickmvp.mvp.model.net.api;

import com.zzy.quick.net.HttpManager;

import org.zzy.quickmvp.common.AppConfig;
import org.zzy.quickmvp.mvp.model.net.service.WeatherService;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/9/15
 */

public class WeatherApi {

    private static WeatherService weatherService;

    public static WeatherService getWeatherService(){
        if (weatherService == null) {
            synchronized (WeatherApi.class){
                if (weatherService == null) {
                    weatherService= HttpManager.getInstance().getRetrofit(AppConfig.API_DATA_URL,true).create(WeatherService.class);
                }
            }
        }
        return weatherService;
    }

}
