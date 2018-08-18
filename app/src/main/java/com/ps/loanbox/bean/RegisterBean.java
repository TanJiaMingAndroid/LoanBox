package com.ps.loanbox.bean;

/**
 * Created by 8146 on 2018/2/2.
 */

public class RegisterBean {

    /**
     * sessionid : a8f7fbb0d74d49f29d00480ec66d5845
     * userinfo : {"createdAt":"2018-02-01 18:56:52","id":7,"imei":"123123123","isAuth":-1,"isDel":1,"isFrom":4,"lastTime":"2018-02-01 18:56:52","openId":"11111111111111111","password":"","passwordSign":"270611","phone":"15990006398","updatedAt":"2018-02-01 18:56:52","uuid":"9e045d80-1236-4802-9c30-5b049b5d7195"}
     */

    private String sessionid;
    private UserinfoBean userinfo;

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
        /**
         * createdAt : 2018-02-01 18:56:52
         * id : 7
         * imei : 123123123
         * isAuth : -1
         * isDel : 1
         * isFrom : 4
         * lastTime : 2018-02-01 18:56:52
         * openId : 11111111111111111
         * password :
         * passwordSign : 270611
         * phone : 15990006398
         * updatedAt : 2018-02-01 18:56:52
         * uuid : 9e045d80-1236-4802-9c30-5b049b5d7195
         */

        private String createdAt;
        private int id;
        private String imei;
        private int isAuth;
        private int isDel;
        private int isFrom;
        private String lastTime;
        private String openId;
        private String password;
        private String passwordSign;
        private String phone;
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
