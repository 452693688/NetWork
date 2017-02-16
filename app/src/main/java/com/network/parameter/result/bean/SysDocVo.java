package com.network.parameter.result.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysDocVo implements Serializable {

    public SysDoc sysDoc;
    public SysDept sysDept;
}
