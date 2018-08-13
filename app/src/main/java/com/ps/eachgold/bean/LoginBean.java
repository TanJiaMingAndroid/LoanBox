package com.ps.eachgold.bean;

/**
 * Created by 8146 on 2018/2/2.
 */

public class LoginBean {


    /**
     * sessionid : 2516ba7600cb4f609ab3938354345c90
     * userinfo : {"createdAt":"2018-02-01 18:56:53","id":7,"imei":"123123123","isAuth":-1,"isDel":1,"isFrom":4,"lastTime":"2018-02-01 19:29:53","name":"","openId":"11111111111111111","password":"","passwordSign":"","phone":"15990006398","termType":"","updatedAt":"2018-02-01 19:29:53","uuid":"9e045d80-1236-4802-9c30-5b049b5d7195"}
     */
    //是否需要绑定手机 1：需要 0：不需要
    private int isNewUser;
    private String sessionid;
    private UserinfoBean userinfo;

    public void setIsNewUser(int isNewUser) {
        this.isNewUser = isNewUser;
    }

    public int getIsNewUser() {
        return isNewUser;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public static class UserinfoBean {


        private String createdAt;
        private int id;
        private String imei;
        private int isAuth;
        private int isDel;
        private int isFrom;
        private String lastTime;
        private String name;
        private String openId;
        private String password;
        private String passwordSign;
        private String phone;
        private String termType;
        private String updatedAt;
        private String uuid;

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public int getIsAuth() {
            return isAuth;
        }

        public void setIsAuth(int isAuth) {
            this.isAuth = isAuth;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public int getIsFrom() {
            return isFrom;
        }

        public void setIsFrom(int isFrom) {
            this.isFrom = isFrom;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPasswordSign() {
            return passwordSign;
        }

        public void setPasswordSign(String passwordSign) {
            this.passwordSign = passwordSign;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getTermType() {
            return termType;
        }

        public void setTermType(String termType) {
            this.termType = termType;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }
}
