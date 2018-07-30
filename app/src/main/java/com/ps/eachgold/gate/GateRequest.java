package com.ps.eachgold.gate;

import com.ps.eachgold.net.ApiAction;
import com.ps.eachgold.net.headerRequset.BaseRequset;

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
