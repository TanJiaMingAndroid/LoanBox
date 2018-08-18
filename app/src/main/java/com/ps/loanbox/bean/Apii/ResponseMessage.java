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

import java.io.Serializable;

/**
 * @Description 响应基类封装
 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
 * @date 2018年1月25日 上午9:50:04
 * @version V1.0.1
 * @param <T>
 */
public class ResponseMessage<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Header header;

	/**
	 * 提交或返回数据
	 */
	private T data;

	public ResponseMessage() {

	}

	public ResponseMessage(RequestMessage request) {
		if (request.getHeader() != null) {
			createHeader(request.getHeader());
		}
	}

	public void createHeader() {
		this.header = new Header();
		this.header.setMsgType(Header.RESPONSE);
	}

	public void createHeader(Header header) {
		if (this.header == null) {
			this.header = new Header(header);
		} else {
			this.header.create(header);
			this.header.setSendingTime(Header.getDateTime());
		}
		this.header.setMsgType(Header.RESPONSE);
	}

	public void createError(Header header) {
		if (this.header == null) {
			this.createHeader();
		}
		this.header.setCode(header.getCode());
		this.header.setMsg(header.getMsg());
	}

	public void createError(ResultCode result) {
		if (this.header == null) {
			this.createHeader();
		}
		this.header.setCode(result.getCode());
		this.header.setMsg(result.getDesc());
	}

	public void createError(HjException e) {
		if (this.header == null) {
			this.createHeader();
		}
		this.header.setCode(e.getErrorCode());
		this.header.setMsg(e.getMessage());
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
