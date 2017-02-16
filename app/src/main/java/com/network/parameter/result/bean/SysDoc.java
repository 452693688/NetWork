package com.network.parameter.result.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysDoc implements Serializable {
    /**医生编号*/
    public Integer docId;
    /**医生姓名*/
    public String docName;
    /**医生头像*/
    public String docAvatar;
    /**性别*/
    public String docGender;
    /**手机号码*/
    public String docMobile;
    /**医院编号*/
    public Integer docHosId;
    /**科室编号*/
    public Integer docDeptId;
    /**医生职称*/
    public String docTitle;
    /**身份证号*/
    public String docIdcard;
    /**密码*/
    public String docPassword;
    /**医生擅长*/
    public String docSkill;
    /**医生介绍*/
    public String docResume;
    /**评分*/
    public Double docScoure;
    /**预约挂号状态*/
    public Boolean docBookStatus;
    /**预约挂号id*/
    public String docBookId;
    /**图文问诊开通状态*/
    public Boolean docPicConsultStatus;
    /**图文问诊价格*/
    public Double docPicConsultPrice;
    /**视频问诊开通状态*/
    public Boolean docVideoConsultStatus;
    /**视频问诊价格*/
    public Double docVideoConsultPrice;
    /**名医开通状态*/
    public Boolean docFamousConsultStatus;
    /**名医价格*/
    public Double docFamousConsultPrice;
    /**名片二维码*/
    public String docQrcodeUrl;
    /**身份证照片地址*/
    public String docIdcardUrl;
    /**执业证照片*/
    public String docLicenceUrl;
    /**职称照片*/
    public String docTitleUrl;
    /**医生状态（-1锁定 0待审核 1审核通过）*/
    public String docStatus;
    /**审核状态*/
    public Date auditTime;
    /**创建时间*/
    public Date createTime;
    /**修改时间*/
    public Date modifyTime;

    public Boolean isAss;

}
