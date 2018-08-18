/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.ps.loanbox.bean.Apii;

/**
 * 用户注册
 * 
 * @Description
 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
 * @date 2018年1月24日 下午2:38:49
 * @version V1.0.1
 */
public class UserRegisterRequest extends RequestMessage {
	/**
	 * @Fields serialVersionUID:
	 */
	private static final long serialVersionUID = 2301349644081904483L;

	/** 手机号 */
	private String mobile;

	/** 密码 */
	private String password;

	/** 验证码 */
	private String verifyCode;

	/** 推广渠道 */
	private String regChannel;

	/** 微信openid */
	private String openid;

	/** 设备token */
	private String deviceToken;

	@Override
	public void createHeader() {
		super.createHeader();
		header.setAction(HjAction.U_REGISTER);
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getRegChannel() {
		return regChannel;
	}

	public void setRegChannel(String regChannel) {
		this.regChannel = regChannel;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

}
