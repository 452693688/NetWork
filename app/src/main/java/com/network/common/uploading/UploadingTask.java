package com.network.common.uploading;

import android.text.TextUtils;

import com.network.common.constants.Constants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;




public class UploadingTask implements Runnable {

    protected Map<String, String> params = new HashMap<String, String>();
    private File file;
    private OnRequestBack onReslutListener;
    private String service;
    private String msg = "";

    public UploadingTask(File file, String service,
                         OnRequestBack onReslutListener) {
        this.file = file;
        this.service = service;
        this.onReslutListener = onReslutListener;
    }

    private void init() {
        String spid = "1000";
        String oper = "127.0.0.1";
        String channel = "21";
        String random = "1234";
        String pwd = "ahigZ8M3";
        String sign = "";

        params.put("spid", spid);
        params.put("channel", channel);
        params.put("random", random);
        params.put("sign", sign);
        params.put("format", "JSON");
        params.put("oper", oper);

    }


    /**
     * 文件上传
     *
     * @param service
     */
    public String execute(File file, String service) {
        String data = null;
        init();
        params.put("service", service);
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost post = new HttpPost(Constants.SEARVICE_APP);
            Map<String, File> files = new HashMap<String, File>();
            files.put("file", file);
            List<NameValuePair> pairs = new ArrayList<NameValuePair>(
                    params.size());
            for (Entry<String, String> entry : params.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
            post.setEntity(makeMultipartEntity(pairs, files));
            HttpResponse response = httpclient.execute(post);
            int code = response.getStatusLine().getStatusCode();
            if (code != 200) {
                return "";
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // System.out.println(" in -> " + params);
                data = EntityUtils.toString(entity);
                post.abort();
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "服务器连接失败！";
        }
        return data;
    }

    private HttpEntity makeMultipartEntity(List<NameValuePair> params,
                                           Map<String, File> files) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE); // 如果有SocketTimeoutException等情况，可修改这个枚举
        // builder.setCharset(Charset.forName("UTF-8")); //不要用这个，会导致服务端接收不到参数
        if (params != null && params.size() > 0) {
            for (NameValuePair p : params) {
                builder.addTextBody(p.getName(), p.getValue(),
                        ContentType.TEXT_PLAIN.withCharset("UTF-8"));
            }
        }
        if (files != null && files.size() > 0) {
            Set<Entry<String, File>> entries = files.entrySet();
            for (Entry<String, File> entry : entries) {
                builder.addPart(entry.getKey(), new FileBody(entry.getValue()));
            }
        }
        return builder.build();
    }

    @Override
    public void run() {
        String data = execute(file, service);
        if (TextUtils.isEmpty(data)) {
            // 失败
            onReslutListener.onFailed(UploadingSourceHandler.WHAT_NET_WORK_LINK_SERVE_FAILED,
                    file.getPath(), msg);
        } else {
            // 成功
            onReslutListener.onSucess(UploadingSourceHandler.WHAT_NET_WORK_LINK_SERVE_SUCCEED,
                    data, file.getPath());
        }
    }

    /**
     * 结果监听
     */
    public interface OnRequestBack {

        /**
         * 成功
         *
         * @param path
         */
        void onSucess(int code, String data, String path);

        /**
         * 失败
         */
        void onFailed(int code, String path, String msg);
    }
}
