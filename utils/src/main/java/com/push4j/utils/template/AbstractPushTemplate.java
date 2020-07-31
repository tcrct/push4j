package com.push4j.utils.template;

import com.push4j.dto.PushDataDto;
import com.push4j.utils.ToolsKit;
import org.fastboot.common.utils.SettingKit;

public abstract class AbstractPushTemplate {

    private static String ZAT_PUSH_APP_ID ;
    // 取PUSH APPID
    protected String getAppId() {
        if (ToolsKit.isEmpty(ZAT_PUSH_APP_ID)) {
            ZAT_PUSH_APP_ID = SettingKit.duang().key("push.appid").defaultValue("00000001").getString();
        }
        return ZAT_PUSH_APP_ID;
    }

    public abstract String pushData(PushDataDto dataDto);

}
