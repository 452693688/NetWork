package com.network.common.net.source;


import com.network.parameter.Paginator;

/**
 * Created by Administrator on 2016/1/15.
 */
public class AbstractResponseData {
    /** 响应码 */
    public String code;
    /** 响应消息 */
    public String msg;
    /** 页面对象 */
    public Paginator paginator;
}
