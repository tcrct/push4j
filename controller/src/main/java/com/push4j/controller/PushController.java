package com.push4j.controller;

import com.push4j.dto.PushRequestDto;
import com.push4j.service.PushService;
import org.fastboot.common.base.BaseController;
import org.fastboot.common.dto.R;
import org.fastboot.common.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 推送处理控制层
 *
 * @author Laotang
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/mpay" )
public class PushController extends BaseController<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushController.class);

    @Autowired
    private PushService pushService;

    /**
     * 接收推送消息
     * @param dto
     * @return
     */
    @RequestMapping(value = "/push", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R push(@Validated @RequestBody PushRequestDto dto) {
        try {
            return R.success(pushService.push(dto));
        } catch (Exception e) {
            LogUtils.log(LOGGER,e.getMessage(), e);
            return R.error(500, e.getMessage());
        }
    }
}
