package org.zzy.quickmvp.mvp.presenter;

import com.zzy.quick.mvp.presenter.BasePresenter;
import com.zzy.quick.net.HttpManager;
import com.zzy.quick.net.HttpSubscriber;
import com.zzy.quick.net.NetError;
import com.zzy.quick.utils.log.LogFactory;

import org.zzy.quickmvp.common.AppConfig;
import org.zzy.quickmvp.mvp.model.bean.CurrentWeather;
import org.zzy.quickmvp.mvp.model.net.api.WeatherApi;
import org.zzy.quickmvp.mvp.ui.MainActivity;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/9/15
 */

public class WeatherPresenter extends BasePresenter<MainActivity>{

    /**
     * 得到当前天气数据
     * */
    public void getCurrentWeather(String city){
        WeatherApi.getWeatherService().getCurrentTimeWeather(city, AppConfig.APP_KEY)
                .compose(HttpManager.<CurrentWeather>getErrorTransformer())
                .compose(HttpManager.<CurrentWeather>getFlowableScheduler())
                .subscribe(new HttpSubscriber<CurrentWeather>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    public void onNext(CurrentWeather currentWeather) {
                        getView().updataCurrentWeather(currentWeather);
                    }
                });
    }
}
