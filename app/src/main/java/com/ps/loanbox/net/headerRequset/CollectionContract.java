package com.ps.loanbox.net.headerRequset;

import com.ps.loanbox.bean.Header;
import com.ps.loanbox.bean.ProductBean;
import com.ps.loanbox.contract.ImpBasePresenter;
import com.ps.loanbox.contract.ImpBaseView;

import java.util.List;

/**
 * Created by 8303 on 2018/8/15.
 */
public interface CollectionContract {
    interface Model {
    }

    interface View extends ImpBaseView {
        /**
         * 获取我的收藏成功
         * @param orderBeanList
         * @param header
         */
        void getCollectionSuccess(List<ProductBean> collectionBeanList, Header header);
    }

    interface Presenter extends ImpBasePresenter {
        /**
         * 获取我的收藏
         */
        void getCollection();
    }
}