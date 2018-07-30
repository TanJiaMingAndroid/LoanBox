package com.ps.eachgold.gate;

import com.ps.eachgold.bean.BannerBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;
import com.ps.eachgold.gate.NewBean;

import java.util.List;

/**
 * Created by 8146 on 2018/2/9.
 */

public class NewContract {
    interface View extends ImpBaseView {
        //初始化列表
        void initRecycler();
        //添加监听
        void addListener();
        //getList
        void getListSuccess(List<NewBean> list, Header header);
    }

    interface Presenter extends ImpBasePresenter {
        void getList(String category,int page,int size);
    }
}
