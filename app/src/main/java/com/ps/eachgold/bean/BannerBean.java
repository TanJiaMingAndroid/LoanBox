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

    //private int id;
    private int isShow;
    private String pic;
    private int sort;
    private int type;
    private String url;
    //add
    private int productId;
    private String title;
    private int id;
    private String position;
    private int status;
    private String link;//链接地址
    private String imgUrl;//图片地址
    private String beginTime;//广告开始时间
    private String endTime;//广告结束时间

    public int getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public int getStatus() {
        return status;
    }

    public String getLink() {
        return link;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
