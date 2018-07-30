package com.ps.eachgold.presenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.ps.eachgold.contract.main.MainContract;
import com.ps.eachgold.util.FragmentUtil;

/**
 * Created by 8146 on 2018/1/15.
 * 主页P层
 */

public class MainPresenter implements MainContract.Presenter {


    private Context mContext;
    private MainContract.View mView;
    private FragmentUtil mFragmentUtil;
//    private ApiService mApiService;

    public MainPresenter(Context context, MainContract.View view) {
        this.mContext = context;
        this.mView = view;
        mFragmentUtil = new FragmentUtil();
       // mApiService = NetClient.getInstance().net().create(ApiService.class);
    }

    @Override
    public void onDetach() {
        if (mView != null) {
            mView = null;
        }
    }

    @Override
    public void showCurrenterFragment(String fragmentValue) {
        FragmentManager fragmentManager = mView.getMySupportManager();
        mFragmentUtil.changeCurrentFragment(fragmentValue, fragmentManager);
    }
}
