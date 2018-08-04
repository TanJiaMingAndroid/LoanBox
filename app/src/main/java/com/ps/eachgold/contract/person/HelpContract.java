package com.ps.eachgold.contract.person;

import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

/**
 * Created by 8657 on 2018/8/4.
 */
public interface HelpContract {
    interface View extends ImpBaseView{
        String getText();
        void commitSuccess(String result);
    }
    interface Presenter extends ImpBasePresenter{
        void commitText();
    }
}
