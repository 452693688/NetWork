package com.network.uploading;


import android.os.Message;

import com.network.common.net.able.RequestBack;
import com.network.common.uploading.UploadingRequest;
import com.network.common.uploading.UploadingSourceHandler;
import com.network.parameter.result.ResultObject;
import com.ui.utiles.other.DLog;
import com.ui.utiles.other.JSONUtile;


/**
 * Created by Administrator on 2016/1/15.
 */
//上传图片
public class ImageUploadingManage extends UploadingSourceHandler<ImageLoadingData> {
    //上传图片成功
    public static final int WHAT_UPLOADING_IMAGE_SUCCED = 50;
    //上传图片失败
    public static final int WHAT_UPLOADING_IMAGE_FAILED = WHAT_UPLOADING_IMAGE_SUCCED + 1;
    private final String TAG = "ImageUploadingManage";
    private UploadingRequest netSource;

    private DataManagerListener dataManagerListener = new DataManagerListener() {
        @Override
        public Message onDealFailed(int what, ImageLoadingData imageLoadingData, String path, String msg) {
            return super.onDealFailed(WHAT_UPLOADING_IMAGE_FAILED, imageLoadingData, path, msg);
        }

        @Override
        public Message onDealSucceed(int what, ImageLoadingData imageLoadingData, String path, String msg) {
            return super.onDealSucceed(WHAT_UPLOADING_IMAGE_SUCCED, imageLoadingData, path, msg);
        }
    };

    public ImageUploadingManage(RequestBack requestBack) {
        super(requestBack);
        netSource = new UploadingRequest();
        netSource.setRequsetListener(dataManagerListener);
    }

    @Override
    protected ImageLoadingData parseResp(String json) {
        DLog.e(TAG, "解析：" + json);
        ResultObject<String> result = (ResultObject<String>) JSONUtile.json2Obj(json,
                ResultObject.class, String.class);
        ImageLoadingData data = null;
        if (result != null) {
            data = new ImageLoadingData();
            data.data = result.getObj();
            data.code = result.getCode();
            data.msg = result.getMsg();
        }
        return data;
    }

    //上传图片
    public void doRequestImage(String path) {
        netSource.doRequest(path, "yhyhgx.file.upload");
    }
}
