package com.push4j.service;

import com.push4j.dto.PushRequestDto;
import com.push4j.utils.ToolsKit;
import org.springframework.stereotype.Service;

/**
 * Hello world!
 *
 */
@Service
public class PushService {

    public void push(PushRequestDto pushRequestDto) {
        System.out.println(ToolsKit.toJsonString(pushRequestDto));
    }


}
