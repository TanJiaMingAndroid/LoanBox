package com.ps.eachgold.bean;

/**
 * Created by 8146 on 2018/2/1.
 */

public class MessageBean {
    /**
     * adminId : 0
     * content : test
     * createdAt : 2018-02-01 15:54:06
     * des : test
     * id : 1
     * title : test
     * type : 1
     * userId : 0
     */

    private int adminId;
    private String content;
    private String createdAt;
    private String des;
    private int id;
    private String title;
    private int type;
    private int userId;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
