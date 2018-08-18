package com.ps.loanbox.net.headerRequset;

import com.ps.loanbox.contract.ImpBasePresenter;
import com.ps.loanbox.contract.ImpBaseView;

/**
 * Created by 8303 on 2018/8/15.
 */
public interface PicContract {
    interface Model {
    }

    interface View extends ImpBaseView {
        /**
         * 照片的base64
         * @return
         */
        String getPic();
        /**
         * 照片的类型 png/jpe/jpeg
         * @return
         */
        String getImageType();
        /**
         * 上传照片成功
         * @param picUrl 照片的url
         */
        void submitPicSuccess(String picUrl);

    }

    interface Presenter extends ImpBasePresenter {
        /**
         * 上传照片
         */
        void submitPic();

    }
}
