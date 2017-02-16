package com.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ui.activity.BaseApplication;


public class NetStateReceiver extends BroadcastReceiver {
    private static int NET_STATE = 0;
    public static final int NET_STATE_NONET = 1;
    public static final int NET_STATE_WIFI = NET_STATE_NONET + 1;
    public static final int NET_STATE_G = NET_STATE_WIFI + 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        netState(context);
    }

    private static void netState(Context context) {
        // 判断网络状态
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager == null) {
            return;
        }
        // 获取代表联网状态的NetWorkInfo对象
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return;
        }
        // 获取当前的网络连接是否可用
        boolean available = networkInfo.isAvailable();
        if (!available) {
            NET_STATE = NET_STATE_NONET;
            return;
        }
        if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            //WIFI网
            NET_STATE = NET_STATE_WIFI;
            return;
        }
        if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            //3G网
            NET_STATE = NET_STATE_G;
            return;
        }
// PushManager.getInstance().initialize(baseApplication);
    }

    public static int getNetWorkState() {
        if (NET_STATE == 0) {
            netState(BaseApplication.baseApplication);
        }
        if (NET_STATE == 0) {
            NET_STATE = NET_STATE_NONET;
        }
        return NET_STATE;
    }
}