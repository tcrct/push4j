package com.push4j.entity;


/**
 * Hello world!
 *
 */
public class SignEntity implements java.io.Serializable {


    private String key;
    private String secret;
    private String name;
    private String desc;
    /**
     * 是否开启，0是1否
     */
    private Integer enable;

    public SignEntity() {

    }

    public SignEntity(String name, String desc) {
//        this.key = IdUtil.objectId();
//        this.secret = IdUtil.simpleUUID();
        this.name = name;
        this.desc = desc;
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
}
