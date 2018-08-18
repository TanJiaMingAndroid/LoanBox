package com.ps.loanbox.net.headerRequset;

import com.ps.loanbox.net.ApiAction;

/**
 * Created by 8146 on 2018/2/2.
 */

public class   ForgotPswCodeRequest extends BaseRequset {

    private String phone; //

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ForgotPswCodeRequest() {
        createHeader();

    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.FORGOT_CODE);
    }
}
