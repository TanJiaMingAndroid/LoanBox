package com.ps.loanbox.net.headerRequset;

import com.ps.loanbox.net.ApiAction;

/**
 * Created by 8146 on 2018/2/1.
 */

public class MyExclusiveRequest extends BaseRequset{

    private String moblie; //请求类型 ：额度高-high、利息低-low、放款快-fast hot-热门 代还-repay

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }

    public MyExclusiveRequest() {
        createHeader();

    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.EXCLUSIVE);
    }
}
