package com.ps.eachgold.contract.login;

import android.widget.TextView;

import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.RegisterBean;
import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

/**
 * Created by 8146 on 2018/1/15.
 */

public interface RegisterContract {
    interface View extends ImpBaseView {
        String getPhone();
        String getCode();
        String getPsw();
        String getPswAgain();
        TextView getSendCode();
        void registerSuccess(RegisterBean bean, Header header);
    }

    interface Presenter extends ImpBasePresenter {
        void sendCode();
        void register();
        void openAgree();
    }
}
