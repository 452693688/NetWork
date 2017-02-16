package com.network.common.uploading;

import com.network.common.constants.ClientInfo;
import com.network.common.uploading.able.UploadingRequsetListener;
import com.ui.utiles.other.ToastUtile;

import java.io.File;




/**
 * 图片上传
 */
public class UploadingRequest {
    private UploadingRequsetListener requsetListener;
    private RequestState requestState = new RequestState();


    public void setRequsetListener(UploadingRequsetListener requsetListener) {
        this.requsetListener = requsetListener;
    }

    public void doRequest(String path, String service) {
        if (requsetListener == null) {
            ToastUtile.showToast("not requsetListener !");
            return;
        }
        if (ClientInfo.getInstance().networkType == ClientInfo.NONET) {
            requestState.onFailed(UploadingSourceHandler.WHAT_LOCALITY_NET_WORK_ERROR, path,
                    "请检查网络 ！");
            return;
        }
        File file = new File(path);
        if (!file.exists()) {
            requestState.onFailed(UploadingSourceHandler.WHAT_DATA_FILR_ERROR, path,
                    "文件不存在");
            return;
        }
        UploadingTask task = new UploadingTask(file, service, requestState);
        NetSourceThreadPool.getInstance().execute(task);
    }

    class RequestState implements UploadingTask.OnRequestBack {

        @Override
        public void onSucess(int code, String data, String path) {
             requsetListener.RequsetDetails(code,data,path,"");
        }

        @Override
        public void onFailed(int code, String path, String msg) {
            requsetListener.RequsetDetails(code,"",path,msg);
        }
    }

}
