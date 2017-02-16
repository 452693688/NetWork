package com.ui.utiles.upload;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * @author fwfw
 */
public class ReWebChomeClient extends WebChromeClient {

    private OpenFileChooserCallBack mOpenFileChooserCallBack;

    public ReWebChomeClient(OpenFileChooserCallBack openFileChooserCallBack) {
        mOpenFileChooserCallBack = openFileChooserCallBack;
    }

    @Override
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        mOpenFileChooserCallBack.openFileChooserCallBack(webView,filePathCallback,fileChooserParams);
        return  true;
    }


    public interface OpenFileChooserCallBack {
        void openFileChooserCallBack(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams);
    }

}
