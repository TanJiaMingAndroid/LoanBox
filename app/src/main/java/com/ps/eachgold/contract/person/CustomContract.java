package com.ps.eachgold.contract.person;

import com.ps.eachgold.bean.BankCardBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

import java.util.List;

/**
 * Created by 8146 on 2018/1/16.
 */

public interface CustomContract {
    interface View extends ImpBaseView {
        //初始化列表
        void initRecycler();
        //添加监听
        void addListener();

        void getListSuccess(List<LoanBean> list, Header header);
        void getCardListSuccess(List<BankCardBean> list, Header header);
    }

    interface Presenter extends ImpBasePresenter {

        void getList(String labelName);
        void getCardList(String labelName);

        void saveLog(String prodType,String prodId);

    }
}
