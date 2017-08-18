package com.zzy.quick.net;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.zzy.quick.mvp.model.IModel;

import org.json.JSONException;

import java.net.UnknownHostException;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 项目名称: PlusOneLivePush
 * 创建人: 周正一
 * 创建时间：2017/8/3
 * 自定义Subscriber，主要是对错误进行包装，让所有的错误都执行onFail方法
 */

public abstract class HttpSubscriber<T extends IModel> extends ResourceSubscriber<T> {

    @Override
    public void onError(Throwable e) {
        NetError error = null;
        if (e != null) {
            if (!(e instanceof NetError)) {
                if (e instanceof UnknownHostException) {
                    error = new NetError(e, NetError.NoConnectError);
                } else if (e instanceof JSONException
                        || e instanceof JsonParseException
                        || e instanceof JsonSyntaxException) {
                    error = new NetError(e, NetError.ParseError);
                } else {
                    error = new NetError(e, NetError.OtherError);
                }
            } else {
                error = (NetError) e;
            }

            if (useCommonErrorHandler()
                    && HttpManager.getCommonProvider() != null) {
                if (HttpManager.getCommonProvider().handleError(error)) {        //使用通用异常处理
                    return;
                }
            }
            onFail(error);
        }

    }

    protected abstract void onFail(NetError error);


    @Override
    public void onComplete() {

    }

    protected boolean useCommonErrorHandler() {
        return true;
    }


}
