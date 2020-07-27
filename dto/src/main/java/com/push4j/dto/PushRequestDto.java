package com.push4j.dto;

import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * 模板ID
     */
    private String templateCode;
    /**
     * 变量值
     */
    private Map<String,String> valueMap = new HashMap<>();

    /**
     * 指定接收标识集合
     */
    private List<String> receiveList = new ArrayList<>();

    /**
     * 指定接收类型
     */
    private String receiveType;

    public PushRequestDto() {
    }

    public PushRequestDto(String templateCode, Map<String, String> valueMap, List<String> receiveList, String receiveType) {
        this.templateCode = templateCode;
        this.valueMap = valueMap;
        this.receiveList = receiveList;
        this.receiveType = receiveType;
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
}
