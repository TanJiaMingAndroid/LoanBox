/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.ps.loanbox.bean.Apii;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Description 分页类封装
 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
 * @date 2018年1月25日 上午9:48:52
 * @version V1.0.1
 */
public class Page {
	/**
	 * 当前页
	 */
	private int index = 1;
	/**
	 * 页大小
	 */
	private int size;
	/**
	 * 总页数
	 */
	// private int count;
	/**
	 * 总行数
	 */
	private long total;

	public Page() {
		this.index = 1;
		this.size = 20;
	}

	public Page(int index, int size) {
		if (index > 0) {
			this.index = index;
		} else {
			this.index = 1;
		}
		if (size > 0 && size <= 200) {
			this.size = size;
		} else {
			this.size = 20;
		}
	}

	public Page(String index, String size) {
		this(Integer.parseInt(index), Integer.parseInt(size));
	}

	public int getIndex() {
		if (total != 0 && index == 0) {
			index = 1;
		}
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public int getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public int getCount() {
		long mod = total % size;
		long count = total / size;
		count = mod == 0 ? count : (count + 1);
		return new Long(count).intValue();
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@JSONField(serialize = false)
	public int getStartRowNum() {
		// if (this.getIndex() > getCount() && getCount() != 0) {
		// this.setIndex(this.getCount());
		// }
		return (this.getIndex() - 1) * this.size;
	}

}
