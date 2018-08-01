package com.ps.eachgold.net;

import com.ps.eachgold.bean.BankBean;
import com.ps.eachgold.bean.BankCardBean;
import com.ps.eachgold.bean.BannerBean;
import com.ps.eachgold.bean.BaseHeaderBean;
import com.ps.eachgold.bean.CustomBean;
import com.ps.eachgold.bean.LaberBean;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.bean.LoginBean;
import com.ps.eachgold.bean.MessageBean;
import com.ps.eachgold.bean.RegisterBean;
import com.ps.eachgold.gate.NewBean;
import com.ps.eachgold.gate.NewDetailBean;


import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by 8146 on 2018/1/31.
 */

public interface ApiService {

    /**
     * 基本图片域名
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<String>> getBaseImgUrl(@Body RequestBody requestBody);

    /**
     * 注册
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<RegisterBean>> register(@Body RequestBody requestBody);

    /**
     *  登录
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<LoginBean>> login(@Body RequestBody requestBody);




    /**
     * 发送验证码  注册-登录 -忘记密码
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<String>> sendCode(@Body RequestBody requestBody);



    /**
     * 验证-忘记密码-验证码
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<String>> checkForgetCode(@Body RequestBody requestBody);



    /**
     * 重置密码
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<String>>  resetPsw(@Body RequestBody requestBody);

    /**
     * 修改密码
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<String>> UpdatePsw(@Body RequestBody requestBody);

    /**
     * 银行信息接口
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<List<BankBean>>> getBanklist(@Body RequestBody requestBody);


    /**
     * Banner 图片类型（1首页banner2信用卡banner3代还banner4启动页）
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<List<BannerBean>>> getBanner(@Body RequestBody requestBody);


    /**
     * 意见反馈
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<String>>  saveFeedback(@Body RequestBody requestBody);

    /**
     * 保存信息
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<String>>  saveInfo(@Body RequestBody requestBody);

    /**
     * 信用卡    //请求类型：bank（表示 银行下的信用卡），hot(表示热门的信用卡)
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<List<BankCardBean>>>  getBankCardist(@Body RequestBody requestBody);


    /**
     * 贷款类     "reqType":"hot"  //请求类型 ：额度高-high、利息低-low、放款快-fast hot-热门 代还-repay
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<List<LoanBean>>>  getLoanList(@Body RequestBody requestBody);

    /**
     * 我的3 个贷款产品 EXCLUSIVE0001
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<List<LoanBean>>>  getExclusive(@Body RequestBody requestBody);

    /**
     * 标签 名字EXCLUSIVE0002
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<List<LaberBean>>>  getCustomLaber(@Body RequestBody requestBody);


    /**
     * 标签分类列表  EXCLUSIVE0003   //SuperMarket 贷款产品 Repayment 信用卡代还返回
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<List<LoanBean>>>  getCustomList(@Body RequestBody requestBody);

    /**
     * 标签分类列表  EXCLUSIVE0003 信用卡
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<List<BankCardBean>>>  getCardCustomList(@Body RequestBody requestBody);

    /**
     * 消息
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<List<MessageBean>>>  getMsg(@Body RequestBody requestBody);


    /**
     * 消息
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<String>>  getMsgCount(@Body RequestBody requestBody);


    /**
     * 消息
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<String>>  saveLog(@Body RequestBody requestBody);


    /**
     * 开关
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<String>>  getGate(@Body RequestBody requestBody);


    /**
     * 列表
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<List<NewBean>>>  getTypeList(@Body RequestBody requestBody);


    /**
     * 详情
     */
    @POST("/hjApi/doCall")
    Observable<BaseHeaderBean<NewDetailBean>>  getDetail(@Body RequestBody requestBody);

}