package com.network.common.net.source;

import android.text.TextUtils;

import com.network.common.net.connect.URLConnectionBase;
import com.ui.utiles.other.DLog;
import com.ui.utiles.other.JSONUtile;

import org.apache.http.util.ByteArrayBuffer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;



/**
 * Created by Administrator on 2016/1/14.
 */
public class SourceTask<T extends AbstractRequestData<?>> implements Runnable {
    private int responseCode;
    private String responseMsg;
    private OnRequestBack onRequestBack;
    private T requestData;
    private byte[] request;
    private final String TAG = "SourceTask";

    public SourceTask(OnRequestBack onRequestBack, T requestData) {
        this.onRequestBack = onRequestBack;
        this.requestData = requestData;

    }

    private byte[] postMethod() {
        try {
            HttpURLConnection urlConn = (HttpURLConnection) URLConnectionBase.getURLConnection(requestData.getUrl());
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
            //设置重定向
            urlConn.setInstanceFollowRedirects(true);
            // 开始连接
            urlConn.connect();
            // 发送请求参数
            DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
            String str = "";
            str = requestData.getDataStr();
            if (TextUtils.isEmpty(str)) {
                Object data = requestData.getData();
                str = JSONUtile.obj2String(data);
            }
            str="{\"channel\":\"21\",\"oper\":\"127.0.0.1\",\"random\":\"6806\",\"sign\":\"dcec6e810c683bc0a165a302fe629d34\",\"spid\":\"1001\",\"token\":\"TOKEN_f6e46ef5594a41b59642643fcd9acc20\",\"limit\":\"30\",\"page\":0,\"deptId\":null,\"docFamousConsultStatus\":null,\"docPicConsultStatus\":null,\"docVideoConsultStatus\":null,\"hosId\":null,\"service\":\"nethos.doc.list\"}";
            DLog.e("请求:", str);
            dos.write(str.getBytes());
            dos.flush();
            dos.close();
            // 判断请求是否成功
            responseCode = urlConn.getResponseCode();
            responseMsg = urlConn.getResponseMessage();
            if (responseCode != 200) {
                return null;
            }
            // 读取数据
            /** 判断是否是GZIP **/
            boolean isGzipEncoding = false;
            String contentEncoding = urlConn.getContentEncoding();
            if (!TextUtils.isEmpty(contentEncoding)) {
                if (contentEncoding.contains("gzip")) {
                    isGzipEncoding = true;
                }
            }
            DLog.e("请求gzip:" , isGzipEncoding);
            byte[] readBuffer = new byte[1024];
            ByteArrayBuffer buffer = null;
            InputStream input = urlConn.getInputStream();
            // 如果是GZIP压缩
            if (isGzipEncoding) {
                GZIPInputStream inPutStream = new GZIPInputStream(input);
                int size = (inPutStream.available());
                buffer = new ByteArrayBuffer(size);
                int len = 0;
                while ((len = inPutStream.read(readBuffer)) != -1) {
                    buffer.append(readBuffer, 0, len);
                }
                inPutStream.close();
            } else {
                //非GZIP压缩
                 int size = (input.available());
                buffer = new ByteArrayBuffer(size);
                int len = 0;
                while ((len = input.read(readBuffer)) != -1) {
                    buffer.append(readBuffer, 0, len);
                }
                input.close();
            }
            request = str.getBytes();
            return buffer.toByteArray();
        } catch (IOException ioe) {
            responseMsg = ioe.getMessage();
            responseCode = 400;
            return null;
        }
    }

    @Override
    public void run() {
        byte[] result = postMethod();
        if (result == null) {
            DLog.e("请求失败:" + responseCode, responseMsg);
            onRequestBack.onFailed(responseCode, responseMsg);
            return;
        }
        DLog.e("请求成功:" + responseCode,  new String(result));
        onRequestBack.onSucess(responseCode, responseMsg, result, request);

    }

    public interface OnRequestBack {
        /**
         * 成功
         *
         * @param bytes 成功返回值
         */
        void onSucess(int responseCode, String responseMsg, byte[] bytes, byte[] request);

        /**
         * 失败
         */
        void onFailed(int responseCode, String responseMsg);
    }
}
