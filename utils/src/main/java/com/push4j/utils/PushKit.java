package com.push4j.utils;

import com.push4j.dto.PushRequestDto;

/**
 * Created by laotang on 2020/7/30.
 */
public class PushKit {

    private static class PushKitHolder {
        private static final PushKit INSTANCE = new PushKit();
    }
    private PushKit() {
    }
    public static final PushKit duang() {
        return PushKitHolder.INSTANCE;
    }

    private String account;
    private String title;
    private String content;
    private String phoneSystem;

    public PushKit account(String account) {
        this.account = account;
        return this;
    }
    public PushKit title(String title) {
        this.title = title;
        return this;
    }
    public PushKit content(String content) {
        this.content = content;
        return this;
    }
    public PushKit phoneSystem(String phoneSystem) {
        this.phoneSystem = phoneSystem;
        return this;
    }

    public void push() {

    }

    /**
     * 查询状态
     * @return
     */
    public String status() {
        return null;
    }
}
