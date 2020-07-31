package com.push4j.entity;

import org.beetl.sql.core.annotatoin.Table;
import org.fastboot.db.model.BaseEntity;

import javax.validation.constraints.NotBlank;

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
     */
    @NotBlank(message = "模板类型不能为空")
    private String type;
    /**
     * 模板code
     */
    @NotBlank(message = "模板code不能为空")
    private String code;
    /**
     * 模板名称
     */
    @NotBlank(message = "模板名称不能为空")
    private String name;
    /**
     * 模板内容
     */
    @NotBlank(message = "模板内容不能为空")
    private String content;
    /**
     * 模板说明
     */
    private String desc;
    /**
     * 是否开启 0是1否
     */
    private Integer enable;
    /**
     * 标识于这条模板是那个应用的，即SignEntity里的appKey字段
     */
    @NotBlank(message = "appKey不能为空")
    private String appKey;

    public TemplateEntity() {
    }

    public TemplateEntity(String title, String type, String name, String content, String desc, Integer enable, String appKey) {
        this.title = title;
        this.type = type;
        this.name = name;
        this.content = content;
        this.desc = desc;
        this.enable = enable;
        this.appKey = appKey;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
