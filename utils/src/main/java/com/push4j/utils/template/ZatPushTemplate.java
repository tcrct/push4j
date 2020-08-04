package com.push4j.utils.template;

import cn.hutool.core.util.IdUtil;
import com.push4j.dto.*;
import com.push4j.utils.ToolsKit;

import java.util.HashMap;
import java.util.Map;

/**
 * 中澳通推送数据组装模板
 *
 * @author Laotang
 * @since 1.0
 */
public class ZatPushTemplate extends AbstractPushTemplate{

    public ZatPushTemplate() {
    }

    @Override
    public String pushData(PushDataDto dataDto) {

        ZatPushContentDto contentDto = new ZatPushContentDto();
        contentDto.setUnread("0");
        contentDto.setActUrl("");
        contentDto.setImgUrl("");
        Map<String, Object> contMap = new HashMap<>();
        contMap.put("alert", dataDto.getAlter());
        contMap.put("reqData", dataDto.getReqData());

//        contentDto.setCont(ToolsKit.toJsonString(contMap));
        contentDto.setCont(contMap);

        return ToolsKit.toJsonString(builderZatPushDto(dataDto, contentDto));
    }
    private ZatPushDto builderZatPushDto(PushDataDto dataDto, ZatPushContentDto contentDto) {
        ZatPushDto zatPushDto = new ZatPushDto();
        zatPushDto.setPushId(IdUtil.objectId());
        zatPushDto.setUserId(dataDto.getAccount());
        zatPushDto.setClientType("android".equals(dataDto.getPhoneSystem().toLowerCase()) ? "2" : "1");
        zatPushDto.setAppId(getAppId());
        zatPushDto.setContent(contentDto);
        return zatPushDto;
    }
}
