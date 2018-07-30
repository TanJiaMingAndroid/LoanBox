package com.ps.eachgold.net.headerRequset;

import com.ps.eachgold.net.ApiAction;

/**
 * Created by 8146 on 2018/2/1.
 */

public class MessageRequest extends BaseRequset {
    /**
     * "moblie":"18268210602" ,
     * "lastId":"1"
     */
    private  String moblie;


    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }



    public MessageRequest() {
        createHeader();

    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.MSG);
    }
}

