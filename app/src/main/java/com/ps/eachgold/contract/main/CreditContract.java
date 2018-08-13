package com.ps.eachgold.contract.main;

import com.ps.eachgold.bean.BankBean;
import com.ps.eachgold.bean.BankCardBean;
import com.ps.eachgold.bean.BannerBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

import java.util.List;

/**
 * Created by 8146 on 2018/1/17.
 */

public interface CreditContract {
    interface View extends ImpBaseView {
        //初始化列表
        void initRecycler();
        //添加头部
        void addHead();
        //添加尾部
        void addFoot();
        //添加监听
        void addListener();
        //getList
        void getProductListSuccess(List<LoanBean> list, Header header);

    }

    interface Presenter extends ImpBasePresenter {

        void getProductList(int page,int size,int sort);
        void saveLog(String prodType,String prodId);
    }
}
