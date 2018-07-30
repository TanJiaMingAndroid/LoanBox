package com.ps.eachgold.contract.login;

import android.widget.TextView;

import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoginBean;
import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

/**
 * Created by 8146 on 2017/1/12.
 * 验证码登录 MVP连接层
 */

public interface CodeLoginContract {
    interface View extends ImpBaseView {
        String getPhone();
        String getCode();
        TextView getLoginTv();
        TextView getSendCode();
        void loginSuccess(LoginBean bean, Header header);
    }

    interface Presenter extends ImpBasePresenter {
        void sendCode();
        void login();
    }
}
