package com.ps.loanbox.net.headerRequset;/**
 * Created by 8657 on 2018/8/14.
 */

import com.ps.loanbox.net.ApiAction;

/**
 * creat by tanjiaming at 2018/8/14
 */
public class BindTelRequest extends BaseRequset{
    private String phone;
    private int id;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.BIND_TEL);
    }
}
