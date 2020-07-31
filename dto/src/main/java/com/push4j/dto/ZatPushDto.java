package com.push4j.dto;

/**
 * 中澳通推送内容Dto对象
 *
 * @author Laotang
 * @since 1.0
 */
public class ZatPushDto implements java.io.Serializable {

    /**消息推送id*/
    private String pushId;
    /**需要發送的用戶id*/
    private String userId;
    /**客戶端類型 1-ios 2-android*/
    private String clientType;
    /**应用id*/
    private String appId;
    /**是否群发 false-非群发 true-群发*/
    private Boolean broadCast;
    /**推送到客户端的内容Dto*/
    private ZatPushContentDto content;

    public ZatPushDto() {
    }

    public ZatPushDto(String pushId, String userId, String clientType, String appId, Boolean broadCast, ZatPushContentDto content) {
        this.pushId = pushId;
        this.userId = userId;
        this.clientType = clientType;
        this.appId = appId;
        this.broadCast = broadCast;
        this.content = content;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Boolean getBroadCast() {
        return broadCast;
    }

    public void setBroadCast(Boolean broadCast) {
        this.broadCast = broadCast;
    }

    public ZatPushContentDto getContent() {
        return content;
    }

    public void setContent(ZatPushContentDto content) {
        this.content = content;
    }
}
