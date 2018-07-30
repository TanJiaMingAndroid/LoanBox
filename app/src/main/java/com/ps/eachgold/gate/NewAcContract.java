package com.ps.eachgold.gate;

import com.ps.eachgold.bean.Header;
import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;



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
