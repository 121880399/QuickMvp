package com.zzy.quick.utils.log;


/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/14
 * Log工厂，用于生成Log工具。
 */

public class LogFactory {
    private static LogUtil logUtil;

    public static LogUtil getLogUtil(){
        if(logUtil==null){
            synchronized (LogFactory.class){
                if(logUtil==null){
                    logUtil=new LoggerUtil();
                }
            }
        }
        return logUtil;
    }
}
