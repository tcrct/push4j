package com.push4j.service;

import com.push4j.dto.PushRequestDto;
import org.springframework.stereotype.Service;

/**
 * Hello world!
 *
 */
@Service
public class PushService {

    public void push(PushRequestDto pushRequestDto) {
        System.out.println(pushRequestDto.hashCode());
    }


    public void push(String pushRequestDto) {
        System.out.println(pushRequestDto.hashCode());
    }
}
