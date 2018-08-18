package com.ps.loanbox.contract.loan;

import com.ps.loanbox.contract.ImpBasePresenter;
import com.ps.loanbox.contract.ImpBaseView;

/**
 * Created by 8146 on 2018/1/22.
 */

public interface HotLoanContract {
    interface View extends ImpBaseView {
        //初始化列表
        void initRecycler();
        //添加监听
        void addListener();
    }

    interface Presenter extends ImpBasePresenter {

    }
}
