package com.network.manager;


import com.network.common.net.able.RequestBack;
import com.network.common.net.source.AbstractSourceHandler;
import com.network.source.account.LoginData;
import com.network.source.account.LoginNetSource;

/**
 * Created by Administrator on 2016/1/15.
 */
//登录
public class LoginManage extends AbstractSourceHandler<LoginData> {
    private LoginNetSource netSource ;

    private DataManagerListener dataManagerListener = new DataManagerListener();

    public LoginManage(RequestBack requestBack) {
        super(requestBack);
        netSource = new LoginNetSource();
        netSource.setRequsetListener(dataManagerListener);
    }

    public void setData(String account, String password) {
        netSource.account = account;
        netSource.password = password;
    }

    public void doRequest() {
        netSource.doRequest();
    }
}
