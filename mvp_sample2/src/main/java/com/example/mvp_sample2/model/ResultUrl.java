package com.example.mvp_sample2.model;

public class ResultUrl {
    private String message;
    private String code;
    private ResultFormat result;

    public ResultUrl(String message, String code, ResultFormat result) {
        this.message = message;
        this.code = code;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResultFormat getResult() {
        return result;
    }

    public void setResult(ResultFormat result) {
        this.result = result;
    }
}
