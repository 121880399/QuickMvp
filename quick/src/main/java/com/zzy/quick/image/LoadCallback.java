package com.zzy.quick.image;

import android.graphics.Bitmap;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/13
 */

public abstract class LoadCallback {

    /**
     * 当加载失败
     * */
    void onLoadFailed(Throwable e){}

    /**
     * 当加载成功
     *
     */
    public abstract void onLoadSuccess(Bitmap bitmap);

    /**
     * 当加载被取消
     * */
    void onLoadCanceled(){}
}
