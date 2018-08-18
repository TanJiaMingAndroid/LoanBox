package com.ps.loanbox.net.headerRequset;

import com.ps.loanbox.net.ApiAction;

/**
 * Created by 8146 on 2018/2/3.
 */

public class InfoStepRequest extends BaseRequset {
    /**
     * name true string 姓名
     * idCard true string 身份证号码
     * level false number 账户等级
     * cardAddress false string
     * homeAddress false string
     * photoUrl false string
     * sex true number 性别（1男/2女）
     * education true number 教育程度(1中专/高中以下/2中专/高中/3专科/4本科/5研究生以上)
     * job true number 职业类型（1上班族/2个体户/3企业主/4自由职业）
     * income true number 收入范围（1 3000以下/2 3000-5000/3 5000-10000/4 10000-15000/5 15000以上）
     * creditCard true number 有无信用卡（1有/2无）
     * creditCardNum false number 信用卡张数
     * creditCardMoney false number 信用卡最高额度范围（1 5000以下/2 5000-1万/3 1万-3万/4 3万-5万/5 5万-10万/6 10万以上）
     * socialSecurity true number 有无社保（-1无/1有 本地社保/2有外地社保）
     * accumulationFund true number 有无公积金（-1无/1有 本地公积金/2有外地公积金）
     * house true number 有无房产（-1无/1准备购买/2有房无贷款/3有房 按揭贷款/4有房 已被抵押）
     * car true number 有无车（-1无/1准备购买/2有车 无贷款/3有车 按揭贷款/4有车 但已抵押）
     * lastNoticeId false number 最后阅读的通知
     */
    private String name;
    private String idCard;
    private int level;
    private String cardAddress;
    private String homeAddress;
    private String photoUrl;
    private int sex;
    private int education;
    private int job;
    private int income;
    private int creditCard;
    private int creditCardNum;
    private int creditCardMoney;
    private int socialSecurity;
    private int accumulationFund ;
    private int house;
    private int car;
    private int lastNoticeId;



    public InfoStepRequest() {
        createHeader();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCardAddress() {
        return cardAddress;
    }

    public void setCardAddress(String cardAddress) {
        this.cardAddress = cardAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(int creditCard) {
        this.creditCard = creditCard;
    }

    public int getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(int creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public int getCreditCardMoney() {
        return creditCardMoney;
    }

    public void setCreditCardMoney(int creditCardMoney) {
        this.creditCardMoney = creditCardMoney;
    }

    public int getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(int socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public int getAccumulationFund() {
        return accumulationFund;
    }

    public void setAccumulationFund(int accumulationFund) {
        this.accumulationFund = accumulationFund;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public int getLastNoticeId() {
        return lastNoticeId;
    }

    public void setLastNoticeId(int lastNoticeId) {
        this.lastNoticeId = lastNoticeId;
    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.SAVE_INFO);
    }
}
