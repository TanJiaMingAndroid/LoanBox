package com.ps.loanbox.bean;

/**
 * Created by 8146 on 2018/2/1.
 */

public class MessageBean {

    /**
     * id : 491414
     * userId : 139
     * productId : 0
     * messageType : 1
     * message : 不想处理
     * status : 1
     * createTime : 1533882505000
     * updateTime : 1534149247000
     * strCreateTime : 2018-08-10 14:28:25
     * strUpdateTime : 2018-08-13 16:34:07
     */

    private int id;
    private int userId;
    private int productId;
    private int messageType;
    private String message;
    private int status;
    private long createTime;
    private long updateTime;
    private String strCreateTime;
    private String strUpdateTime;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getStrCreateTime() {
        return strCreateTime;
    }

    public void setStrCreateTime(String strCreateTime) {
        this.strCreateTime = strCreateTime;
    }

    public String getStrUpdateTime() {
        return strUpdateTime;
    }

    public void setStrUpdateTime(String strUpdateTime) {
        this.strUpdateTime = strUpdateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
