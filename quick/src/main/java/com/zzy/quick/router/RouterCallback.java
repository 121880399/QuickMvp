package com.zzy.quick.router;

import android.app.Activity;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/24
 */

public interface RouterCallback {

    void onBefore(Activity from,Class<?> to);

    void onNext(Activity from,Class<?> to);

    void onError(Activity from,Class<?> to,Throwable throwable);

}
