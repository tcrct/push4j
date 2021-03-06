package com.push4j.entity;

import org.beetl.sql.core.annotatoin.Table;
import org.fastboot.db.model.BaseEntity;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 模板对象实体
 *
 * @author Laotang
 * @since 1.0
 */
@Table(name = "template")
public class TemplateEntity extends BaseEntity {

    /**
     * 标题
     */
    private String title;
    /**
     * 模板类型
     * 0：系統消息
     *1：業務提示
     *2：活動消息
     *3：實時交易
     */
    @NotBlank(message = "模板类型不能为空")
    private String type;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 模板code
     */
    @NotBlank(message = "模板code不能为空")
    private String code;
    /**
     * 模板内容
     */
    @NotBlank(message = "中文模板内容不能为空")
    private String content;

    /**
     * 英文模板标题
     */
    private String enTitle;
    /**
     * 英文模板内容
     */
    private String enContent;
    /**
     * 模板说明
     */
    private String desc;
    /**
     * 是否开启 0是1否
     */
    private Integer enable;

    /**路由，告诉手机端，点击推送消息后，打开那一个页面，type为非h5时为页面标识值，h5时为一条完整的URL*/
    private String route;
    /**打开方式，分 proto:原生页面，h5:H5全屏，h5Alter:H5弹窗，redpackage:红包弹窗*/
    private String openType;


    /**
     * 标识于这条模板是那个应用的，即SignEntity里的appKey字段
     */
    @NotBlank(message = "appKey不能为空")
    private String appKey;

    /**审核人ID*/
    private Integer approveUserid;
    /**审核时间*/
    private Date approveTime;

    public TemplateEntity() {
    }

    public TemplateEntity(String title, String businessType, String type, String content, String enTitle,String enContent, String desc, Integer enable, String appKey, String route, String openType) {
        this.title = title;
        this.businessType = businessType;
        this.type = type;
        this.content = content;
        this.enTitle = enTitle;
        this.enContent =enContent;
        this.desc = desc;
        this.enable = enable;
        this.appKey = appKey;
        this.route = route;
        this.openType = openType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Integer getApproveUserid() {
        return approveUserid;
    }

    public void setApproveUserid(Integer approveUserid) {
        this.approveUserid = approveUserid;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle;
    }

    public String getEnContent() {
        return enContent;
    }

    public void setEnContent(String enContent) {
        this.enContent = enContent;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
