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
 * 发送注册短信
 * 
 * @Description
 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
 * @date 2018年1月24日 下午2:34:30
 * @version V1.0.1
 */
public class SmsUserRegisterRequest extends RequestMessage {
	private static final long serialVersionUID = 1L;

	private String mobile; // 手机号

	@Override
	public void createHeader() {
		super.createHeader();
		header.setAction(HjAction.SMS_USER_REGISTER);
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
