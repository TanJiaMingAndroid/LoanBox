package com.ps.eachgold.gate;

import com.ps.eachgold.net.ApiAction;
import com.ps.eachgold.net.headerRequset.BaseRequset;

/**
 * Created by 8146 on 2018/2/9.
 */

public class DetailRequest extends BaseRequset {

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public DetailRequest() {
        createHeader();
    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.NEW_DETAIL);
    }

}
