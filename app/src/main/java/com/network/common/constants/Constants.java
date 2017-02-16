package com.network.common.constants;


import com.ui.utiles.other.DataSave;

public class Constants {
    public static String ACCESS_NORMAL_URL = "file:///android_asset/friendly/html/index.html";
    public static String TEST_SERVICE_URL_APP = "http://yhyhgx.diandianys.com/app";
    public static String TEST_SERVICE_URL_API = "http://yhyhgx.diandianys.com/api";
    public static String SEARVICE_APP = TEST_SERVICE_URL_APP;
    public static String SEARVICE_API = TEST_SERVICE_URL_API;
    private static String TOKEN="";

    public static void setToken(String token) {
        TOKEN = token;
        DataSave.stringSave(DataSave.TOKEN,token);
    }

    public static String getToken() {
        if ("".equals(TOKEN)) {
            TOKEN = DataSave.stringGet(DataSave.TOKEN);
        }
        return TOKEN;
    }
}
