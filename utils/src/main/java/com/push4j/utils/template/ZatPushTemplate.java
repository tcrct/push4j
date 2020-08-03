package com.push4j.utils.template;

import cn.hutool.core.util.IdUtil;
import com.push4j.dto.PushDataDto;
import com.push4j.dto.ZatPushContentDto;
import com.push4j.dto.ZatPushDto;
import com.push4j.utils.ToolsKit;

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
        contentDto.setActUrl("http://127.0.0.1/acturl/123");
        contentDto.setImgUrl("http://127.0.0.1/imgurl/123");
        contentDto.setCont(dataDto.getContent());

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
