package com.ps.eachgold.contract.loan;

import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.UserInfoBean;
import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;
import com.squareup.leakcanary.HeapDump;

/**
 * Created by 8146 on 2018/1/18.
 */

public interface InfoStepThreeContract {
    interface View extends ImpBaseView {
        void saveInfoSuccess(String result, Header header);
    }

    interface Presenter extends ImpBasePresenter {
        void saveInfo(UserInfoBean bean);
    }
}
