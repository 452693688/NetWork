package com.ui.utiles.other;

import android.util.Log;

/**
 * Created by Administrator on 2016/3/9.
 */
public class DLog {
    public static boolean DBUG = true;

    public static void e(String tag, Object value) {
        if (!DBUG) {
            return;
        }
        if ("BluetoothReadService".equals(tag)) {
            return;
        }
        Log.e(tag, value + "");
    }
}