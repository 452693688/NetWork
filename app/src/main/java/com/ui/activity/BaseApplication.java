package com.ui.activity;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/1/15.
 */
public class BaseApplication extends Application {
    /**
     * true 打印日志
     */
    public static boolean IS_DEBUG = false;
    public static BaseApplication baseApplication;

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        context = this;
        //CrashHandler.getInstance().init();
        /* Picasso picasso = new Picasso.Builder(this).downloader(
                new OkHttpDownloader(new File(FileUtile.getImageCachePathPrivate())))
                .build();
        Picasso.setSingletonInstance(picasso);*/
    }


}
