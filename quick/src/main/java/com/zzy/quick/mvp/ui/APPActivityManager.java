package com.zzy.quick.mvp.ui;

import android.app.Activity;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;

import static okhttp3.Protocol.get;

/**
 * 管理所有的Activity
 * Created by 周正一 on 2017/5/8.
 */
public class APPActivityManager {
    private ArrayMap<String,Activity> allActivity = new ArrayMap<String,Activity>();

    private APPActivityManager() {
    }

    private static APPActivityManager activityManager;

    public static APPActivityManager getInstance() {
        if (activityManager == null) {
            synchronized (APPActivityManager.class){
                if(activityManager==null){
                    activityManager = new APPActivityManager();
                }
            }
        }
        return activityManager;
    }

    /**
     * 添加Activity
     * */
    public void addActivity(Activity activity) {
        if (allActivity != null) {
            Log.d("ddd","activity name:"+activity.getLocalClassName());
            allActivity.put(activity.getLocalClassName(),activity);
        }
    }

    /**
     * 删除Activity
     * */
    public void removeActivity(Activity activity) {
        if (allActivity != null) {
            allActivity.remove(activity.getLocalClassName());
        }
    }

    /**
     * 通过Activity名字得到Activity
     * @param activityName Activity类的全名(包括包名)
     * */
    public Activity getActivity(String activityName){
        if(allActivity != null){
            if(allActivity.containsKey(activityName)) {
                return allActivity.get(activityName);
            }
        }
        return null;
    }

    /**
     * 销毁除主页面和登录页面外的页面（修改完登录密码后使用）
     */
//    public void finishActivitiesButMain() {
//        int size = lists.size();
//        for (int i = 0; i < size; i++) {
//            Activity activity = lists.get(i);
//            if (activity != null) {
//                if (!(activity instanceof MainActivity)) {
//                    if (!(activity instanceof LoginActivity)) {
//                        activity.finish();
//                    }
//                }
//            }
//        }
//    }
    /**
     * 销毁所有页面
     */
    public void finishActivities() {
        int size = allActivity.size();
        for (int i = 0; i < size; i++) {
            Activity activity = allActivity.valueAt(i);
            activity.finish();
        }
        allActivity.clear();
    }
}
