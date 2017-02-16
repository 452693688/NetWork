package com.network.parameter.result.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysDept implements Serializable {
    /**科室ID*/
    public Integer deptId;
    /**科室名称*/
    public String deptName;
    /**科室简介*/
    public String deptContent;
    /**医院ID*/
    public Integer hosId;
    /**标准科室代码*/
    public String gbDeptCode;

    public Boolean enable;

    public Date createTime;

    public Date modifyTime;

}
