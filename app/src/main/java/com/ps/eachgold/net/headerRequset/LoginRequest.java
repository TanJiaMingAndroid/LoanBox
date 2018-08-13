package com.ps.eachgold.net.headerRequset;

import com.ps.eachgold.net.ApiAction;

/**
 * Created by 8146 on 2018/2/2.
 */

public class LoginRequest extends BaseRequset {


    private Long facebookId;

    private String facebookUser;

    private String regChannel;

    private String appName;

    private String appPackage;


    private String imei;
    private String termType;

    public void setFacebookId(Long facebookId) {
        this.facebookId = facebookId;
    }

    public void setFacebookUser(String facebookUser) {
        this.facebookUser = facebookUser;
    }

    public void setRegChannel(String regChannel) {
        this.regChannel = regChannel;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public Long getFacebookId() {
        return facebookId;
    }

    public String getFacebookUser() {
        return facebookUser;
    }

    public String getRegChannel() {
        return regChannel;
    }

    public String getAppName() {
        return appName;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public String getImei() {
        return imei;
    }

    public String getTermType() {
        return termType;
    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.FB_LOGIN);
    }
}
