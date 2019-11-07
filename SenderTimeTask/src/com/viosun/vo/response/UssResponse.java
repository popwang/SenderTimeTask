package com.viosun.vo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UssResponse {

    public static final Integer NORMAL_STATUS = 0;
    public static final Integer ERROR_STATUS = -1;

    public static final Integer ERROR_STATUS_TOKEN = -10001;

    public static final Integer ERROR_STATUS_NONCESTR = -10010;
    public static final Integer ERROR_STATUS_TIMESTAMP = -10020;
    public static final Integer ERROR_STATUS_SIGN = -10030;
    public static final Integer ERROR_STATUS_APPID = -10040;

    private Integer status;

    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
