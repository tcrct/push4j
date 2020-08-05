package com.push4j.dto;

import java.util.Date;
import java.util.Map;

/**
 * 消息中心列表
 *
 */
public class MessageDto {

    /**图标*/
    private String icon;
    /**标题*/
    private String title;
    /**类型：999其它，4优惠券，3交易，2活动，1业务，0系统*
     * 999是指推送后需要入库的，但又不显示到消息中心的数据
     */
    private String type;
    /**消息内容*/
    private String content;
    /**点击消息列表后，详情页需要用到的参数*/
    private Map<String,String> params;
    /**创建时间*/
    private Date createTime;
    /**是否未读，0是1否*/
    private Integer unRead;

    private ReqDataDto reqDataDto;

    public MessageDto() {

    }

    public MessageDto(String title, String type, String content, Date createTime, Integer unRead) {
        this(null, title, type, content,null,createTime,unRead);
    }

    public MessageDto(String icon, String title, String type, String content, Map<String, String> params, Date createTime, Integer unRead) {
        this.icon = icon;
        this.title = title;
        this.type = type;
        this.content = content;
        this.params = params;
        this.createTime = createTime;
        this.unRead = unRead;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUnRead() {
        return unRead;
    }

    public void setUnRead(Integer unRead) {
        this.unRead = unRead;
    }

    public ReqDataDto getReqDataDto() {
        return reqDataDto;
    }

    public void setReqDataDto(ReqDataDto reqDataDto) {
        this.reqDataDto = reqDataDto;
    }
}
