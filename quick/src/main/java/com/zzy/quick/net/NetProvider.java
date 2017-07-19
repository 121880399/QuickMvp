package com.zzy.quick.net;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/18
 */

public interface NetProvider {

    Interceptor[] configInterceptors();

    /**
     * 配置Http请求，在这里可以添加你需要的参数
     * */
    void configHttps(OkHttpClient.Builder builder);

    /**
     * 配置Cookie
     * */
    CookieJar configCookie();

    RequestHandler configHandler();

    /**
     * 配置连接超市时间
     * */
    long configConnectTimeoutMills();

    /**
     * 配置读超时时间
     * */
    long configReadTimeoutMills();

    boolean configLogEnable();

    boolean handleError(NetError error);
}
