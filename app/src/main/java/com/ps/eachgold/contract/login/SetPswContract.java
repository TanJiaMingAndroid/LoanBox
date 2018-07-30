package com.ps.eachgold.contract.login;

import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

/**
 * Created by 8146 on 2018/1/18.
 */

public interface SetPswContract {
    interface View extends ImpBaseView {
        String getPsw();
        String getPswAgain();
        void sureSuccess();
    }

    interface Presenter extends ImpBasePresenter {

        void sure(String phone);

    }
}
