package com.ps.loanbox.net.headerRequset;

import com.ps.loanbox.bean.Header;
import com.ps.loanbox.bean.MessageBean;
import com.ps.loanbox.contract.ImpBasePresenter;
import com.ps.loanbox.contract.ImpBaseView;

import java.util.List;

/**
 * Created by 8146 on 2018/1/16.
 */

public interface MessageContract {
    interface View extends ImpBaseView {
        //初始化列表
        void initRecycler();
        //添加监听
        void addListener();

        void getListSuccess(List<MessageBean> list, Header header);
    }

    interface Presenter extends ImpBasePresenter {

        void getList(int page, int size);
    }
}
