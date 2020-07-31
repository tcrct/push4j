package com.push4j.dto;

public class PushResponseDto implements java.io.Serializable {

    private Integer code;
    private String message;

    public PushResponseDto() {
        code = 200;
        message = "success";
    }

    public PushResponseDto(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
