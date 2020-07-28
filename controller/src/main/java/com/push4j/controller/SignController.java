package com.push4j.controller;

import com.push4j.dto.PushRequestDto;
import com.push4j.entity.SignEntity;
import com.push4j.service.PushService;
import com.push4j.service.SignService;
import org.fastboot.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册第三方调用APP控制层
 *
 * @author Laotang
 * @since 1.0
 *
 */
@RestController
@RequestMapping(value = "/sign" )
public class SignController extends BaseController<SignEntity> {

    @Autowired
    private SignService signService;

    @PostMapping(value = "/push",  produces = "application/json")
    public String push(@Validated @RequestBody SignEntity dto) {

         return "success";
    }
}
