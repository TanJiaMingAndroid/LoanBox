package com.ps.eachgold.contract.loan;

import com.ps.eachgold.bean.BankBean;
import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

import java.util.List;

/**
 * Created by 8146 on 2018/1/18.
 */

public interface HandleCardContract {
    interface View extends ImpBaseView {
        //初始化列表
        void initRecycler();
        //添加监听
        void addListener();
        //
        void getBankListSuccess(List<BankBean> list);



    }

    interface Presenter extends ImpBasePresenter {
        void getBankList();
        void saveLog(String prodType,String prodId);
    }
}
