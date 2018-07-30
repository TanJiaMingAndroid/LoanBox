package com.ps.eachgold.net.headerRequset;

import com.ps.eachgold.net.ApiAction;

/**
 * Created by 8146 on 2018/1/31.
 */

public class LoanListRequset extends BaseRequset{

    private String reqType; //请求类型 ：额度高-high、利息低-low、放款快-fast hot-热门 代还-repay

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public LoanListRequset() {
        createHeader();

    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.LOAN);
    }
}
