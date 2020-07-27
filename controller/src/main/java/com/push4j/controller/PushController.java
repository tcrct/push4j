package com.push4j.controller;

import cn.hutool.http.Method;
import com.push4j.dto.PushRequestDto;
import com.push4j.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Hello world!
 *
 */
@RestController
@RequestMapping("/mpay" )
@Validated
public class PushController {

    @Autowired
    private PushService pushService;

    @RequestMapping(value = "/push", method = RequestMethod.GET)
    public String push(String pushRequestDto) {
         pushService.push(pushRequestDto);
         return "success";
    }
}
