package com.push4j.entity;


import org.fastboot.db.annotation.Param;
import org.fastboot.db.model.BaseEntity;
import javax.validation.constraints.NotBlank;

/**
 * 推送状态实体
 * 用于定时重复发送
 *
 * @author Laotang
 * @since 1.0
 */
@org.beetl.sql.core.annotatoin.Table(name = "status")
public class StatusEntity extends BaseEntity {

    @Param(name = "account", label = "手机系统", desc = "用户帐号")
    private String account;

    @Param(name = "phoneSystem", label = "手机系统", desc = "手机系统")
    private String phoneSystem;

    @Param(name = "title", label = "标题", desc = "推送标题")
    private String title;

    @Param(name = "content", label = "推送内容", desc = "要推送的内容")
    @NotBlank(message = "推送的内容不能为空")
    private String content;

    @Param(name = "flag", label = "是否推送成功", desc = "是否推送成功，默认为成功: success")
    private String flag;

    public StatusEntity() {

    }

    public StatusEntity(String account, String phoneSystem, String title, String content, String flag) {
        this.account = account;
        this.phoneSystem = phoneSystem;
        this.title = title;
        this.content = content;
        this.flag = flag;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhoneSystem() {
        return phoneSystem;
    }

    public void setPhoneSystem(String phoneSystem) {
        this.phoneSystem = phoneSystem;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
