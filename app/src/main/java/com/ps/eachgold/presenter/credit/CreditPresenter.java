package com.ps.eachgold.presenter.credit;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.BankBean;
import com.ps.eachgold.bean.BankCardBean;
import com.ps.eachgold.bean.BannerBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.bean.Page;
import com.ps.eachgold.contract.main.CreditContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;
import com.ps.eachgold.net.headerRequset.BankCardlistRequest;
import com.ps.eachgold.net.headerRequset.BankListRequset;
import com.ps.eachgold.net.headerRequset.BannerRequset;
import com.ps.eachgold.net.headerRequset.CliclLogRequest;
import com.ps.eachgold.util.SPutils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;


/**
 * Created by 8146 on 2018/1/18.
 */

public class CreditPresenter implements CreditContract.Presenter {

    private Context mContext;
    private CreditContract.View mView;


    private ApiService mApiService;

    public CreditPresenter(Context context, CreditContract.View view) {
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
    public void getProductList(int page, int size,int sort) {
        BankCardlistRequest requset=new BankCardlistRequest();
        requset.setSort(sort);
        requset.setStatus(2);
        Page mPage=new Page();
        mPage.setIndex(page);
        mPage.setSize(size);
        requset.getHeader().setPage(mPage);
        String userStr = JSON.toJSONString(requset);
        Log.e("creditPPP",userStr);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),userStr);
        mApiService.getMarketProductList(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<List<LoanBean>>(mContext, mView) {
                    @Override
                    public void onSuccess(List<LoanBean> list, Header header) {
                        if(mView != null){
                            mView.getProductListSuccess(list,header);
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
}
