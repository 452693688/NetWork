package com.network.parameter.request;


import com.network.parameter.BaseRequest;

/**
 * Created by Administrator on 2016/4/8.
 */
public class LoginBeanReq extends BaseRequest {
    private String service = "yhyhgx.user.login";
    private String userMobile;
    private String userPassword;

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
