package com.zzy.quick.utils.log;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/14
 */

public interface LogUtil {

    public void d(String tag,String msg);

    public void d(String msg);

    public void i(String tag,String msg);

    public void i(String msg);

    public void v(String tag,String msg);

    public void v(String msg);

    public void w(String tag,String msg);

    public void w(String msg);

    public void e(String tag,String msg);

    public void e(String msg);

    public void e(Throwable throwable,String msg,Object... args);

    public void json(String json);

    public void xml(String xml);

    public void map(Object map);

    public void set(Object set);

    public void list(Object list);

    public void array(Object array);


    /**
     * 设置是否打印log
     * */
    public void isLogAble(boolean isLog);

    /**
     * 设置全局Tag
     * */
    public void setGlobalTag(String tag);


}
