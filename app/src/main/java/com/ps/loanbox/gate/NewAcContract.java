package com.ps.loanbox.gate;

import com.ps.loanbox.bean.Header;
import com.ps.loanbox.contract.ImpBasePresenter;
import com.ps.loanbox.contract.ImpBaseView;



/**
 * Created by 8146 on 2018/2/9.
 */

public class NewAcContract {
    interface View extends ImpBaseView {
        void getData(NewDetailBean bean, Header header);
    }

    interface Presenter extends ImpBasePresenter {
        void getData(String id);

    }
}
