package com.push4j.entity;

import org.beetl.sql.core.annotatoin.Table;
import org.fastboot.db.model.BaseEntity;

import java.util.Date;

/**
 * 推送日志记录
 * 调用第三方推送后，将日志记录保存到库
 *
 * @author Laotang
 * @since 1.0
 */
@Table(name = "message")
public class MessageEntity extends BaseEntity {

    public final static String USERID_FIELD = "userId";
    public final static String TYPE_FIELD = "type";
    public final static String TITLE_FIELD = "title";
    public final static String CONTENT_FIELD = "content";
    public final static String UNREAD_FIELD = "unRead";

    /**类型*/
    private String type;
    /**标题*/
    private String title;
    /**内容*/
    private String content;
    /**发送时间*/
    private Date sendTime;
    /**发送状态，是否成功发送到手机端，0是1否*/
    private Integer sendStatus;
    /**手机系统，android, ios*/
    private String phoneSystem;
    /**用户ID*/
    private String userId;
    /**是否已读，0是1否*/
    private Integer unRead;

    /**请求参数*/
    private String reqData;

    public MessageEntity() {
    }

    public MessageEntity(String type, String title, String content, Date sendTime, Integer sendStatus, String phoneSystem, String userId, Integer unRead) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.sendTime = sendTime;
        this.sendStatus = sendStatus;
        this.phoneSystem = phoneSystem;
        this.userId = userId;
        this.unRead = unRead;
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

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getPhoneSystem() {
        return phoneSystem;
    }

    public void setPhoneSystem(String phoneSystem) {
        this.phoneSystem = phoneSystem;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUnRead() {
        return unRead;
    }

    public void setUnRead(Integer unRead) {
        this.unRead = unRead;
    }

    public String getReqData() {
        return reqData;
    }

    public void setReqData(String reqData) {
        this.reqData = reqData;
    }
}
