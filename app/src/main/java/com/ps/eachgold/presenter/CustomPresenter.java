package com.ps.eachgold.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.BankCardBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.contract.person.CustomContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;
import com.ps.eachgold.net.headerRequset.CliclLogRequest;
import com.ps.eachgold.net.headerRequset.CustomRequest;
import com.ps.eachgold.util.SPutils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 8146 on 2018/2/1.
 */

public class CustomPresenter implements CustomContract.Presenter {


    private Context mContext;
    private CustomContract.View mView;

    private ApiService mApiService;

    public CustomPresenter(Context context, CustomContract.View view) {
        this.mContext = context;
        this.mView = view;
        mApiService = NetClient.getInstance().net().create(ApiService.class);
    }


    @Override
    public void getList(String labelName) {
        // //SuperMarket 贷款产品 Repayment 信用卡代还 Card 信用卡产品

        String phone= (String) SPutils.get(mContext,"phone","");

        CustomRequest requset=new CustomRequest();
        requset.setLabelName(labelName);
        requset.setMoblie(phone);
        requset.setOpenId("");
        String id = (String) SPutils.get(mContext, "sessionid", "");
        requset.getHeader().setSessionid(id);

        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),userStr);
        mApiService.getCustomList(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<List<LoanBean>>(mContext, mView) {
                    @Override
                    public void onSuccess(List<LoanBean> list, Header header) {
                        if(mView != null){
                            mView.getListSuccess(list,header);
                        }
                    }
                });
    }

    @Override
    public void getCardList(String labelName) {

        String phone= (String) SPutils.get(mContext,"phone","");

        CustomRequest requset=new CustomRequest();
        requset.setLabelName(labelName);
        requset.setMoblie(phone);
        requset.setOpenId("");
        String id = (String) SPutils.get(mContext, "sessionid", "");
        requset.getHeader().setSessionid(id);

        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),userStr);
        mApiService.getCardCustomList(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<List<BankCardBean>>(mContext, mView) {
                    @Override
                    public void onSuccess(List<BankCardBean> list, Header header) {
                        if(mView != null){
                            mView.getCardListSuccess(list,header);
                        }
                    }
                });
    }

    @Override
    public void saveLog(String prodType, String prodId) {
        String phone= (String) SPutils.get(mContext,"phone","");
        if(!"".equals(phone)){
            CliclLogRequest requset=new CliclLogRequest();
            requset.setMoblie(phone);
            requset.setProdId(prodId);
            requset.setProdType(prodType);

            String userStr = JSON.toJSONString(requset);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),userStr);
            mApiService.saveLog(requestBody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MyObserver3<String>(mContext, mView) {
                        @Override
                        public void onSuccess(String result, Header header) {

                        }
                    });
        }
    }

    @Override
    public void onDetach() {
        if (mView != null) {
            mView = null;
        }
    }

}

