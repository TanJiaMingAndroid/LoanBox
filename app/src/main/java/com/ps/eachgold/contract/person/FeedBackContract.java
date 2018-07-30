package com.ps.eachgold.contract.person;

import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

/**
 * Created by 8146 on 2018/1/16.
 */

public interface FeedBackContract {
    interface View extends ImpBaseView {
        String getText();
        void commitSuccess(String result);
    }

    interface Presenter extends ImpBasePresenter {
        void commitText();
    }
}
