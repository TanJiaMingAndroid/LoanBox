package com.ps.loanbox.bean;

import java.io.Serializable;

/**
 * Created by 8146 on 2018/1/31.
 *  Bean 基类 -带Header
 */

public class BaseHeaderBean<E> implements Serializable

{
    private Header header;

    private E data;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
