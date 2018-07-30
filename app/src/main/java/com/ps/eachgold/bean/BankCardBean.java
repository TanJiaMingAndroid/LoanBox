package com.ps.eachgold.bean;

/**
 * Created by 8146 on 2018/1/31.
 * 银行卡 -模型类
 */

public class BankCardBean {


    /**
     * bankId : 1
     * createdAt : 2018-01-31 19:10:57
     * id : 1
     * isDel : 1
     * isHot : 0
     * isShow : 1
     * name : 吉利白金信用卡
     * pic : /product/bank/pufa/pufa01.png
     * presentation : 一张可以资金提取、随借随还的高额度信用卡
     * sort : 0
     * url : https://ecentre.spdbccc.com.cn/creditcard/toBasicInfo.htm
     */

    private int bankId;
    private String createdAt;
    private int id;
    private int isDel;
    private int isHot;
    private int isShow;
    private String name;
    private String pic;
    private String presentation;
    private int sort;
    private String url;

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
