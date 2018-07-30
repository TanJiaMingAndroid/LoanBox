package com.ps.eachgold.contract.person;

import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

/**
 * Created by 8146 on 2018/1/16.
 */

public interface ChangePswContract {
    interface View extends ImpBaseView {
        String getPsw();
        String getPswAgain();
        void changeSuccess();
        void setPswSuccess();
    }

    interface Presenter extends ImpBasePresenter {
        void changePsw();

        void setPsw();
    }
}
