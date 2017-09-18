package com.zzy.quick.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/13
 * 利用Glide实现图片加载
 */

public class GlideLoader implements ImageLoader{

    private static final String ASSETS_HEADER="file:///android_asset/";

    @Override
    public void loadNet(String url, ImageView target) {
        loadNet(url,target,null);
    }

    @Override
    public void loadNet(String url, ImageView target, Options options) {
        load(getRequestManager(target.getContext()).load(url),target,options);
    }

    @Override
    public void loadNet(Context context, String url, Options options, LoadCallback callback) {
        DrawableTypeRequest request = getRequestManager(context).load(url);
        if(options==null) options=Options.defaultOptions();

        if(options.loadingResId!= Options.RES_NONE){
            request.placeholder(options.loadingResId);
        }

        if(options.loadErrorResId!=Options.RES_NONE){
            request.error(options.loadErrorResId);
        }

        wrapScaleType(request,options)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//硬盘策略只保存源文件
                .crossFade()
                .into(new SimpleTarget<GlideBitmapDrawable>() {
                    @Override
                    public void onResourceReady(GlideBitmapDrawable resource, GlideAnimation glideAnimation) {
                        if(resource!=null && resource.getBitmap()!=null){
                            if(callback!=null){
                                callback.onLoadSuccess(resource.getBitmap());
                            }
                        }
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        if(callback!=null){
                            callback.onLoadFailed(e);
                        }
                    }
                });
    }

    @Override
    public void loadResource(int resId, ImageView target, Options options) {
        load(getRequestManager(target.getContext()).load(resId),target,options);
    }

    @Override
    public void loadAssets(String assetName, ImageView target, Options options) {
        load(getRequestManager(target.getContext()).load(ASSETS_HEADER+assetName),target,options);
    }

    @Override
    public void loadFile(File file, ImageView target, Options options) {
        load(getRequestManager(target.getContext()).load(file),target,options);
    }

    @Override
    public void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    @Override
    public void resume(Context context) {
        getRequestManager(context).resumeRequests();
    }

    @Override
    public void pause(Context context) {
        getRequestManager(context).pauseRequests();
    }

    private void load(DrawableTypeRequest request,ImageView target,Options options){
        if(options==null)options =Options.defaultOptions();

        if(options.loadingResId != Options.RES_NONE){
            request.placeholder(options.loadingResId);
        }

        if(options.loadErrorResId!= Options.RES_NONE){
            request.error(options.loadErrorResId);
        }

        wrapScaleType(request,options)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(target);
    }

    private RequestManager getRequestManager(Context context){
        if(context instanceof Activity){
            return Glide.with((Activity) context);
        }
        return Glide.with(context);
    }

    private DrawableTypeRequest wrapScaleType(DrawableTypeRequest request,Options options){
        if(options!=null && options.scaleType!=null){
            switch (options.scaleType){
                case MATRIX:
                    break;
                case FIT_XY:
                    break;
                case FIT_START:
                    break;
                case FIT_END:
                    break;
                case CENTER:
                    break;
                case CENTER_INSIDE:
                    break;
                case FIT_CENTER:
                    request.fitCenter();
                    break;
                case CENTER_CROP:
                    request.centerCrop();
                    break;
            }
        }
        return  request;
    }
}
