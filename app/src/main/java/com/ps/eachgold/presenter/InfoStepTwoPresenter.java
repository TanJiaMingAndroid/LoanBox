package com.ps.eachgold.presenter;

import android.content.Context;

import com.ps.eachgold.contract.loan.InfoStepTwoContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.NetClient;

/**
 * Created by 8146 on 2018/1/18.
 */

public class InfoStepTwoPresenter implements InfoStepTwoContract.Presenter {

    private Context mContext;
    private InfoStepTwoContract.View mView;

    private ApiService mApiService;

    public InfoStepTwoPresenter(Context context, InfoStepTwoContract.View view) {
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
}
