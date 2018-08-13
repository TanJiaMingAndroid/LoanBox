package com.ps.eachgold.net.headerRequset;/**
 * Created by 8657 on 2018/8/10.
 */

import com.ps.eachgold.net.ApiAction;

/**
 * creat by tanjiaming at 2018/8/10
 */
public class TelLoginRequest extends  BaseRequset{

    private String phone;

    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }
    public TelLoginRequest() {
        createHeader();

    }
    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.TEL_LOGIN);
    }
}
