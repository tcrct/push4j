package com.push4j.entity;


import cn.hutool.core.util.IdUtil;
import org.fastboot.db.annotation.Param;
import org.fastboot.db.model.BaseEntity;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * 签名实体类
 * 每一个接入调用者都必须注册，然后分配key与secret作身份标识
 *
 * @author Laotang
 * @since 1.0
 */
@org.beetl.sql.core.annotatoin.Table(name = "sign")
public class SignEntity extends BaseEntity {

    @Param(name = "app key", label = "app key", desc = "访问关键字")
//    @NotBlank(message = "app key不能为空")
    private String key;

    @Param(name = "app secret", label = "app secret", desc = "访问安全码")
//    @NotBlank(message = "app secret不能为空")
    private String secret;

    @Param(name = "名称", label = "名称", desc = "第三方调用者名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @Param(name = "说明", label = "说明", desc = "第三方调用者说明")
    private String desc;

    /**
     * 是否开启，0是1否
     */
    @Param(name = "是否启用", label = "是否启用", desc = "是否开启，0是1否")
    @Range(min = 0, max = 1)
    private Integer enable = 0;

    public SignEntity() {

    }

    public SignEntity(String name, String desc) {
        this.key = IdUtil.objectId();
        this.secret = IdUtil.simpleUUID();
        this.name = name;
        this.desc = desc;
        this.enable = 0;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "SignEntity{" +
                "key='" + key + '\'' +
                ", secret='" + secret + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", enable=" + enable +
                '}';
    }
}
