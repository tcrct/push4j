package com.push4j.dto;

import java.util.List;
import java.util.Map;

/**
 * 推送请求对象
 */
public class PushRequestDto implements java.io.Serializable {

    public static final String TEMPLATE_CODE_FIELD = "templateCode";
    public static final String VALUE_MAP_FIELD = "valueMap";
    public static final String RECEIVE_LIST_FIELD = "receiveList";
    public static final String RECEIVE_TYPE_FIELD = "receiveType";
    public static final String TITLE_FIELD = "title";
    public static final String CONTENT_FIELD = "content";

    /**
     * 标题
     */
    private String title;
    /**
     * 模板ID
     */
    private String templateCode;
    /**
     * 变量值
     */
    private Map<String,String> valueMap;

    /**
     * 指定接收标识集合
     */
    private List<String> receiveList;

    /**
     * 指定接收类型
     */
    private String receiveType;

    /**
     * 与用户对应的手机系统,
     */
    private Map<String,String> phoneSystem;

    /**
     * 扩展参数的map
     */
    private Map<String,Object> extMap;


    public PushRequestDto() {
    }

    public PushRequestDto(String templateCode, String title, Map<String, String> valueMap, List<String> receiveList, String receiveType, Map<String,String> phoneSystem, Map<String,Object> extMap) {
        this.templateCode = templateCode;
        this.title = title;
        this.valueMap = valueMap;
        this.receiveList = receiveList;
        this.receiveType = receiveType;
        this.phoneSystem = phoneSystem;
        this.extMap = extMap;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public Map<String, String> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<String, String> valueMap) {
        this.valueMap = valueMap;
    }

    public List<String> getReceiveList() {
        return receiveList;
    }

    public void setReceiveList(List<String> receiveList) {
        this.receiveList = receiveList;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, Object> getExtMap() {
        return extMap;
    }

    public void setExtMap(Map<String, Object> extMap) {
        this.extMap = extMap;
    }

    public Map<String,String> getPhoneSystem() {
        return phoneSystem;
    }

    public void setPhoneSystem(Map<String,String> phoneSystem) {
        this.phoneSystem = phoneSystem;
    }
}
