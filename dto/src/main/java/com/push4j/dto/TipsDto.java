package com.push4j.dto;

/**
 * 手机接到的推送提示对象，即手机端顶部提示信息对象
 *
 * @author Laotang
 * @since 1.0
 */
public class TipsDto {

    /**推送弹窗(提示)消息**/
    private AlterDto alter;
    /**消息内容，这个Dto是用来做点击消息后继的操作所需要的数据DTO*/
    private ReqDataDto reqData;

}
