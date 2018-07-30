package com.ps.eachgold.contract.person;

import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LaberBean;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

import java.util.List;

/**
 * Created by 8146 on 2018/2/1.
 */

public interface PersonContract {

    interface View extends ImpBaseView {
        //我的专属LoanBean
        void getMyExclusiveSuccess(List<LoanBean> list, Header header);
        //查看更多--标签
        void getLabelSuccess(List<LaberBean> list);

        void getMsg(String count);
    }

    interface Presenter extends ImpBasePresenter {
        void getMyExclusive();

        void getLabel();

        void getMsgCount();
    }
}
