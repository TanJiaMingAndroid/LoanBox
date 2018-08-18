package com.ps.loanbox.gate;

import com.ps.loanbox.net.ApiAction;
import com.ps.loanbox.net.headerRequset.BaseRequset;

/**
 * Created by 8146 on 2018/2/9.
 */

public class LaberRequest extends BaseRequset {

    private String category;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LaberRequest() {
        createHeader();
    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.NEW_TYPE);
    }

}