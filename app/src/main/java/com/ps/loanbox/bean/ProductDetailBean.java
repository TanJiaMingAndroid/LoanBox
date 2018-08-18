package com.ps.loanbox.bean;/**
 * Created by 8657 on 2018/8/14.
 */

import java.util.List;

/**
 * creat by tanjiaming at 2018/8/14
 */
public class ProductDetailBean {


    /**
     * applyCondition : condition test
     * applyProcess : process test
     * coopeMode : 1
     * custServPhone : 15868480733
     * description : teserte3333
     * fixedSortType : 57
     * id : 11
     * logoUrl : http://sjx-api.sanjinxia.com/dcApi/image/2018-08-15/1508515760.png
     * name : Go Dana
     * productData : [{"answer":"answer0001","borrowAmount":100,"borrowPeriod":200,"dayRate":2,"id":1,"interest":400,"questionTitle":"question774","repayAmount":500},{"answer":"answer0001","borrowAmount":30,"borrowPeriod":50,"dayRate":40.22,"id":11,"interest":612.1484,"questionTitle":"question55","repayAmount":642},{"answer":"answer0001","borrowAmount":0,"borrowPeriod":0,"dayRate":0,"id":12,"interest":0,"questionTitle":"question13","repayAmount":0},{"answer":"","borrowAmount":3000000,"borrowPeriod":7,"dayRate":3,"id":98,"interest":630000,"questionTitle":"","repayAmount":3630000},{"answer":"","borrowAmount":30000000,"borrowPeriod":14,"dayRate":1,"id":125,"interest":4200000,"questionTitle":"","repayAmount":34200000},{"answer":"","borrowAmount":3500000,"borrowPeriod":21,"dayRate":1,"id":137,"interest":735000,"questionTitle":"","repayAmount":4235000},{"answer":"","borrowAmount":4000000,"borrowPeriod":28,"dayRate":1,"id":138,"interest":1120000,"questionTitle":"","repayAmount":5120000}]
     * repayInfo : repayInfo test
     * sortType : -1
     */

    private String applyCondition;
    private String applyProcess;
    private int coopeMode;
    private String custServPhone;
    private String description;
    private int fixedSortType;
    private int id;
    private String logoUrl;
    private String name;
    private String repayInfo;
    private String url;
    private int sortType;
    private List<ProductDataBean> productData;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    public int getCoopeMode() {
        return coopeMode;
    }

    public void setCoopeMode(int coopeMode) {
        this.coopeMode = coopeMode;
    }

    public String getCustServPhone() {
        return custServPhone;
    }

    public void setCustServPhone(String custServPhone) {
        this.custServPhone = custServPhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFixedSortType() {
        return fixedSortType;
    }

    public void setFixedSortType(int fixedSortType) {
        this.fixedSortType = fixedSortType;
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

    public String getRepayInfo() {
        return repayInfo;
    }

    public void setRepayInfo(String repayInfo) {
        this.repayInfo = repayInfo;
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public List<ProductDataBean> getProductData() {
        return productData;
    }

    public void setProductData(List<ProductDataBean> productData) {
        this.productData = productData;
    }

    public static class ProductDataBean {
        /**
         * answer : answer0001
         * borrowAmount : 100
         * borrowPeriod : 200
         * dayRate : 2
         * id : 1
         * interest : 400
         * questionTitle : question774
         * repayAmount : 500
         */

        private String answer;
        private int borrowAmount;
        private int borrowPeriod;
        private double dayRate;
        private int id;
        private int interest;
        private String questionTitle;
        private int repayAmount;

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInterest() {
            return interest;
        }

        public void setInterest(int interest) {
            this.interest = interest;
        }

        public String getQuestionTitle() {
            return questionTitle;
        }

        public void setQuestionTitle(String questionTitle) {
            this.questionTitle = questionTitle;
        }

        public int getRepayAmount() {
            return repayAmount;
        }

        public void setRepayAmount(int repayAmount) {
            this.repayAmount = repayAmount;
        }
    }
}

