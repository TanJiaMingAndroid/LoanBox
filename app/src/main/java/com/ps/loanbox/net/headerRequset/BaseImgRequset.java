package com.ps.loanbox.net.headerRequset;

import com.ps.loanbox.net.ApiAction;

/**
 * Created by 8146 on 2018/1/31.
 */

public class BaseImgRequset extends BaseRequset{

    //广告类型 1 开屏广告 ，2 弹窗广告， 3 浮窗广告
    private int bannerType;

    public int getBannerType() {
        return bannerType;
    }

    public void setBannerType(int bannerType) {
        this.bannerType = bannerType;
    }

    public BaseImgRequset() {
        createHeader();
    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.BASEIMG);
    }
}
