package com.push4j.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/***
 * 点击推送消息后，需要提供的请求数据信息Dto
 *
 * @author Laotang
 * @since 1.0
 */
public class ReqDataDto {

    /**消息類型
     * 0：系統消息
     * 1：業務提示
     * 2：活動消息
     * 3：實時交易
     */
    private String msgType;
    /**点击推送提示后，需要打开页面的代号标识
     * proto:原生页面，h5:H5全屏，h5Alter:H5弹窗，redpackage:红包弹窗
     * */
    private String type;
    /**路由，告诉手机端，点击推送消息后，打开那一个页面，type为非h5时为页面标识值，h5时为一条完整的URL*/
    private String route;
    /**请求数据的参数集合*/
    private Map<String,Object> params;

    public ReqDataDto() {
    }

    public ReqDataDto(String msgType, String type, String route,Map<String, Object> params) {
        this.msgType = msgType;
        this.type = type;
        this.route = route;
        this.params = params;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
