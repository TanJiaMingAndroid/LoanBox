/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.ps.eachgold.bean.Apii;

//import com.ps.hj.model.Admin;
//import com.ps.hj.model.User;

import java.io.Serializable;

/**
 * @Description 请求基类封装
 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
 * @date 2018年1月25日 上午9:49:09
 * @version V1.0.1
 */
public class RequestMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Header header;
//	protected User user;
//	protected Admin admin;

	public void createHeader() {
		header = new Header();
		header.setMsgType(Header.REQUEST);
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public Admin getAdmin() {
//		return admin;
//	}
//
//	public void setAdmin(Admin admin) {
//		this.admin = admin;
//	}

}
