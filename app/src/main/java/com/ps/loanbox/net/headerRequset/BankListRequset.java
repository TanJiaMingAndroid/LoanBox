package com.ps.loanbox.net.headerRequset;

import com.ps.loanbox.net.ApiAction;

/**
 * Created by 8146 on 2018/1/31.
 */

public class BankListRequset extends BaseRequset{




    public BankListRequset() {
        createHeader();
    }
    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.BANKLIST);
    }
}
