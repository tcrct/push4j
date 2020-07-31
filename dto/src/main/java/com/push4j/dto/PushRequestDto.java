package com.push4j.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "模板code字段不能为空")
    private String templateCode;
    /**
     * 变量值
     */
    @NotNull
    private Map<String,String> valueMap;

    /**
     * 指定接收标识集合
     */
    private List<String> receiveList;

    /**
     * 指定接收类型
     */
    private String receiveType;

    public PushRequestDto() {
    }

    public PushRequestDto(String templateCode, String title, Map<String, String> valueMap, List<String> receiveList, String receiveType) {
        this.templateCode = templateCode;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
