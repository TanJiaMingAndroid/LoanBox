package com.ps.eachgold.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.UserInfoBean;
import com.ps.eachgold.contract.loan.InfoStepThreeContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;
import com.ps.eachgold.net.headerRequset.InfoStepRequest;
import com.ps.eachgold.util.SPutils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 8146 on 2018/1/18.
 */

public class InfoStepThreePresenter implements InfoStepThreeContract.Presenter {

    private Context mContext;
    private InfoStepThreeContract.View mView;
    private ApiService mApiService;

    public InfoStepThreePresenter(Context context, InfoStepThreeContract.View view) {
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
    public void saveInfo(UserInfoBean bean) {
        InfoStepRequest requset = new InfoStepRequest();
        requset.setName(bean.getName());
        requset.setIdCard(bean.getIdCard());
        requset.setSex(bean.getSex());
        requset.setEducation(bean.getEdusID());
        requset.setJob(bean.getIdentityID());
        requset.setIncome(bean.getMonthId());
        requset.setCreditCard(bean.getCreditFlag());
        requset.setCreditCardNum(bean.getCreditCount());
        requset.setCreditCardMoney(bean.getCreditId());
        requset.setSocialSecurity(bean.getSocialId());
        requset.setAccumulationFund(bean.getFundId());
        requset.setHouse(bean.getHouseId());
        requset.setCar(bean.getCarId());



        String id = (String) SPutils.get(mContext, "sessionid", "");
        requset.getHeader().setSessionid(id);

        String str = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), str);

        mApiService.saveInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<String>(mContext, mView) {
                    @Override
                    public void onSuccess(String result, Header header) {
                        mView.saveInfoSuccess(result,header);
                    }
                });
    }
}
