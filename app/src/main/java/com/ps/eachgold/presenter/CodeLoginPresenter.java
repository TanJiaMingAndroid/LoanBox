package com.ps.eachgold.presenter;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoginBean;
import com.ps.eachgold.contract.login.CodeLoginContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;
import com.ps.eachgold.net.headerRequset.LoginCodeRequest;
import com.ps.eachgold.net.headerRequset.LoginRequest;
import com.ps.eachgold.util.CodeCountDownTimer;
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

public class CodeLoginPresenter implements CodeLoginContract.Presenter {


    private Context mContext;
    private CodeLoginContract.View mView;

    private ApiService mApiService;

    private CodeCountDownTimer myCountDownTimer;

    public CodeLoginPresenter(Context context, CodeLoginContract.View view) {
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
            LoginCodeRequest requset = new LoginCodeRequest();
            requset.setPhone(phone);
            String userStr = JSON.toJSONString(requset);
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
                            if (myCountDownTimer != null) {
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
    public void login() {
        String phone = mView.getPhone();
        String code = mView.getCode();
        if (RegexUtils.isMobileExact(phone)) {
            if (StringUtils.isEmpty(code)) {
                T.showShort("请输入验证码!");
                return;
            }
            if (code.length() < 4) {
                T.showShort("验证码长度不正确");
                return;
            }
            //IMEI
            String imei = ((TelephonyManager) mContext.getSystemService(TELEPHONY_SERVICE))
                    .getDeviceId();

            LoginRequest requset = new LoginRequest();
            requset.setPhone(phone);
            requset.setImei(imei);
            requset.setValCode(code); //需加密
            requset.setTermType(android.os.Build.MODEL);

            String userStr = JSON.toJSONString(requset);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), userStr);
            mApiService.login(requestBody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MyObserver3<LoginBean>(mContext, mView) {
                        @Override
                        public void onSuccess(LoginBean result, Header header) {
                            mView.loginSuccess(result, header);
                        }
                    });


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
