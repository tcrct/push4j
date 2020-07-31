package com.push4j.dto;

public class PushDataDto implements java.io.Serializable {

    private String account;
    private String title;
    private String content;
    private String phoneSystem;

    public PushDataDto() {
    }

    public PushDataDto(String account, String title, String content, String phoneSystem) {
        this.account = account;
        this.title = title;
        this.content = content;
        this.phoneSystem = phoneSystem;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public String getPhoneSystem() {
        return phoneSystem;
    }

    public void setPhoneSystem(String phoneSystem) {
        this.phoneSystem = phoneSystem;
    }
}
