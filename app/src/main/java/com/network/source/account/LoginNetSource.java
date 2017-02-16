package com.network.source.account;


import com.network.common.net.source.AbstractSourceRTS;
import com.network.parameter.result.ResultListObject;
import com.network.parameter.result.bean.SysDocVo;
import com.ui.utiles.other.DLog;
import com.ui.utiles.other.JSONUtile;

/**
 * Created by Administrator on 2016/1/15.
 */
public class LoginNetSource extends AbstractSourceRTS<LoginData, LoginReq> {
    private final String TAG = "LoginNetSource";
    public String account;
    public String password;

    @Override
    protected LoginReq getRequest() {
        LoginReq req = new LoginReq();
        req.req.setUserMobile(account);
        req.req.setUserPassword(password);
        return req;
    }

    @Override
    protected LoginData parseResp(byte[] bytes, byte[] request) {
        String json = new String(bytes);
        DLog.e(TAG, "-----" + json);
    /*    ResultObject<SysDocVo> result = (ResultObject<SysDocVo>) JSONUtile.json2Obj(json,
                ResultObject.class, SysDocVo.class);*/
        ResultListObject<SysDocVo> result = (ResultListObject<SysDocVo>) JSONUtile.json2Obj(json,
                ResultListObject.class, SysDocVo.class);
        LoginData data = null;
        if (result != null) {
            data = new LoginData();
            data.code = result.getCode();
            data.msg = result.getMsg();
            data.data = result.getList();
            data.token = result.getToken();
        }
        return data;
    }
}
