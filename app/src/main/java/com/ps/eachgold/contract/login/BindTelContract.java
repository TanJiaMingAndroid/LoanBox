package com.ps.eachgold.contract.login;

import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoginBean;
import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

/**
 * Created by 8657 on 2018/8/6.
 */
public interface BindTelContract {
    interface View extends ImpBaseView{
        String getPhone();
        void loginSuccess(LoginBean bean, Header header);

    }
    interface Presenter extends ImpBasePresenter{

    }
}
