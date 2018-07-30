package com.ps.eachgold.bean;

import java.io.Serializable;

/**
 * Created by 8146 on 2018/2/3.
 */

public class UserInfoBean implements Serializable{
    private String name;
    private int sex;
    private String idCard;
    private int identityID;
    private int edusID;


    private int monthId; //月收入
    private int creditFlag; //有无信用卡
    private int CreditCount; //信用卡张数
    private int creditId; //信用卡最高额度范围
    private int socialId; //有无社保
    private int fundId;//有公积金
    private int carId;//有无车产
    private int houseId;//有无房产

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getIdentityID() {
        return identityID;
    }

    public void setIdentityID(int identityID) {
        this.identityID = identityID;
    }

    public int getEdusID() {
        return edusID;
    }

    public void setEdusID(int edusID) {
        this.edusID = edusID;
    }

    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }

    public int getCreditFlag() {
        return creditFlag;
    }

    public void setCreditFlag(int creditFlag) {
        this.creditFlag = creditFlag;
    }

    public int getCreditCount() {
        return CreditCount;
    }

    public void setCreditCount(int creditCount) {
        CreditCount = creditCount;
    }

    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public int getSocialId() {
        return socialId;
    }

    public void setSocialId(int socialId) {
        this.socialId = socialId;
    }

    public int getFundId() {
        return fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }
}
