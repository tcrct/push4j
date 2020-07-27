package com.push4j.controller;

import com.push4j.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Hello world!
 *
 */
@RestController
@RequestMapping(value = "/mpay" )
@Validated
public class PushController {

    @Autowired
    private PushService pushService;

    @GetMapping(value = "/push")
    public String push(String pushRequestDto) {
         pushService.push("laotang");
         return "success";
    }
}
