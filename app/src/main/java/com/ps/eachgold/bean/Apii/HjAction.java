/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.ps.eachgold.bean.Apii;

/**
 * @Description 功能号定义
 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
 * @date 2018年1月25日 上午9:47:05
 * @version V1.0.1
 */
public class HjAction {
	// =========================用户端接口列表=========================
	/**
	 * 用户注册
	 */
	public final static String U_REGISTER = "U0001";
	/**
	 * 用户登录
	 */
	public final static String U_LOGIN = "U0002";
	/**
	 * 用户登出
	 */
	public final static String U_LOGOUT = "U0003";
	/**
	 * 修改密码
	 */
	public final static String U_MODIFY_PWD = "U0004";
	/**
	 * 验证用户手机号是否存在(找回密码时使用)
	 */
	public final static String U_VERIFY_MOBILE = "U0005";
	/**
	 * 忘记密码-重置密码
	 */
	public final static String U_RESET_PWD = "U0006";
	/**
	 * 更换绑定手机
	 */
	public final static String U_MODIFY_PHONE = "U0007";
	/**
	 * 查看用户信息
	 */
	public final static String U_INFO_GET = "U0008";
	/**
	 * 用户信息修改
	 */
	public final static String U_INFO_EDIT = "U0009";
	/**
	 * 设置支付密码
	 */
	public final static String U_PAY_PWD_SET = "U0010";
	/**
	 * 修改支付密码
	 */
	public final static String U_PAY_PWD_EDIT = "U0011";
	/**
	 * 更换绑定手机前验证当前密码
	 */
	public final static String U_MODIFY_PHONE_CONFORM_PWD = "U0012";

	/**
	 * 创建订单，确认订单
	 */
	public final static String U_ORDER_ADD = "U0040";
	/**
	 * 订单详情
	 */
	public final static String U_ORDER_DETAIL = "U0041";
	/**
	 * 订单支付
	 */
	public final static String U_ORDER_PAY = "U0042";
	/**
	 * 订单支付详情查看
	 */
	public final static String U_ORDER_PAY_DETAIL = "U0043";
	/**
	 * 取消订单
	 */
	public final static String U_ORDER_CANCEL = "U0044";
	/**
	 * 申请退款
	 */
	public final static String U_ORDER_REFUND_APPLY = "U0045";
	/**
	 * 退款详情
	 */
	public final static String U_ORDER_REFUND_DETAIL = "U0046";

	/**
	 * 我的订单列表
	 */
	public final static String U_ORDER_MY_PAGE_LIST = "U0047";

	/**
	 * 申请退款预处理
	 */
	public final static String U_ORDER_PRE_REFUND_APPLY = "U0048";

	/**
	 * 他人根据用户的id查看用户信息
	 */
	public final static String U_GETINFO_BY_ID = "U0060";

	/**
	 * 意见反馈
	 */
	public final static String U_FEEDBACK = "U0070";
	/**
	 * 添加分享记录
	 */
	public final static String U_WX_SHARE_ADD = "U0071";

	/** --------------------------通用功能定义------------------------------ */
	/**
	 * 上传头像
	 */
	public final static String C_HEAD_UPLOAD = "F0001";
	/**
	 * 上传图片
	 */
	public final static String C_IMAGE_UPLOAD = "F0002";
	/**
	 * 图片查看
	 */
	public final static String C_IMAGE_VIEW = "F0003";
	/**
	 * 获取融云token
	 */
	public final static String C_RONG_TOKEN_GET = "C0001";

	/**
	 * 获取banner图列表
	 */
	public final static String C_BANNER_LIST = "C0002";

	/**
	 * 根据id查询城市信息
	 */
	public final static String C_AREA_SELECT_BY_ID = "C0010";
	/**
	 * 查询包含首字母的城市列表
	 */
	public final static String C_AREA_CITY_BY_CHAR = "C0011";
	/**
	 * 查询所有城市列表
	 */
	public final static String C_AREA_SELECT_ALL = "C0012";

	/**
	 * 根据pid查询下属城市列表
	 */
	public final static String C_AREA_LIST_BY_PID = "C0013";

	/** --------------------------短信功能定义------------------------------ */
	/**
	 * 用户注册短信验证码
	 */
	public final static String SMS_USER_REGISTER = "M0001";
	/**
	 * 忘记密码-发送验证码
	 */
	public final static String SMS_USER_FORGET_PWD = "M0002";
	/**
	 * 更换绑定手机-验证验证码
	 */
	public final static String SMS_USER_MODIFY_PHONE = "M0003";

	/**
	 * 代付款未支付订单提醒
	 */
	public final static String SMS_USER_ORDER_NEEDPAY = "M0004";

	/**
	 * 退款申请已受理
	 */
	public final static String SMS_USER_ORDER_FEFUND_ACCEPT = "M0005";

	/**
	 * 退款到账通知
	 */
	public final static String SMS_USER_ORDER_FEFUNDED = "M0006";

	// =================后台管理=====================
	/**
	 * 后台登录
	 */
	public final static String A_LOGIN = "A0001";
	/**
	 * 修改登录密码
	 */
	public final static String A_EDIT_PWD = "A0002";

	/**
	 * 后台用户列表
	 */
	public final static String A_SEARCH_USER = "A0003";

	/**
	 * 后台用户个人信息
	 */
	public final static String A_SEARCH_USER_INFO = "A0004";

	/**
	 * 修改后台用户个人信息
	 */
	public final static String A_EDIT_USER_INFO = "A0005";

	/**
	 * 移入移出黑名单
	 */
	public final static String A_ADD_BLACK = "A0006";

	/**
	 * 黑名单列表
	 */
	public final static String A_SEARCH_BlACK_USER = "A0007";
	/**
	 * 所有权限树状图
	 */
	public final static String A_RIGHT_TREE = "A0010";

	/**
	 * 添加角色
	 */
	public final static String A_ROLE_ADD = "A0011";
	/**
	 * 编辑角色
	 */
	public final static String A_ROLE_EDIT = "A0012";
	/**
	 * 删除角色
	 */
	public final static String A_ROLE_DEL = "A0013";
	/**
	 * 角色详情
	 */
	public final static String A_ROLE_DETAIL = "A0014";

	/**
	 * 角色分页查询
	 */
	public final static String A_ROLE_PAGE_LIST = "A0015";

	/**
	 * 添加管理员
	 */
	public final static String A_ADMIN_ADD = "A0016";
	/**
	 * 编辑管理员
	 */
	public final static String A_ADMIN_EDIT = "A0017";
	/**
	 * 删除管理员
	 */
	public final static String A_ADMIN_DEL = "A0018";
	/**
	 * 管理员详情
	 */
	public final static String A_ADMIN_DETAIL = "A0019";
	/**
	 * 管理员分页显示
	 */
	public final static String A_ADMIN_PAGE_LIST = "A0020";

	/**
	 * 添加推荐
	 */
	public final static String A_ADD_RECOMMEND = "A0033";
	/**
	 * 取消推荐
	 */
	public final static String A_CANCEL_RECOMMEND = "A0037";

	/**
	 * 订单列表
	 */
	public final static String A_SEARCH_ORDER = "A0060";
	/**
	 * 用户反馈
	 */
	public final static String A_SEARCH_USER_FEEDBACK = "A0070";

}
