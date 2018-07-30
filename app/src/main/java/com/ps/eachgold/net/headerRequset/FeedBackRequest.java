package com.ps.eachgold.net.headerRequset;

import com.ps.eachgold.net.ApiAction;

/**
 * Created by 8146 on 2018/1/31.
 */

public class FeedBackRequest extends BaseRequset{


    /**
     * header : {"action":"FEEDBACK0001","code":"0","devicetype":"1","msgtype":0,"sendingtime":"2016-09-28 11:25:24.105","version":"1.0.01"}
     * mobile : 13777777777
     * openId : xxxxxxx
     * content : 13777777777
     */

    private String mobile;
    private String openId;
    private String content;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FeedBackRequest() {
        createHeader();
    }
    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.FEEDBACK);
    }

}
