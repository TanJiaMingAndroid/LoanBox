package com.ps.eachgold.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.contract.person.ChangePswContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;
import com.ps.eachgold.net.headerRequset.UpdateRequest;
import com.ps.eachgold.util.RSAUtil;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.util.StringUtils;
import com.ps.eachgold.util.T;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 8146 on 2018/1/16.
 */

public class ChangePswPresenter implements ChangePswContract.Presenter {

    private Context mContext;
    private ChangePswContract.View mView;

    private ApiService mApiService;

    public ChangePswPresenter(Context context, ChangePswContract.View view) {
        this.mContext = context;
        this.mView = view;
       mApiService = NetClient.getInstance().net().create(ApiService.class);
    }


    @Override
    public void changePsw() {


        String psw = mView.getPsw(); //原密码
        String pswAgain = mView.getPswAgain(); //新密码
        if(StringUtils.isEmpty(psw)||psw.length()<6){
            T.showShort("请按规范输入密码!");
            return;
        }
        if(StringUtils.isEmpty(pswAgain)||pswAgain.length()<6){
            T.showShort("请按规范输入密码!");
            return;
        }

        if(psw.equals(pswAgain)){
            T.showShort("新密码不能和原密码相同");
            return;
        }

//加密
        String rsa = "";
        String rsa2 = "";
        try {
            rsa = RSAUtil.encrypt(psw);
            rsa2 = RSAUtil.encrypt(pswAgain);
        } catch (Exception e) {
            e.printStackTrace();
        }

        UpdateRequest requset = new UpdateRequest();
        requset.setPassword(rsa);
        requset.setNewPassword(rsa2);

        String id = (String) SPutils.get(mContext, "sessionid", "");
        requset.getHeader().setSessionid(id);

        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), userStr);

        mApiService.UpdatePsw(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<String>(mContext, mView) {
                    @Override
                    public void onSuccess(String result, Header header) {
                        if (header.getCode() == 0) {
                            mView.changeSuccess();
                        }
                    }
                });

    }

    @Override
    public void setPsw() {
        String psw = mView.getPsw(); //原密码
        String pswAgain = mView.getPswAgain(); //新密码
        if(StringUtils.isEmpty(psw)||psw.length()<6){
            T.showShort("请按规范输入密码!");
            return;
        }
        if(StringUtils.isEmpty(pswAgain)||pswAgain.length()<6){
            T.showShort("请按规范输入密码!");
            return;
        }

        if(!psw.equals(pswAgain)){
            T.showShort("两次密码输入不一致");
            return;
        }

//加密
        String rsa = "";
        String rsa2 = "";
        try {
            rsa = RSAUtil.encrypt(psw);
            rsa2 = RSAUtil.encrypt(pswAgain);
        } catch (Exception e) {
            e.printStackTrace();
        }

        UpdateRequest requset = new UpdateRequest();
        requset.setNewPassword(rsa);
        requset.setConfirmPassword(rsa2);

        String id = (String) SPutils.get(mContext, "sessionid", "");
        requset.getHeader().setSessionid(id);

        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), userStr);

        mApiService.UpdatePsw(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<String>(mContext, mView) {
                    @Override
                    public void onSuccess(String result, Header header) {
                        if (header.getCode() == 0) {
                            mView.setPswSuccess();
                        }
                    }
                });
    }

    @Override
    public void onDetach() {
        if (mView != null) {
            mView = null;
        }
    }

}
