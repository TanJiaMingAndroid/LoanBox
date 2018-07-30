package com.ps.eachgold.bean;

/**
 * Created by 8146 on 2018/1/23.
 * Banner 模型类
 */

public class BannerBean {
    /**
     * id : 4
     * isShow : 1
     * pic : test/test.jpg
     * sort : 0
     * type : 4
     * url : www.adyun.com
     */

    private int id;
    private int isShow;
    private String pic;
    private int sort;
    private int type;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
