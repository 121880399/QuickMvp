package com.zzy.quick.net;

import com.zzy.quick.utils.log.LogFactory;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/19
 */

public class LogInterceptor implements Interceptor {
    public static final String TAG="NET";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        logRequest(request);
        Response response= chain.proceed(request);
        return logResponse(response);
    }

    private void logRequest(Request request){
        try {
            String url=request.url().toString();
            Headers headers=request.headers();

            LogFactory.getLogUtil().d(TAG,"url: "+url);
            LogFactory.getLogUtil().d(TAG,"method: "+request.method());
            if(headers!=null && headers.size()>0){
                 LogFactory.getLogUtil().d(TAG,"headers: "+headers.toString());
            }

            RequestBody requestBody=request.body();
            if (requestBody != null) {
                MediaType mediaType=requestBody.contentType();
                if (mediaType != null) {
                    if(isText(mediaType)){
                        LogFactory.getLogUtil().d(TAG,"params: "+bodyToString(request));
                    }else{
                        LogFactory.getLogUtil().d(TAG,"params: "+"maybe [file part] , too large too print , ignored!");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Response logResponse(Response response){
        try{
            Response.Builder builder=response.newBuilder();
            Response clone = builder.build();
            ResponseBody body=clone.body();
            if (body != null) {
                MediaType mediaType=body.contentType();
                if (mediaType != null) {
                    if(isText(mediaType)){
                        String resp=body.toString();
                        LogFactory.getLogUtil().d(TAG,resp);

                        body=ResponseBody.create(mediaType,resp);
                        return response.newBuilder().body(body).build();
                    }
                }else{
                    LogFactory.getLogUtil().d(TAG,"data : " + " maybe [file part] , too large too print , ignored!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    private boolean isText(MediaType mediaType) {
        if (mediaType == null) return false;

        return ("text".equals(mediaType.subtype())
                || "json".equals(mediaType.subtype())
                || "xml".equals(mediaType.subtype())
                || "html".equals(mediaType.subtype())
                || "webviewhtml".equals(mediaType.subtype())
                || "x-www-form-urlencoded".equals(mediaType.subtype()));
    }

    private String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }

}
