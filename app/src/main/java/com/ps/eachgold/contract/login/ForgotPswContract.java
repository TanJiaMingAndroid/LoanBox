package com.ps.eachgold.contract.login;

import android.widget.TextView;

import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

/**
 * Created by 8146 on 2018/1/18.
 */

public interface ForgotPswContract {
    interface View extends ImpBaseView {
        String getPhone();
        String getCode();
        TextView getSendCode();
        void checkSuccess();
    }

    interface Presenter extends ImpBasePresenter {
        void sendCode();
        void check();
    }
}
