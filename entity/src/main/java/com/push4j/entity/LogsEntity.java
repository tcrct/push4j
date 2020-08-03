package com.push4j.entity;

import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;

/**
 * 推送日志记录
 * 调用第三方推送后，将日志记录保存到库
 *
 * @author Laotang
 * @since 1.0
 */
@Table(name = "logs")
public class LogsEntity implements java.io.Serializable {

    /**记录ID*/
    private Integer id;
    /**类型*/
    private String type;
    /**标题*/
    private String title;
    /**内容*/
    private String content;
    /**发送时间*/
    private Date sendTime;
    /**发送状态，是否成功发送到手机端，0是1否*/
    private String sendStatus;
    /**手机系统，android, ios*/
    private String phoneSystem;

    public LogsEntity() {
    }

    public LogsEntity(Integer id, String type, String title, String content, Date sendTime, String sendStatus, String phoneSystem) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.content = content;
        this.sendTime = sendTime;
        this.sendStatus = sendStatus;
        this.phoneSystem = phoneSystem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getPhoneSystem() {
        return phoneSystem;
    }

    public void setPhoneSystem(String phoneSystem) {
        this.phoneSystem = phoneSystem;
    }
}
