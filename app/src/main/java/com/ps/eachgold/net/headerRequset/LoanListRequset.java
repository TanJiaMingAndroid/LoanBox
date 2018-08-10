package com.ps.eachgold.net.headerRequset;

import com.ps.eachgold.net.ApiAction;

/**
 * Created by 8146 on 2018/1/31.
 */

public class LoanListRequset extends BaseRequset{


    public LoanListRequset() {
        createHeader();

    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.HOTLISt);
    }
}
