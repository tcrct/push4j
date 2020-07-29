package com.push4j.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.push4j.dto.PushRequestDto;
import com.push4j.service.PushService;
import org.fastboot.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.util.IOUtils;

/**
 * Hello world!
 *
 */
@RestController
@RequestMapping(value = "/mpay" )
public class PushController extends BaseController<Object> {

    @Autowired
    private PushService pushService;

    @RequestMapping(value = "/push", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String push(@Validated @RequestBody PushRequestDto dto) {
         pushService.push(dto);
         return "success";
    }
}
