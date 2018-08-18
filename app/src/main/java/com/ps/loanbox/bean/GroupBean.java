package com.ps.loanbox.bean;

import java.util.List;

/**
 * Created by 8146 on 2018/1/17.
 * 二级列表类
 */

public class GroupBean {
    private String title;

    private String num;

    private List<String> childlist;

    public GroupBean(String title, String num,List<String> childlist) {
        this.title = title;
        this.num = num;
        this.childlist = childlist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<String> getChildlist() {
        return childlist;
    }

    public void setChildlist(List<String> childlist) {
        this.childlist = childlist;
    }
}
