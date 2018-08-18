/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.ps.loanbox.bean.Apii;



import com.ps.loanbox.net.ResultCode;
import com.ps.loanbox.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 请求响应头封装
 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
 * @date 2018年1月27日 上午10:19:23
 * @version V1.0.1
 */
public class Header implements Serializable {
	/**
	 * @Fields serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 请求
	 */
	public final static int REQUEST = 0;
	/**
	 * 应答
	 */
	public final static int RESPONSE = 1;

	/**
	 * 游客
	 */
	public final static String GUEST = "guest";

	/**
	 * 功能号，必填
	 */
	private String action;
	/**
	 * 区分请求/应答类型，必填
	 */
	private Integer msgType;

	/**
	 * 设备类型，必填 web, wx, ios, android
	 */
	private String deviceType;

	/**
	 * 手机型号
	 */
	private String clientModel;

	/**
	 * 客户端版本
	 */
	private String clientVersion;

	/**
	 * 请求发起的位置-纬度
	 */
	private double locLatitude;

	/**
	 * 请求发起的位置-经度
	 */
	private double locLongitude;

	/**
	 * sessionId号，登录成功后服务端返回
	 */
	private String sessionId;

	/**
	 * 请求者IP或者MAC地址，预留
	 */
	private String ip;
	/**
	 * 发送时间，格式：yyyy-MM-dd HH:mm:ss
	 */
	private String sendingTime;
	/**
	 * 签名方式，预留
	 */
	private String signMode;
	/**
	 * 签名信息，预留
	 */
	private String signMsg;

	/**
	 * 分页信息
	 */
	private Page page;
	/**
	 * 错误号，0：成功，非0：失败
	 */
	private int code;
	/**
	 * 错误信息
	 */
	private String msg;

	/**
	 * 日期格式："yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return
	 */
	public static String getDateTime() {
		return DateUtil.timeToString(new Date(), DateUtil.FORMAT_11);
	}

	public Header() {
		this.code = ResultCode.OK.getCode();
		this.sendingTime = getDateTime();
		this.clientVersion = "1.0.01";
	}

	public Header(Header header) {
		this();
		create(header);
	}

	public void create(Header header) {
		if (header != null) {
			this.action = header.action;
			// this.userId = header.userId;
			this.sessionId = header.sessionId;
			if (header.page != null && this.page == null) {
				this.page = header.page;
				// this.page = new Page();
				// this.page.setIndex(header.page.getIndex());
				// this.page.setSize(header.page.getSize());
			}
		}
	}

	public void init(Header header) {
		if (header != null) {
			this.deviceType = header.deviceType;
			this.sessionId = header.sessionId;
			this.page = header.page;
		}
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getClientModel() {
		return clientModel;
	}

	public void setClientModel(String clientModel) {
		this.clientModel = clientModel;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public double getLocLatitude() {
		return locLatitude;
	}

	public void setLocLatitude(double locLatitude) {
		this.locLatitude = locLatitude;
	}

	public double getLocLongitude() {
		return locLongitude;
	}

	public void setLocLongitude(double locLongitude) {
		this.locLongitude = locLongitude;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSendingTime() {
		return sendingTime;
	}

	public void setSendingTime(String sendingTime) {
		this.sendingTime = sendingTime;
	}

	public String getSignMode() {
		return signMode;
	}

	public void setSignMode(String signMode) {
		this.signMode = signMode;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static int getResponse() {
		return RESPONSE;
	}

}