package com.ps.eachgold.contract.loan;

import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

/**
 * Created by 8146 on 2018/1/18.
 */

public interface InfoStepOneContract {
    interface View extends ImpBaseView {
        void next();
    }

    interface Presenter extends ImpBasePresenter {

    }
}
