package com.push4j.dto;

public class AlterDto {

    /**图标*/
    private String icon;
    /**标题*/
    private String title;
    /**提示内容*/
    private String content;

    public AlterDto() {
    }

    public AlterDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public AlterDto(String icon, String title, String content) {
        this.icon = icon;
        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
