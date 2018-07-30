package com.ps.eachgold.presenter;

import android.content.Context;

import com.ps.eachgold.contract.loan.InfoStepOneContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.NetClient;

/**
 * Created by 8146 on 2018/1/18.
 */

public class InfoStepOnePresenter implements InfoStepOneContract.Presenter {

    private Context mContext;
    private InfoStepOneContract.View mView;

    private ApiService mApiService;

    public InfoStepOnePresenter(Context context, InfoStepOneContract.View view) {
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


//        String card=mView.getRgCard();
//        String social=mView.getSoical();
//        String Fund=mView.getFund();

}
