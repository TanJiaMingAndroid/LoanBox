package com.ps.eachgold.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.BankBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.contract.credit.CardProgressContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;
import com.ps.eachgold.net.headerRequset.BankListRequset;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 8146 on 2018/1/18.
 */

public class CardProgressPresenter implements CardProgressContract.Presenter {

    private Context mContext;
    private CardProgressContract.View mView;

    private ApiService mApiService;

    public CardProgressPresenter(Context context, CardProgressContract.View view) {
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
    public void getBankList() {
        BankListRequset requset=new BankListRequset();
        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),userStr);

        mApiService.getBanklist(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<List<BankBean>>(mContext, mView) {

                    @Override
                    public void onSuccess(List<BankBean> bankBeans, Header header) {
                        mView.getBankListSuccess(bankBeans);
                    }
                });
    }
}
