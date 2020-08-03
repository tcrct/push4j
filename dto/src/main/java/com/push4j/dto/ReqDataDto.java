package com.push4j.dto;

import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/***
 * 点击推送消息后，需要提供的请求数据信息Dto
 *
 * @author Laotang
 * @since 1.0
 */
public class ReqDataDto {

    /**点击推送提示后，需要打开页面的代号标识
     * 0:原生页面，1:H5全屏，2:H5弹窗，3:红包弹窗
     * */
    private Integer type;
    /**手机端页面代号，例如主页或消息中心的页面代号，当type=0时，该值不能为空*/
    private String pageName;
    /**请求数据的请求uri地址**/
    private String uri;
    /**请求数据的参数集合*/
    private Map<String,String> params;
    /**请求方式，默认为post*/
    private String method = RequestMethod.POST.name().toLowerCase();

}
