package com.ps.eachgold.bean;

import java.util.List;

/**
 * Created by 8146 on 2018/1/31.
 * 首页热门推荐
 */

public class LoanBean {

    private String applyCondition;
    private String applyProcess;
    private String description;
    private int id;
    private String logoUrl;
    private String name;
    private int position;
    private String repayInfo;
    private int status;
    private List<ProductData> productData;

    public String getApplyCondition() {
        return applyCondition;
    }

    public void setApplyCondition(String applyCondition) {
        this.applyCondition = applyCondition;
    }

    public String getApplyProcess() {
        return applyProcess;
    }

    public void setApplyProcess(String applyProcess) {
        this.applyProcess = applyProcess;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getRepayInfo() {
        return repayInfo;
    }

    public void setRepayInfo(String repayInfo) {
        this.repayInfo = repayInfo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ProductData> getProductData() {
        return productData;
    }

    public void setProductData(List<ProductData> productData) {
        this.productData = productData;
    }

    class ProductData{
        String borrowAmount;
        String borrowPeriod;
        double dayRate;

        public void setBorrowAmount(String borrowAmount) {
            this.borrowAmount = borrowAmount;
        }

        public void setBorrowPeriod(String borrowPeriod) {
            this.borrowPeriod = borrowPeriod;
        }

        public void setDayRate(int dayRate) {
            this.dayRate = dayRate;
        }

        public String getBorrowAmount() {
            return borrowAmount;
        }

        public String getBorrowPeriod() {
            return borrowPeriod;
        }

        public double getDayRate() {
            return dayRate;
        }
    }


}
