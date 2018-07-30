package com.ps.eachgold.contract.loan;

import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

import java.util.List;

/**
 * Created by 8146 on 2018/1/17.
 */

public interface LoanListContract {
    interface View extends ImpBaseView {
        //初始化列表
        void initRecycler();
        //添加监听
        void addListener();
        //getList
        void getListSuccess(List<LoanBean> list, Header header);

    }

    interface Presenter extends ImpBasePresenter {

        void getList(String loanType,int page,int size);

        void saveLog(String prodType,String prodId);
    }
}
