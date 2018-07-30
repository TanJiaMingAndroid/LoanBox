package com.ps.eachgold.bean;

/**
 * Created by 8146 on 2018/2/1.
 */

public class CustomBean {
    /**
     * id : 2
     * name : 给你花
     * type : 1
     * presentation : 3分钟填资料，信用额度最高10000元
     * minInterest : 0.027
     * maxInterest : 0.027
     * minMoney : 500
     * maxMoney : 10000
     * lendingTime : 0.08
     * mortgage : 4
     * exclusive : 1
     * pic : /product/loan/geinihua.png
     * url : https://h5.geinihua.com/html/common/landingpage.html?channel=kakadai1-landingpage
     * isHot : 1
     * isShow : 1
     * isDel : 1
     * sort : 0
     * areaId : 0
     * createdAt : 1517402518000
     * updatedAt : 1517402518000
     */

    private int id;
    private String name;
    private int type;
    private String presentation;
    private double minInterest;
    private double maxInterest;
    private int minMoney;
    private int maxMoney;
    private double lendingTime;
    private int mortgage;
    private int exclusive;
    private String pic;
    private String url;
    private int isHot;
    private int isShow;
    private int isDel;
    private int sort;
    private int areaId;
    private long createdAt;
    private long updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public double getMinInterest() {
        return minInterest;
    }

    public void setMinInterest(double minInterest) {
        this.minInterest = minInterest;
    }

    public double getMaxInterest() {
        return maxInterest;
    }

    public void setMaxInterest(double maxInterest) {
        this.maxInterest = maxInterest;
    }

    public int getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(int minMoney) {
        this.minMoney = minMoney;
    }

    public int getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(int maxMoney) {
        this.maxMoney = maxMoney;
    }

    public double getLendingTime() {
        return lendingTime;
    }

    public void setLendingTime(double lendingTime) {
        this.lendingTime = lendingTime;
    }

    public int getMortgage() {
        return mortgage;
    }

    public void setMortgage(int mortgage) {
        this.mortgage = mortgage;
    }

    public int getExclusive() {
        return exclusive;
    }

    public void setExclusive(int exclusive) {
        this.exclusive = exclusive;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
