package com.zzy.quick.net;

import android.text.TextUtils;

import com.zzy.quick.mvp.model.IModel;

import org.reactivestreams.Publisher;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.progressmanager.ProgressManager;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/18
 */

public class HttpManager {

    private static HttpManager instance;
    //是否使用RxJAVA
    private static final boolean USE_RX=true;
    //连接超时时间
    public static final long connectTimeoutMills = 10 * 1000l;
    //读超时时间
    public static final long readTimeoutMills = 10 * 1000l;

    private static NetProvider mProvider = null;

    private Map<String, NetProvider> providerMap = new HashMap<>();
    private Map<String, Retrofit> retrofitMap = new HashMap<>();
    private Map<String, OkHttpClient> clientMap = new HashMap<>();

    private HttpManager(){

    }

    public static HttpManager getInstance(){
        if (instance == null) {
            synchronized (HttpManager.class){
                if (instance == null) {
                    instance=new HttpManager();
                }
            }

        }
        return instance;
    }

    /**
     * 注册公用的Provider
     * */
    public static void registerProvider(NetProvider provider){
        HttpManager.mProvider=provider;
    }

    /**
     * 为URl单独设置Provider
     * */
    public static void registerProvider(String baseUrl,NetProvider provider){
        getInstance().providerMap.put(baseUrl,provider);
    }


    public static <T> T get(String baseUrl,Class<T> service){
        return getInstance().getRetrofit(baseUrl,USE_RX).create(service);
    }

    public Retrofit getRetrofit(String baseUrl,boolean useRx){
        return getRetrofit(baseUrl,null,useRx);
    }

    public Retrofit getRetrofit(String baseUrl,NetProvider provider,boolean useRx){
        if(TextUtils.isEmpty(baseUrl)){
            throw new IllegalStateException("BaseUrl can not be null");
        }
        if(retrofitMap.get(baseUrl)!=null) return retrofitMap.get(baseUrl);

        if(provider==null){
            provider=providerMap.get(baseUrl);
            if(provider==null){
                provider=mProvider;
            }
        }

        checkProvider(provider);

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getClient(baseUrl,provider))
                .addConverterFactory(GsonConverterFactory.create());

        if(useRx){
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }

        Retrofit retrofit=builder.build();
        retrofitMap.put(baseUrl,retrofit);
        providerMap.put(baseUrl,provider);

        return retrofit;
    }

    public OkHttpClient getClient(String baseUrl,NetProvider provider){
        if(TextUtils.isEmpty(baseUrl)){
            throw new IllegalStateException("BaseUrl can not be null");
        }
        if (clientMap.get(baseUrl) != null) {
            return clientMap.get(baseUrl);
        }

        checkProvider(provider);

        OkHttpClient.Builder builder=new OkHttpClient.Builder();

        builder.connectTimeout(provider.configConnectTimeoutMills()!=0
                ? provider.configConnectTimeoutMills()
                : connectTimeoutMills, TimeUnit.MILLISECONDS);

        builder.readTimeout(provider.configReadTimeoutMills()!=0
                ? provider.configReadTimeoutMills()
                : readTimeoutMills,TimeUnit.MILLISECONDS);

        //设置cookie
        CookieJar cookieJar = provider.configCookie();
        if(cookieJar!=null){
            builder.cookieJar(cookieJar);
        }

        provider.configHttps(builder);

        //网络拦截器如果不为空则添加，用于在执行请求前回调我们自定义的方法，
        RequestHandler handler=provider.configHandler();
        if (handler != null) {
            builder.addInterceptor(new NetInterceptor(handler));
        }

        //如果配置了拦截器，则全部加入
        Interceptor[] interceptors=provider.configInterceptors();
        if (interceptors != null && interceptors.length>0) {
            for (Interceptor interceptor:interceptors) {
                builder.addInterceptor(interceptor);

            }
        }

        //是否使用日志拦截器，拦截Http请求和返回
        if(provider.configLogEnable()){
            LogInterceptor logInterceptor =new LogInterceptor();
            builder.addInterceptor(logInterceptor);
        }

        OkHttpClient client = null;
        //如果使用了进度监听
        if(provider.useProgress()){
            client= ProgressManager.getInstance().with(builder).build();
        }

        client= builder.build();
        clientMap.put(baseUrl,client);
        providerMap.put(baseUrl,provider);

        return client;
    }

    private void checkProvider(NetProvider provider){
        if (provider == null) {
            throw new IllegalStateException("must register provider first");
        }
    }

    public static NetProvider getCommonProvider(){
        return mProvider;
    }

    public Map<String,Retrofit> getRetrofitMap(){
        return retrofitMap;
    }

    public Map<String,OkHttpClient> getClientMap(){
        return clientMap;
    }

    /**
     * 清理缓存的Retrofit和OkHttpClient
     * */
    public static void clearCache(){
        getInstance().retrofitMap.clear();
        getInstance().clientMap.clear();
    }

    /**
     * 切换线程
     * */
    public static <T extends IModel> FlowableTransformer<T,T> getScheduler(){
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
