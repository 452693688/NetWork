package com.network.common.uploading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.network.common.net.able.RequestBack;
import com.network.common.net.source.AbstractResponseData;
import com.network.common.uploading.able.UploadingRequsetListener;

import java.lang.ref.SoftReference;


/**
 * Created by Administrator on 2016/1/15.
 */
public abstract class UploadingSourceHandler<T extends AbstractResponseData> extends Handler {
    SoftReference<RequestBack> requestBacks = null;

    public UploadingSourceHandler(RequestBack requestBack) {
        requestBacks = new SoftReference<RequestBack>(requestBack);
    }

    /**
     * 转化Resp
     */
    protected abstract T parseResp(String json);

    private RequestBack getRequestBack() {
        if (requestBacks == null) {
            return null;
        }
        RequestBack requestBack = requestBacks.get();
        return requestBack;
    }

    @Override
    public void handleMessage(Message message) {
        RequestBack requestBack = getRequestBack();
        if (requestBack == null) {
            return;
        }
        Bundle bundle = message.getData();
        String msg = bundle.getString("msg");
        String path = bundle.getString("path");
        requestBack.OnBack(message.what, message.obj, msg, path);
    }

    private Message getMessage(int what, T t, String path, String msg) {
        Message message = this.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString("msg", msg);
        bundle.putString("path", path);
        message.what = what;
        message.obj = t;
        message.setData(bundle);
        return message;
    }

    public class DataManagerListener implements UploadingRequsetListener<T> {
        @Override
        public void RequsetDetails(int resultCode, String data, String path, String msg) {
            T t = null;
            if (resultCode == WHAT_NET_WORK_LINK_SERVE_SUCCEED) {
                t = parseResp(data);
            }
            if (resultCode == WHAT_LOCALITY_NET_WORK_ERROR) {
                dataAnalysis(WHAT_LOCALITY_NET_WORK_ERROR, null, path, "请检查网络！");
                return;
            }
            if (resultCode == WHAT_NET_WORK_LINK_SERVE_FAILED) {
                dataAnalysis(WHAT_LOCALITY_NET_WORK_ERROR, null, path, "连接服务器失败！");
                return;
            }
            if (t != null && ("0".equals(t.code))) {
                dataAnalysis(WHAT_DEAL_SUCCEED, t, path, "操作成功！");
                return;
            }
            if (t == null && TextUtils.isEmpty(data)) {
                dataAnalysis(WHAT_DATA_ERROR, null, path, "数据解析失败！");
                return;
            }
            dataAnalysis(WHAT_DATA_ERROR, null, path, msg);
        }

        //解析数据
        private void dataAnalysis(int resultCode, T t, String path, String msg) {
            Message message = null;
            switch (resultCode) {
                case WHAT_DEAL_SUCCEED:
                    message = onDealSucceed(resultCode, t, path, msg);
                    break;
                case WHAT_DEAL_FAILED:
                    message = onDealFailed(resultCode, t, path, msg);
                    break;
                default:
                    message = getMessage(resultCode, t, path, msg);
                    break;
            }
            sendMessageDelayed(message, 100);
        }

        public Message onDealSucceed(int what, T t, String path, String msg) {
            return getMessage(what, t, path, msg);
        }

        public Message onDealFailed(int what, T t, String path, String msg) {
            return getMessage(what, t, path, msg);
        }
    }

    /**
     * 访问网络失败 没有开启WiFi，移动网络等
     */
    public static final int WHAT_LOCALITY_NET_WORK_ERROR = 101;
    /**
     * 连接服务器成功
     */
    public static final int WHAT_NET_WORK_LINK_SERVE_SUCCEED = 102;
    /**
     * 连接服务器失败
     */
    public static final int WHAT_NET_WORK_LINK_SERVE_FAILED = 103;
    /**
     * 访问网络成功
     */
    public static final int WHAT_NET_REQUEST_SUCCEED = 200;

    /**
     * 业务处理成功
     */
    public static final int WHAT_DEAL_SUCCEED = 300;
    /**
     * 业务操作失败
     */
    public static final int WHAT_DEAL_FAILED = 301;
    /**
     * json转换类失败
     */
    public static final int WHAT_DATA_ERROR = 302;
    /**
     * 文件不存在
     */
    public static final int WHAT_DATA_FILR_ERROR = 302;
    /**
     * token失效或不能为空
     */
    public static final int WHAT_DATA_TOKEN_ERROR = 306;


}
