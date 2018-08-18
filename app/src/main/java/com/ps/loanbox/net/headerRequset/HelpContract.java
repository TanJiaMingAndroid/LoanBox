package com.ps.loanbox.net.headerRequset;

import com.ps.loanbox.bean.HelpBean;
import com.ps.loanbox.contract.ImpBasePresenter;
import com.ps.loanbox.contract.ImpBaseView;

import java.util.List;

/**
 * Created by 8303 on 2018/8/15.
 */
public interface HelpContract {
    interface Model {
    }

    interface View extends ImpBaseView {
        void getHelpSuccess(List<HelpBean> helpBeanList);
    }
    interface Presenter extends ImpBasePresenter {
        void getHelp();
    }
}
