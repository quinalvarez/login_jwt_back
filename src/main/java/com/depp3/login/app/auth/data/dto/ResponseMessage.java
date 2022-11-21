package com.depp3.login.app.auth.data.dto;

public class ResponseMessage {

    private String message;
    private String payload;
    private Integer code;
    private String internalMessage;

    public ResponseMessage(String message, String payload, Integer code, String internalMessage) {
        this.message = message;
        this.payload = payload;
        this.code = code;
        this.internalMessage = internalMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInternalMessage() {
        return internalMessage;
    }

    public void setInternalMessage(String internalMessage) {
        this.internalMessage = internalMessage;
    }
}
