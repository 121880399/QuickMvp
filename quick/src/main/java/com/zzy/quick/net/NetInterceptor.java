package com.zzy.quick.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/18
 */

public class NetInterceptor implements Interceptor {

    RequestHandler handler;

    public NetInterceptor(RequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        if (handler != null) {
            request=handler.onBeforeRequest(request,chain);
        }
        Response respons=chain.proceed(request);
        if (handler != null) {
            respons=handler.onAfterRequest(respons,respons.body().string(),chain);
        }
        return respons;
    }
}
