package com.ps.eachgold.net.headerRequset;

import com.ps.eachgold.net.ApiAction;

/**
 * Created by 8146 on 2018/2/1.
 */

public class CustomRequest extends BaseRequset {
    /**
     * "moblie":"18268210602",
     * "openId":"xxxxxxxxxx",
     * "labelName":"SuperMarket" //SuperMarket 贷款产品 Repayment 信用卡代还 Card 信用卡产品
     */

    private String moblie;
    private String openId;
    private String labelName;

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public CustomRequest() {
        createHeader();
    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.CUSTOM);
    }

}

