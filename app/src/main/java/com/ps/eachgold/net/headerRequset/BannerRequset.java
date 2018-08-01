package com.ps.eachgold.net.headerRequset;

import com.ps.eachgold.net.ApiAction;

/**
 * Created by 8146 on 2018/1/31.
 */

public class BannerRequset extends BaseRequset{

    private String bannerType;

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }


    public BannerRequset() {
        createHeader();
    }


    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.BANNER);
    }
}