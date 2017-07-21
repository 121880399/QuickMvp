package com.zzy.quick.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.zzy.quick.utils.log.LogFactory;

import java.io.InputStream;

import me.jessyan.progressmanager.ProgressManager;
import okhttp3.OkHttpClient;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/20
 * 让Glide使用Okhttp3,除了这里需要配置以外，AndroidManifest也需要配置
 */

public class GlideConfiguration implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        LogFactory.getLogUtil().d("registerComponents");
        //Glide 底层默认使用 HttpConnection 进行网络请求,这里替换为 Okhttp 后才能使用本框架,进行 Glide 的加载进度监听
        //如果不需要监听进度，则直接使用new OkHttpClient
        glide.register(GlideUrl.class
                , InputStream.class
                , new OkHttpUrlLoader.Factory(ProgressManager.getInstance()
                        .with(new OkHttpClient.Builder())
                        .build()));
    }
}
