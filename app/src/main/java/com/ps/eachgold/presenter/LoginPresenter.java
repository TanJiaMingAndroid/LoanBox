package com.ps.eachgold.presenter;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoginBean;
import com.ps.eachgold.contract.login.LoginContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;
import com.ps.eachgold.net.headerRequset.LoginRequest;
import com.ps.eachgold.util.RSAUtil;
import com.ps.eachgold.util.RegexUtils;
import com.ps.eachgold.util.StringUtils;
import com.ps.eachgold.util.T;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by 8146 on 2018/1/15.
 * 登录Fragment-P层
 */

public class LoginPresenter implements LoginContract.Presenter {


    private Context mContext;
    private LoginContract.View mView;

    private ApiService mApiService;

    public LoginPresenter(Context context, LoginContract.View view) {
        this.mContext = context;
        this.mView = view;
        mApiService = NetClient.getInstance().net().create(ApiService.class);
    }

    @Override
    public void login() {
       String phone=mView.getPhone();
       String psw=mView.getPsw();

           if (RegexUtils.isMobileExact(phone)) {
               if (StringUtils.isEmpty(psw)) {
                   T.showShort("请输入密码!");
               } else {
                   //IMEI
                   String imei = ((TelephonyManager) mContext.getSystemService(TELEPHONY_SERVICE))
                           .getDeviceId();
                   //加密
                   String rsa = "";
                   try {
                       rsa = RSAUtil.encrypt(psw);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }

                   LoginRequest requset = new LoginRequest();
                   requset.setPhone(phone);
                   requset.setImei(imei);
                   requset.setPassword(rsa);  //需加密
                   requset.setTermType(android.os.Build.MODEL);

                   String userStr = JSON.toJSONString(requset);
                   Log.e("111",userStr);
                   RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), userStr);
                   mApiService.login(requestBody)
                           .subscribeOn(Schedulers.io())
                           .observeOn(AndroidSchedulers.mainThread())
                           .subscribe(new MyObserver3<LoginBean>(mContext, mView) {
                               @Override
                               public void onSuccess(LoginBean result, Header header) {
                                   mView.loginSuccess(result,header);
                               }
                           });
               }
           } else {
               T.showShort("手机格式不正确!");
           }
    }

    @Override
    public void onDetach() {
        if (mView != null) {
            mView = null;
        }
    }


}
