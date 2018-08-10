package com.ps.eachgold.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.bean.Page;
import com.ps.eachgold.contract.loan.LoanListContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;
import com.ps.eachgold.net.headerRequset.CliclLogRequest;
import com.ps.eachgold.net.headerRequset.LoanListRequset;
import com.ps.eachgold.util.SPutils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 8146 on 2018/1/18.
 */

public class LoanListPresenter implements LoanListContract.Presenter {


    private Context mContext;
    private LoanListContract.View mView;

    private ApiService mApiService;

    public LoanListPresenter(Context context, LoanListContract.View view) {
        this.mContext = context;
        this.mView = view;
        mApiService = NetClient.getInstance().net().create(ApiService.class);
    }

    @Override
    public void getList(String loanType, int page, int size) {
        //  额度高-high、利息低-low、放款快-fast hot-热门、代还-repay
        LoanListRequset requset=new LoanListRequset();
        //requset.setReqType(loanType);
        Page mPage=new Page();
        mPage.setIndex(page);
        mPage.setSize(size);
        requset.getHeader().setPage(mPage);

        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),userStr);
        mApiService.getLoanList(requestBody)
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
