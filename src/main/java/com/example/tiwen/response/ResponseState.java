package com.example.tiwen.response;

/**
 * @author XUJIAYIN
 * @create 2022-02-08-21:41
 */
public enum ResponseState {
    SUCCESS(true,200,"操作成功"),
    FAILED(false,400,"操作失败");

    ResponseState(boolean success,int code,String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    private int code;
    private String message;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
