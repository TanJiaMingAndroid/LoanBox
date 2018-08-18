package com.ps.loanbox.net.headerRequset;

import com.ps.loanbox.bean.Header;

/**
 * Created by 8146 on 2018/1/31.
 */

public class BaseRequset {

    protected Header header;


    public void createHeader() {
        header = new Header();
        header.setMsgType(Header.REQUEST);
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
}
