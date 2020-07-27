package com.push4j.controller;

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
@Validated
public class PushController {

    @Autowired
    private PushService pushService;

    @PostMapping(value = "/mpay/push")
    public String push(PushRequestDto pushRequestDto) {
         pushService.push(pushRequestDto);
         return "success";
    }
}
