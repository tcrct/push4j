package com.push4j.dto;

public class PushDataDto implements java.io.Serializable {

    private String account;
    /**推送弹窗(提示)消息**/
    private AlterDto alter = new AlterDto();
    /**消息内容，这个Dto是用来做点击消息后继的操作所需要的数据DTO，不显示用*/
    private ReqDataDto reqData = new ReqDataDto();
    /**手机系统*/
    private String phoneSystem;

    public PushDataDto() {
    }

    public PushDataDto(String account, String title, String content, String phoneSystem) {
        this.account = account;
        alter.setTitle(title);
        alter.setContent(content);
        this.phoneSystem = phoneSystem;
    }

    public AlterDto getAlter() {
        return alter;
    }

    public void setAlter(AlterDto alter) {
        this.alter = alter;
    }

    public ReqDataDto getReqData() {
        return reqData;
    }

    public void setReqData(ReqDataDto reqData) {
        this.reqData = reqData;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTitle() {
        return alter.getTitle();
    }

    public void setTitle(String title) {
        alter.setTitle(title);
    }

    public String getContent() {
        return alter.getContent();
    }

    public void setContent(String content) {
        alter.setContent(content);
    }

    public String getPhoneSystem() {
        return phoneSystem;
    }

    public void setPhoneSystem(String phoneSystem) {
        this.phoneSystem = phoneSystem;
    }


}
