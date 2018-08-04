package com.ps.eachgold.bean;

import com.ps.eachgold.net.ResultCode;
import com.ps.eachgold.util.DateUtil;

import java.util.Date;

/**
 * Created by 8657 on 2018/8/4.
 * 请求头-模型类
 */

public class Header {
    /**
     * 请求
     */
    public final static int REQUEST = 0;
    /**
     * 应答
     */
    public final static int RESPONSE = 1;
    /**
     * 功能号，必填
     */
    private String action;
    /**
     *  0  -1
     */
    private int code;
    /**
     * 设备类型，必填 web, wx, ios, android  -- 4
     */
    private int deviceType;
    /**
     * 区分请求/应答类型，必填
     */
    private Integer msgType;
    /**
     * 发送时间
     */
    private String sendingtime;
    /**
     * 客户端版本
     */
    private String version;

    private int locLatitude;

    private int locLongitude;

    private String clientVersion;

    private Page page;

    private String sessionid;


    private String msg;
    /**
     * 日期格式："yyyy-MM-dd HH:mm:ss"
     *
     * @return
     */
    public static String getDateTime() {
        return DateUtil.timeToString(new Date(), DateUtil.FORMAT_11);
    }


    public  Header() {
        this.code = ResultCode.OK.getCode();
        this.deviceType=4;
        this.sendingtime = getDateTime();
        this.version = "1.0.01";
    }

    public Header(com.ps.eachgold.bean.Header header) {
        this();
        create(header);
    }
    public void create(com.ps.eachgold.bean.Header header) {
        if (header != null) {
            this.action = header.action;
            // this.userId = header.userId;
//            this.sessionId = header.sessionId;
//            if (header.page != null && this.page == null) {
//                this.page = header.page;
//                // this.page = new Page();
//                // this.page.setIndex(header.page.getIndex());
//                // this.page.setSize(header.page.getSize());
//            }
        }
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getLocLatitude() {
        return locLatitude;
    }

    public void setLocLatitude(int locLatitude) {
        this.locLatitude = locLatitude;
    }

    public int getLocLongitude() {
        return locLongitude;
    }

    public void setLocLongitude(int locLongitude) {
        this.locLongitude = locLongitude;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getSendingtime() {
        return sendingtime;
    }

    public void setSendingtime(String sendingtime) {
        this.sendingtime = sendingtime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }
}
