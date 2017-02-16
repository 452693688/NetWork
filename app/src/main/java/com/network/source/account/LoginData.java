package com.network.source.account;


import com.network.common.net.source.AbstractResponseData;
import com.network.parameter.result.bean.SysDocVo;

import java.util.List;

/**
 * Created by Administrator on 2016/1/15.
 */
public class LoginData extends AbstractResponseData {
    public List<SysDocVo> data;
    public String token;
}
