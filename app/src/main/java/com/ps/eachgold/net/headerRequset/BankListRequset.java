package com.ps.eachgold.net.headerRequset;

import com.ps.eachgold.bean.Apii.HjAction;
import com.ps.eachgold.net.ApiAction;

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
