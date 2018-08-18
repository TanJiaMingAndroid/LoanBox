package com.ps.loanbox.net.headerRequset;


import com.ps.loanbox.net.ApiAction;

/**
 * Created by 8146 on 2018/2/2.
 */

public class HelpRequest extends BaseRequset {

    public HelpRequest() {
        createHeader();
    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.HELP);
    }
}
