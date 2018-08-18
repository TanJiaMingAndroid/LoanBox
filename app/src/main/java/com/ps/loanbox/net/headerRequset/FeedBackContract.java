package com.ps.loanbox.net.headerRequset;

import com.ps.loanbox.contract.ImpBasePresenter;
import com.ps.loanbox.contract.ImpBaseView;

/**
 * Created by 8146 on 2018/1/16.
 */

public interface FeedBackContract {
    interface View extends ImpBaseView {
        String getText();
        String getPicUrl();
        void commitSuccess(String result);
    }

    interface Presenter extends ImpBasePresenter {
        void commitText();
    }
}
