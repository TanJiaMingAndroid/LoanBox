package com.ps.eachgold.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.contract.login.ForgotPswContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;
import com.ps.eachgold.net.headerRequset.CheckForgotCodeRequest;
import com.ps.eachgold.net.headerRequset.ForgotPswCodeRequest;
import com.ps.eachgold.util.CodeCountDownTimer;
import com.ps.eachgold.util.RegexUtils;
import com.ps.eachgold.util.StringUtils;
import com.ps.eachgold.util.T;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 8146 on 2018/1/18.
 */

public class ForgotPswPresenter implements ForgotPswContract.Presenter {


    private Context mContext;
    private ForgotPswContract.View mView;

    private ApiService mApiService;

    public ForgotPswPresenter(Context context, ForgotPswContract.View view) {
        this.mContext = context;
        this.mView = view;
        mApiService = NetClient.getInstance().net().create(ApiService.class);
    }

    @Override
    public void onDetach() {
        if (mView != null) {
            mView = null;
        }
    }

    @Override
    public void sendCode() {
        String phone = mView.getPhone();

        if (RegexUtils.isMobileExact(phone)) {
            CodeCountDownTimer myCountDownTimer = new CodeCountDownTimer(60000, 1000, mView.getSendCode());
            myCountDownTimer.start();

            ForgotPswCodeRequest requset = new ForgotPswCodeRequest();
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
                    });


        } else {
            T.showShort("手机格式不正确!");
        }
    }

    @Override
    public void check() {
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

            CheckForgotCodeRequest requset = new CheckForgotCodeRequest();
            requset.setPhone(phone);
            requset.setValCode(code);
            String userStr = JSON.toJSONString(requset);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), userStr);

            mApiService.checkForgetCode(requestBody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MyObserver3<String>(mContext, mView) {
                        @Override
                        public void onSuccess(String result, Header header) {
                            if (header.getCode() == 0) {
                              mView.checkSuccess();
                            }
                        }
                    });
        } else {
            T.showShort("手机格式不正确!");
        }
    }
}
