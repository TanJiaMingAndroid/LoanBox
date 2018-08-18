package com.ps.loanbox.bean;

/**
 * Created by 8303 on 2018/3/20.
 */

public class WelcomeBean {

    private String createTime;
    private int id;
    private String pic;
    private int position;
    private int sort;
    private int status;
    private String targetUrl;
    private String updateTime;
    /**
     * beginTimeDate : 1530460800000
     * endTimeDate : 1535644800000
     * strBeginTime : 2018-07-02
     * strEndTime : 2018-08-31
     * strPicUrl : http://img.sanjinxia.com//image/2018-07-09/1823325788.jpg
     */

    private long beginTimeDate;
    private long endTimeDate;
    private String strBeginTime;
    private String strEndTime;
    private String strPicUrl;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public long getBeginTimeDate() {
        return beginTimeDate;
    }

    public void setBeginTimeDate(long beginTimeDate) {
        this.beginTimeDate = beginTimeDate;
    }

    public long getEndTimeDate() {
        return endTimeDate;
    }

    public void setEndTimeDate(long endTimeDate) {
        this.endTimeDate = endTimeDate;
    }

    public String getStrBeginTime() {
        return strBeginTime;
    }

    public void setStrBeginTime(String strBeginTime) {
        this.strBeginTime = strBeginTime;
    }

    public String getStrEndTime() {
        return strEndTime;
    }

    public void setStrEndTime(String strEndTime) {
        this.strEndTime = strEndTime;
    }

    public String getStrPicUrl() {
        return strPicUrl;
    }

    public void setStrPicUrl(String strPicUrl) {
        this.strPicUrl = strPicUrl;
    }
}
