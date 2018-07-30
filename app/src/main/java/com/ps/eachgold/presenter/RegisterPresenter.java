package com.ps.eachgold.presenter;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.RegisterBean;
import com.ps.eachgold.contract.login.RegisterContract;
import com.ps.eachgold.net.ApiService;

import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;

import com.ps.eachgold.net.headerRequset.RegisterCodeRequest;

import com.ps.eachgold.net.headerRequset.RegisterRequest;
import com.ps.eachgold.util.CodeCountDownTimer;
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
 */

public class RegisterPresenter implements RegisterContract.Presenter {


    private Context mContext;
    private RegisterContract.View mView;
    private ApiService mApiService;
    private CodeCountDownTimer myCountDownTimer;
    public RegisterPresenter(Context context, RegisterContract.View view) {
        this.mContext = context;
        this.mView = view;
        mApiService = NetClient.getInstance().net().create(ApiService.class);
    }

    @Override
    public void sendCode() {
        String phone = mView.getPhone();
        if (RegexUtils.isMobileExact(phone)) {
            myCountDownTimer = new CodeCountDownTimer(60000, 1000, mView.getSendCode());
            myCountDownTimer.start();
            RegisterCodeRequest requset = new RegisterCodeRequest();
            requset.setPhone(phone);
            String userStr = JSON.toJSONString(requset);
           Log.e("11111",userStr);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), userStr);
            mApiService.sendCode(requestBody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MyObserver3<String>(mContext, mView) {
                        @Override
                        public void onSuccess(String result, Header header) {
                            if (header.getCode() == 0) {
                                T.showShort("验证码发送成功");
                            }
                        }

                        @Override
                        public void onFailure(int code, String message) {
                            super.onFailure(code, message);
                            if (myCountDownTimer != null){
                                myCountDownTimer.cancel();
                                mView.getSendCode().setText("重新发送");
                                mView.getSendCode().setClickable(true);
                            }
                        }
                    });

        } else {
            T.showShort("手机格式不正确!");
        }

    }

    @Override
    public void register() {
        String phone = mView.getPhone();
        String code = mView.getCode();
        String psw = mView.getPsw();
        String pswAgain = mView.getPswAgain();
        if (!RegexUtils.isMobileExact(phone)) {
            T.showShort("手机格式不正确!");
            return;
        }
        if (StringUtils.isEmpty(code)) {
            T.showShort("请输入验证码!");
            return;
        }
        if (code.length() < 4) {
            T.showShort("验证码长度不正确");
            return;
        }
        if (StringUtils.isEmpty(psw) || psw.length() < 6) {
            T.showShort("请按规范输入密码!");
            return;
        }
        if (StringUtils.isEmpty(pswAgain) || pswAgain.length() < 6) {
            T.showShort("请按规范输入密码!");
            return;
        }
        if (!psw.equals(pswAgain)) {
            T.showShort("两次密码不一致");
            return;
        }
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

        RegisterRequest requset = new RegisterRequest();
        requset.setPhone(phone);
        requset.setImei(imei);
        requset.setPassword(rsa);  //需加密
        requset.setValCode(code);
        requset.setTermType(android.os.Build.MODEL);

        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), userStr);
        mApiService.register(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<RegisterBean>(mContext, mView) {
                    @Override
                    public void onSuccess(RegisterBean result, Header header) {
                        T.showShort("注册成功");
                        mView.registerSuccess(result,header);
                    }
                });
    }


    @Override
    public void openAgree() {
        //打开H5
    }

    @Override
    public void onDetach() {
        if (mView != null) {
            mView = null;
        }
    }

}
