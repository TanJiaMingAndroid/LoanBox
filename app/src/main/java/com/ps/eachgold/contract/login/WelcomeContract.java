package com.ps.eachgold.contract.login;

import com.ps.eachgold.bean.BannerBean;
import com.ps.eachgold.contract.ImpBasePresenter;
import com.ps.eachgold.contract.ImpBaseView;

import java.util.List;

/**
 * Created by 8146 on 2017/1/12.
 * 欢迎页 MVP连接层
 */
public interface WelcomeContract {
    interface View extends ImpBaseView {
        //检测是否第一次
        void checkFirst();
        int getType();
        //获取图片域名
        void getImgUrlSuccess(String baseUrl);
        //获取图片域名
        void getAdImgSuccess(List<BannerBean> list);

        void openGateSuccess(String result);


    }

    interface Presenter extends ImpBasePresenter {

        void getImgUrl(int type);

        void getAdImg();

        void openGate();


    }

}
