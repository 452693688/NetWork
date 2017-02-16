package com.network.source.account;


import com.network.common.net.source.AbstractRequestData;
import com.network.parameter.request.LoginBeanReq;

/**
 * Created by Administrator on 2016/1/15.
 */
public class LoginReq extends AbstractRequestData<LoginBeanReq> {

    public LoginBeanReq req = new LoginBeanReq();

    @Override
    protected LoginBeanReq getData() {
        return req;
    }

    @Override
    protected String getDataStr() {
        return null;
    }

    @Override
    protected String getUrl() {
        //return Constants.SEARVICE_APP ;
        return "http://nethos.diandianys.com/api/app/";
    }

}
