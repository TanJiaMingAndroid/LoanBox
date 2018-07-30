package com.ps.eachgold.contract.main;

import com.ps.eachgold.bean.BannerBean;

import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoanBean;

import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

import java.util.List;

/**
 * Created by 8146 on 2018/1/15.
 */

public interface LoanContract {
    interface View extends ImpBaseView {
        //初始化列表
        void initRecycler();
        //添加头部
        void addHead();
        //添加尾部
        void addFoot();
        //添加监听
        void addListener();
        //Banner
        void getBannerSuccess(List<BannerBean> list);
        //getList
        void getListSuccess(List<LoanBean> list, Header header);
    }

    interface Presenter extends ImpBasePresenter {
        void getBanner();

        void getList(int page,int size);

        void saveLog(String prodType,String prodId);
    }
}
