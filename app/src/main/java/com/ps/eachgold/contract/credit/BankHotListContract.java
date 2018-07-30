package com.ps.eachgold.contract.credit;

import com.ps.eachgold.bean.BankCardBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

import java.util.List;

/**
 * Created by 8146 on 2018/1/17.
 */

public interface BankHotListContract {
    interface View extends ImpBaseView {
        //初始化列表
        void initRecycler();

        //添加监听
        void addListener();

        //银行卡
        void getBankCardListSuccess(List<BankCardBean> list, Header header);

    }

    interface Presenter extends ImpBasePresenter {
        void getCardList(int page,int size);
        void saveLog(String prodType,String prodId);
    }
}
