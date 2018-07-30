package com.ps.eachgold.bean;

/**
 * Created by 8146 on 2018/1/31.
 * 分页 模型类
 */

public class Page {
    /**
     * count : 1
     * index : 1
     * size : 10
     * total : 4
     */
    private int count;
    private int index;
    private int size;
    private int total;

    public Page(){

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
