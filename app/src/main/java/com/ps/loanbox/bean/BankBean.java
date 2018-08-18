package com.ps.loanbox.bean;

/**
 * Created by 8146 on 2018/1/19.
 * 银行 -模型类
 */

public class BankBean {

    /**
     * bank : 中国银行
     * id : 1
     * pic : /test/test.jpg
     * queryUrl : www.adyun.com
     */

    private String bank;
    private int id;
    private String pic;
    private String queryUrl;
    private int isShow;
    private int isDel;
    private String label;

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

}
