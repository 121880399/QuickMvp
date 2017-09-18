package com.zzy.quick.image;

import android.content.Context;
import android.widget.ImageView;

import java.io.File;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/13
 * 此接口定义了图片加载框架要实现的功能，扩展的图片加载器都要实现该接口
 */

public interface ImageLoader {



    /**
     *
     *  从网络加载图片
     *  @param url 图片地址
     *  @param target  将图片加载到哪个ImageView
     **/
    void loadNet(String url,ImageView target);

    /**
     *
     *  从网络加载图片
     *  @param url 图片地址
     *  @param target  将图片加载到哪个ImageView
     *  @param options 里面包含默认占位符，错误占位符
     * */
    void loadNet(String url,ImageView target,Options options);

    /**
     * 从网络加载图片
     * @param callback 图片加载成功，失败，取消加载的回调
     * **/
    void loadNet(Context context,String url,Options options,LoadCallback callback);


    /**
     * 从资源加载图片
     *
     * */
    void loadResource(int resId,ImageView target, Options options);

    void loadAssets( String assetName,ImageView target, Options options);

    /**
     * 从文件加载图片
     * */
    void loadFile( File file,ImageView target, Options options);

    /**
     * 清空内存缓存
     * */
    void clearMemoryCache(Context context);

    /**
     * 清空硬盘缓存
     * */
    void clearDiskCache(Context context);

    /**
     * 恢复加载
     * */
    void resume(Context context);

    /**
     * 暂停加载
     * */
    void pause(Context context);

    class Options {
        public static final int RES_NONE = -1;
        public int loadingResId = RES_NONE;        //加载中的资源id,占位符
        public int loadErrorResId = RES_NONE;      //加载失败的资源id，加载失败占位符
        public ImageView.ScaleType scaleType = null;

        public static Options defaultOptions() {
            return new Options(RES_NONE, RES_NONE);
        }

        public Options(int loadingResId, int loadErrorResId) {
            this.loadingResId = loadingResId;
            this.loadErrorResId = loadErrorResId;
        }

        public Options scaleType(ImageView.ScaleType scaleType) {
            this.scaleType = scaleType;
            return this;
        }
    }
}
