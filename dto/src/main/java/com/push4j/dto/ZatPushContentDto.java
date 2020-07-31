package com.push4j.dto;

/**
 * 中澳通推送Dto内容的主体部分
 * 如有需要，可以自由添加字段，但不能减少字段，不影响接收
 *
 * @author Laotang
 * @since 1.0
 */
public class ZatPushContentDto {

    /**未读数*/
    private String unread;
    /**H5 跳转URL*/
    private String act_url;
    /**图片URL*/
    private String img_url;
    /**消息內容*/
    private String cont;

    public ZatPushContentDto() {
    }

    public ZatPushContentDto(String unread, String act_url, String img_url, String cont) {
        this.unread = unread;
        this.act_url = act_url;
        this.img_url = img_url;
        this.cont = cont;
    }

    public String getUnread() {
        return unread;
    }

    public void setUnread(String unread) {
        this.unread = unread;
    }

    public String getActUrl() {
        return act_url;
    }

    public void setActUrl(String act_url) {
        this.act_url = act_url;
    }

    public String getImgUrl() {
        return img_url;
    }

    public void setImgUrl(String img_url) {
        this.img_url = img_url;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }
}
