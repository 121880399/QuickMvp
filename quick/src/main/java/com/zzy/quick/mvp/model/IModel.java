package com.zzy.quick.mvp.model;


/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/7/17
 */

public interface IModel<T> {

    String getErrorMsg();   //后台返回的错误信息

    boolean isNull();       //空数据

    boolean isAuthError();  //验证错误

    boolean isBizError();   //业务错误


}
