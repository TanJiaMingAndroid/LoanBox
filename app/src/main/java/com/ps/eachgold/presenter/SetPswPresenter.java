package com.ps.eachgold.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.contract.login.SetPswContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;
import com.ps.eachgold.net.headerRequset.ResetPswRequest;
import com.ps.eachgold.util.RSAUtil;
import com.ps.eachgold.util.StringUtils;
import com.ps.eachgold.util.T;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 8146 on 2018/1/18.
 */

public class SetPswPresenter implements SetPswContract.Presenter {


    private Context mContext;
    private SetPswContract.View mView;

    private ApiService mApiService;

    public SetPswPresenter(Context context, SetPswContract.View view) {
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
    public void sure(String phone) {

        String psw = mView.getPsw();
        String pswAgain = mView.getPswAgain();

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

        //加密
        String rsa = "";
        try {
            rsa = RSAUtil.encrypt(psw);
        } catch (Exception e) {
            e.printStackTrace();
        }


        ResetPswRequest requset=new ResetPswRequest();

        requset.setPhone(phone);
        requset.setPassword(rsa);
        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),userStr);
        mApiService.resetPsw(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<String>(mContext, mView) {
                    @Override
                    public void onSuccess(String result, Header header) {
                        T.showShort("修改成功！请重新登录");
                        mView.sureSuccess();
                    }
                });

    }
}
