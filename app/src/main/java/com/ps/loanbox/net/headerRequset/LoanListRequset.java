package com.ps.loanbox.net.headerRequset;

import com.ps.loanbox.net.ApiAction;

/**
 * Created by 8146 on 2018/1/31.
 */

public class LoanListRequset extends BaseRequset{

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LoanListRequset() {
        createHeader();

    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.HOTLISt);
    }
}
