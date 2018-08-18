/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.ps.loanbox.net;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 返回码枚举
 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
 * @date 2018年1月25日 上午9:50:55
 * @version V1.0.1
 */

/**
 * @Description
 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
 * @date 2018年1月27日 下午3:47:49
 * @version V1.0.1
 */
public enum ResultCode {

	OK(0, "成功"),

	ERROR(-1, "失败"),

	EXCEPTION(-99, "系统繁忙"),

	LOGIN_FAILED(-10, "登录失败"),

	HEADER_ISNULL(-100, "请求头为空"),

	ACTION_ISNULL(-101, "功能号为空"),

	USERID_ISNULL(-102, "用户信息为空"),

	DEVICETYPE_ISNULL(-103, "设备类型为空"),

	MSGTYPE_ISNULL(-104, "消息类型为空"),

	SESSIONID_ISNULL(-105, "用户未登录"),

	PAGE_ISNULL(-106, "分页参数为空"),

	PARAM_MISSING(-107, "缺少必要参数"),

	INVALID_ACTION(-111, "无效功能号"),

	INVALID_USERID(-112, "用户信息错误"),

	INVALID_DEVICETYPE(-113, "无效设备类型"),

	INVALID_MSGTYPE(-114, "无效消息体"),

	USER_NOT_EXISTS(-122, "用户不存在"),

	USER_EXISTS(-123, "用户已存在"),

	PARAMETER_ERROR(-124, "参数错误"),

	RECORD_UPDATE_FAIL(-125, "信息更新失败"),

	RECORD_SAVE_FAIL(-126, "信息保存失败"),

	RECORD_NOT_EXIST(-127, "信息不存在"),

	RECORD_EXISTS(-128, "信息已经存在"),

	RECORD_SEARCH_FAIL(-129, "信息查询失败"),

	RONG_TOKEN_GET_ERROR(-130, "获取融云token出错"),

	RONG_TOKEN_REFRESH_ERROR(-131, "刷新融云token出错"),

	ILLEGAL_REQUEST(-140, "非法请求"),

	// 消息体

	AUTH_ISNULL(-200, "验证码为空"),

	AUTH_INVALID(-201, "验证码失效，请重新获取"),

	AUTH_ERROR(-202, "验证码错误"),

	AUTH_FAIL_COUNT(-203, "验证码输错次数过多"),

	AUTH_FAIL_COUNT_TEN_MIN(-204, "10分钟内只能发送3条短信，请稍后再试"),

	SMS_ERROR(-205, "短信发送失败"),

	PASSWORD_ISNULL(-220, "密码为空"),

	PASSWORD_LENGTH(-221, "密码长度不合法"),

	PASSWORD_FORMAT_ERROR(-222, "密码格式错误"),

	PASSWORD_ERROR(-223, "密码错误"),

	NEW_PASSWORD_ISNULL(-224, "新密码为空"),

	USER_PHONE_ISNULL(-240, "用户手机号为空"),

	UID_OR_PHONE_ISNULL(-241, "用户id 或手机号码为空"),

	PHONE_EXISTS(-242, "手机号已存在"),

	PHONE_WRONG(-243, "无效的手机号"),

	TITLE_ISNULL(-260, "标题为空"),

	CONTENT_ISNULL(-261, "内容为空"),

	USER_AVATAR_ISNULL(-271, "用户头像不能为空"),

	USER_NAME_PASSWORD_ERROR(-272, "用户名或密码错误"),

	USER_NAME_ISNULL(-273, "用户名为空"),

	ORDER_TYPE_RANK_ISNULL(-290, "排名排序不能为空"),

	DATE_ISNULL(-291, "日期不能为空"),

	ID_CARD_ISNULL(-300, "身份证不能为空"),

	ID_CARD_VALIDATE_FAIL(-301, "身份证验证失败"),

	ID_CARD_REPEAT(-302, "身份证号码重复"),

	BANK_CARD_VALIDATE_FAIL(-310, "无效银行卡号"),

	FILE_DIR_CREATE_ERROR(-320, "无法上传文件"),

	IMAGE_TRANS_ERROR(-321, "图片转换错误"),

	IMAGE_NOT_SUPPORT(-322, "图片类型不支持"),

	IMAGE_UPLOAD_ERROR(-323, "图片上传错误"),

	FOCUS_TYPE_ISNULL(-324, "图片转换错误"),

	FILE_ISNULL(-325, "文件为空"),

	IO_EXCEPTION(-326, "读取数据流发生异常"),

	DEVICE_TOKEN_ISNULL(-330, "设备token不能为空"),

	SEARCH_VALUE_ISNULL(-340, "搜索条件不能为空"),

	REGION_ID_ISNULL(-501, "区域ID为空"),

	REGION_PID_ISNULL(-502, "父级区域ID为空"),

	ROLENAME_EXISTS(-701, "角色名已经存在"),

	ROLE_HAVE_ADMINS(-702, "存在与此角色关联的管理员"),

	ROLE_NOTEXISTS(-703, "角色不存在"),

	WX_OPENID_ERROR(-1001, "获取微信openid异常"),

	WX_USERINFO_ERROR(-1002, "获取微信用户信息异常"),

	WX_OPENID_ISNULl(-1003, "微信openid为空"),

	WX_CERT_EXCEPYION(-1004, "加载微信证书发生异常"),

	WX_REFUND_EXCEPYION(-1005, "微信退款发生异常"),

	ZFB_REFUND_EXCEPYION(-1006, "支付宝退款发生异常"),

	NOTIFY_MONEY_ERROR(-1007, "订单金额错误"),

	NOTIFY_ALLREADY_DEALED(-1008, "通知已经处理，无需再次处理"),

	QRCODE_CREATE_ERROR(-1011, "创建二维码图片失败"),

	QRCODE_DECODE_ERROR(-1012, "识别二维码图片失败"),

	PELEASE_FILL_CONTENT(-1021, "请填写完活动内容"),

	NOT_PERMISSION(-1031, "没有权限操作"),

	USER_NOT_LOGIN(-1032, "该用户不能登录"),

	USER_DISABLED(-1033, "用户已锁定"),

	USER_EXIT_CLUB(-1040, "用户已在该俱乐部"),

	USER_NOT_CLUB(-1041, "该用户没有加入俱乐部"),

	SELLER_BINDING_BANK(-1070, "该用户已绑定过银行卡"),

	PLEASE_BINDING_YOUSELF_BANK(-1070, "请绑定本人银行卡");

	// 成员变量
	private int code; // 编码
	private String desc; // 中文描述
	private String enDesc; // 英文描述
	/**
	 * 返回码MAP
	 */
	private static Map<Integer, String> map;

	static {
		// 初始化
		map = new HashMap<>();
		for (ResultCode code : ResultCode.values()) {
			map.put(code.getCode(), code.getDesc());
		}
	}

	private ResultCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private ResultCode(int code, String desc, String enDesc) {
		this.code = code;
		this.desc = desc;
		this.enDesc = enDesc;
	}

	public static ResultCode getType(int code) {
		if (code <= 0) {
			return null;
		}
		for (ResultCode resultCode : ResultCode.values()) {
			if (resultCode.code == code) {
				return resultCode;
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 获取返回码反描述
	 * 
	 * @return
	 */
	public static String getDesc(int code) {
		return map.get(code);
	}

	public String getEnDesc() {
		return enDesc;
	}

	public void setEnDesc(String enDesc) {
		this.enDesc = enDesc;
	}

}
