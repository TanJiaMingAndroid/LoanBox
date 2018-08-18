package com.ps.loanbox.bean;

/**
 * Created by 8146 on 2018/1/31.
 * 首页热门推荐
 */

public class ProductBean {


    /**
     * borrowAmount : 111
     * borrowPeriod : 5
     * dayRate : 1
     * description : 666
     * logoUrl : http://sjx-api.sanjinxia.com/dcApi/image/2018-08-15/1213151864.png
     * maxBorrowAmount : 222
     * maxBorrowPeriod : 7
     * minBorrowAmount : 111
     * minBorrowPeriod : 5
     * name : 111222
     * productId : 62
     */

    private int borrowAmount;
    private int borrowPeriod;
    private double dayRate;
    private String description;
    private String logoUrl;
    private int maxBorrowAmount;
    private int maxBorrowPeriod;
    private int minBorrowAmount;
    private int minBorrowPeriod;
    private String name;
    private int productId;

    public int getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(int borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public int getBorrowPeriod() {
        return borrowPeriod;
    }

    public void setBorrowPeriod(int borrowPeriod) {
        this.borrowPeriod = borrowPeriod;
    }

    public double getDayRate() {
        return dayRate;
    }

    public void setDayRate(double dayRate) {
        this.dayRate = dayRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getMaxBorrowAmount() {
        return maxBorrowAmount;
    }

    public void setMaxBorrowAmount(int maxBorrowAmount) {
        this.maxBorrowAmount = maxBorrowAmount;
    }

    public int getMaxBorrowPeriod() {
        return maxBorrowPeriod;
    }

    public void setMaxBorrowPeriod(int maxBorrowPeriod) {
        this.maxBorrowPeriod = maxBorrowPeriod;
    }

    public int getMinBorrowAmount() {
        return minBorrowAmount;
    }

    public void setMinBorrowAmount(int minBorrowAmount) {
        this.minBorrowAmount = minBorrowAmount;
    }

    public int getMinBorrowPeriod() {
        return minBorrowPeriod;
    }

    public void setMinBorrowPeriod(int minBorrowPeriod) {
        this.minBorrowPeriod = minBorrowPeriod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
