package com.ps.loanbox.net.headerRequset;

import com.ps.loanbox.net.ApiAction;

/**
 * Created by 8146 on 2018/2/2.
 */

public class ResetPswRequest extends BaseRequset {
    /**
     * imei false string 手机imei值（android，ios必填）
     * password false string RSA加密后密码值（android，ios，pc必填）
     * phone true string 手机号
     * openId false string 微信openid（wx必填）
     * valCode true string 短信验证码
     */


    private String phone; //

    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public ResetPswRequest() {
        createHeader();

    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.RESET_PSW);
    }
}
