package com.ps.eachgold.contract.main;

import android.support.v4.app.FragmentManager;

import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;


/**
 * Created by 8146 on 2017/1/12.
 * MainActivity主页 MVP连接层
 */

public interface MainContract {
    interface View extends ImpBaseView {
        FragmentManager getMySupportManager();
    }

    interface Presenter extends ImpBasePresenter {
        void showCurrenterFragment(String fragmentValue);
    }


}
