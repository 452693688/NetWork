package com.network.common.uploading.able;

/**
 * Created by Administrator on 2016/1/15.
 */
public interface UploadingRequsetListener<T> {
    /**
     * 请求成功或者失败详情：
     * resultCode 业务请求响应吗；
     * responseCode 响应请求码；
     * obj 数据；
     * msg 提示信息
     **/
    public void RequsetDetails(int resultCode, String data, String path, String msg);
}
