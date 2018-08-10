package com.ps.eachgold.bean;

import java.util.List;

/**
 * Created by 8146 on 2018/1/31.
 * 首页热门推荐
 */

public class LoanBean {



    private int productId;
    private String productName;
    private int status;
    private int position;
    private String logoUrl;
    private List<ProductData> productData;

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setProductData(List<ProductData> productData) {
        this.productData = productData;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getStatus() {
        return status;
    }

    public int getPosition() {
        return position;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public List<ProductData> getProductData() {
        return productData;
    }

    class ProductData{
        String borrowAmount;
        String borrowPeriod;
        int dayRate;

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

        public int getDayRate() {
            return dayRate;
        }
    }


}
