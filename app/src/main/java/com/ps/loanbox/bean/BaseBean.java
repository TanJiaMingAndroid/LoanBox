package com.ps.loanbox.bean;

import java.io.Serializable;

/**
 * Created by 8146 on 2018/1/15.
 * Bean 基类
 */

public class BaseBean<E> implements Serializable{
    private int code;
    private String message;

    private E data;
    public boolean isSuccess() {
        return code == 200;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
