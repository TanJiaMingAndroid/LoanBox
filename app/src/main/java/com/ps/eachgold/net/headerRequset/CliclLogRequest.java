package com.ps.eachgold.net.headerRequset;

import com.ps.eachgold.net.ApiAction;

/**
 * Created by 8146 on 2018/2/5.
 */

public class CliclLogRequest extends BaseRequset {
    /**
     * "moblie":"18268210602",
     * "openId":"xxxxxxxxxx",
     *  prodType 产品类型产品类型,如Bank,Card,SuperMarket,Repayment',
     *  prodId  产品编号
     */

    private String moblie;
    private String openId;
    private String prodType;
    private String prodId;

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


    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public CliclLogRequest() {
        createHeader();
    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.LOG);
    }

}

