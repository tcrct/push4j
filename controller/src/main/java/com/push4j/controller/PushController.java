package com.push4j.controller;

import com.push4j.dto.PushRequestDto;
import com.push4j.service.PushService;
import org.fastboot.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Hello world!
 *
 */
@RestController
@RequestMapping(value = "/mpay" )
public class PushController extends BaseController<Object> {

    @Autowired
    private PushService pushService;

    @PostMapping(value = "/push",  produces = "application/json")
    public String push(@Validated @RequestBody PushRequestDto dto) {
         pushService.push(dto);
         return "success";
    }
}
