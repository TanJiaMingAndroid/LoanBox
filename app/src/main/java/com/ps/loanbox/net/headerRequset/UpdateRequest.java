package com.ps.loanbox.net.headerRequset;

import com.ps.loanbox.net.ApiAction;

/**
 * Created by 8146 on 2018/2/3.
 */

public class UpdateRequest extends BaseRequset {
    /**
     * imei false string 手机imei值（android，ios必填）
     * password false string RSA加密后密码值（android，ios，pc必填）
     * phone true string 手机号
     * openId false string 微信openid（wx必填）
     * valCode true string 短信验证码
     */


    private String newPassword; //

    private String password;

    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public UpdateRequest() {
        createHeader();

    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.UPDATE_PSW);
    }
}