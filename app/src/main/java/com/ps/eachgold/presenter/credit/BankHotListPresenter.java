package com.ps.eachgold.presenter.credit;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.BankCardBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.Page;
import com.ps.eachgold.contract.credit.BankHotListContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;
import com.ps.eachgold.net.headerRequset.BankCardlistRequest;
import com.ps.eachgold.net.headerRequset.CliclLogRequest;
import com.ps.eachgold.util.SPutils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 8146 on 2018/1/18.
 */

public class BankHotListPresenter implements BankHotListContract.Presenter {
    private Context mContext;
    private BankHotListContract.View mView;

    private ApiService mApiService;
    CompositeDisposable disposables = new CompositeDisposable();
    public BankHotListPresenter(Context context, BankHotListContract.View view) {
        this.mContext = context;
        this.mView = view;
        mApiService = NetClient.getInstance().net().create(ApiService.class);
    }

    @Override
    public void getCardList(int page, int size) {
        BankCardlistRequest requset=new BankCardlistRequest();
        requset.setReqType("hot");
        requset.setBankId("0");
        Page mPage=new Page();
        mPage.setIndex(page);
        mPage.setSize(size);
        requset.getHeader().setPage(mPage);
        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),userStr);

        mApiService.getBankCardist(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<List<BankCardBean>>(mContext, mView) {
                    @Override
                    public void onSuccess(List<BankCardBean> bankBeans, Header header) {
                        mView.getBankCardListSuccess(bankBeans,header);
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
