package com.viosun.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveGateRequest {

    /**
     * 设备ID
     */
    @JsonProperty("STN")
    private String stn;

    /**
     * 当地时间，年月日小时分秒
     */
    @JsonProperty("LT")
    private String lt;

    /**
     * 卡号
     */
    @JsonProperty("CardNo")
    private String cardNo;

    /**
     * 姓名
     */
    @JsonProperty("FName")
    private String name;

    /**
     * 头像
     */
    @JsonProperty("IDPhoto")
    private String idPhoto;

    /**
     * 身份证
     */
    @JsonProperty("IDCard")
    private String idCard;

    /**
     * 工种
     */
    @JsonProperty("PE")
    private String pe;

    /**
     * 职务
     */
    @JsonProperty("MARK")
    private String mark;

    /**
     * 实时照片
     */
    @JsonProperty("Photo")
    private String photo;

    /**
     * 进退场标识
     */
    @JsonProperty("Status")
    private String status;

    public String getStn() {
        return stn;
    }

    public void setStn(String stn) {
        this.stn = stn;
    }

    public String getLt() {
        return lt;
    }

    public void setLt(String lt) {
        this.lt = lt;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(String idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
