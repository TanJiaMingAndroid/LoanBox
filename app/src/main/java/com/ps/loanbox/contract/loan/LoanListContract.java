package com.ps.loanbox.contract.loan;

import com.ps.loanbox.bean.Header;
import com.ps.loanbox.bean.ProductBean;
import com.ps.loanbox.contract.ImpBasePresenter;
import com.ps.loanbox.contract.ImpBaseView;

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
        void getListSuccess(List<ProductBean> list, Header header);

    }

    interface Presenter extends ImpBasePresenter {

        void getList(String loanType,int page,int size);

        void saveLog(String prodType,String prodId);
    }
}
