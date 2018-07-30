package com.ps.eachgold.net.headerRequset;

import com.ps.eachgold.net.ApiAction;

/**
 * Created by 8146 on 2018/2/2.
 */

public class LoginRequest extends BaseRequset {
    /**
     * imei false string 手机imei值（android，ios必填）
     * password false string  密码RSA加密值（app密码登录时必填）
     * phone true string 手机号
     * openId false string 微信openid（wx必填）
     * valCode true string 验证码（app验证码登录时必填）
     */


    private String phone; //

    private String imei;

    private String password;

    private String openId;

    private String valCode;


    private String termType;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getValCode() {
        return valCode;
    }

    public void setValCode(String valCode) {
        this.valCode = valCode;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public LoginRequest() {
        createHeader();

    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.LOGIN);
    }
}
