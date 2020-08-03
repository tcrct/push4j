package com.push4j.dto;

import java.util.Date;
import java.util.Map;

/**
 * 消息中心列表
 *
 */
public class MessageListDto {

    /**图标*/
    private String icon;
    /**标题*/
    private String title;
    /**类型：999其它，4优惠券，3交易，2活动，1业务，0系统*
     * 999是指推送后需要入库的，但又不显示到消息中心的数据
     */
    private Integer type;
    /**消息内容*/
    private String content;
    /**点击消息列表后，详情页需要用到的参数*/
    private Map<String,String> params;
    /**创建时间*/
    private Date createTime;
    /**是否已读，0是1否*/
    private Integer read;

}
