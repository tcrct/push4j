package com.push4j.entity;

import com.push4j.utils.ToolsKit;

/**
 * 模板对象实体
 *
 * @author Laotang
 * @since 1.0
 */
public class TemplateEntity {

    /**
     * 模板类型
     */
    private String type;
    /**
     * 模板code
     */
    private String code;
    /**
     * 模板名称
     */
    private String name;
    /**
     * 模板内容
     */
    private String content;
    /**
     * 模板说明
     */
    private String desc;
    /**
     * 是否开启 0是1否
     */
    private Integer enable;

    public TemplateEntity() {
    }

    public TemplateEntity(String type, String name, String content, String desc, Integer enable) {
        this.type = type;
        this.name = name;
        this.content = content;
        this.desc = desc;
        this.enable = enable;
        this.code = ToolsKit.getCrc16(buildCrcString());
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

    private String buildCrcString() {
        return type + name + System.currentTimeMillis();
    }
}
