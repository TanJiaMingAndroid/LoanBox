package com.ps.loanbox.gate;

import com.ps.loanbox.net.ApiAction;
import com.ps.loanbox.net.headerRequset.BaseRequset;

/**
 * Created by 8146 on 2018/2/9.
 */

public class GateRequest extends BaseRequset {

    public GateRequest() {
        createHeader();
    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.APP_GATE);
    }

}
