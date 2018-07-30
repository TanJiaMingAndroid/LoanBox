package com.ps.eachgold.contract.creditReport;

import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

/**
 * Created by 8146 on 2018/1/16.
 */

public interface CreditReportContract {
    interface View extends ImpBaseView {
        //初始化列表
        void initRecycler();

        //添加头部
        void addHead();

        //添加监听
        void addListener();
    }
    interface Presenter extends ImpBasePresenter {

    }
}
