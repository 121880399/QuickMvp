package com.zzy.quick.utils.log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/14
 * 使用Logger开源框架来打印日志
 */

public class LoggerUtil implements LogUtil {


    @Override
    public void d(String tag, String msg) {
        Logger.t(tag).d(msg);
    }

    @Override
    public void d(String msg) {
        Logger.d(msg,new Object[]{});
    }

    @Override
    public void i(String tag, String msg) {
        Logger.t(tag).i(msg);
    }

    @Override
    public void i(String msg) {
        Logger.i(msg,new Object[]{});
    }

    @Override
    public void v(String tag, String msg) {
        Logger.t(tag).v(msg);
    }

    @Override
    public void v(String msg) {
        Logger.v(msg,new Object[]{});
    }

    @Override
    public void w(String tag, String msg) {
        Logger.t(tag).w(msg);
    }

    @Override
    public void w(String msg) {
        Logger.w(msg,new Object[]{});
    }

    @Override
    public void e(String tag, String msg) {
        Logger.t(tag).e(msg);
    }

    @Override
    public void e(String msg) {
        Logger.e(msg,new Object[]{});
    }

    @Override
    public void e(Throwable throwable, String msg, Object... args) {
        Logger.e(throwable,msg,args);
    }

    @Override
    public void json(String json) {
        Logger.json(json);
    }

    @Override
    public void xml(String xml) {
        Logger.xml(xml);
    }

    @Override
    public void map(Object map) {
        Logger.d(map);
    }

    @Override
    public void set(Object set) {
        Logger.d(set);
    }

    @Override
    public void list(Object list) {
        Logger.d(list);
    }

    @Override
    public void array(Object array) {
        Logger.d(array);
    }

    @Override
    public void isLogAble(boolean isLog) {
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return isLog;
            }
        });
    }

    @Override
    public void setGlobalTag(String tag) {
        FormatStrategy formatStrategy= PrettyFormatStrategy.newBuilder()
                .tag(tag).build();
        Logger.clearLogAdapters();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }
}
