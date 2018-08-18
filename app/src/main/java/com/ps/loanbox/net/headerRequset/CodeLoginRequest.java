package com.ps.loanbox.net.headerRequset;/**
 * Created by 8657 on 2018/8/14.
 */

import com.ps.loanbox.net.ApiAction;

/**
 * creat by tanjiaming at 2018/8/14
 */
public class CodeLoginRequest extends BaseRequset{
    private String phone;
    private String valCode;
    private String regChannel;
    private String appName;
    private String appPackage;
    private String imei;
    private String termType;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValCode() {
        return valCode;
    }

    public void setValCode(String valCode) {
        this.valCode = valCode;
    }

    public String getRegChannel() {
        return regChannel;
    }

    public void setRegChannel(String regChannel) {
        this.regChannel = regChannel;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public CodeLoginRequest() {
        createHeader();

    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.CODE_LOGIN);
    }
}
