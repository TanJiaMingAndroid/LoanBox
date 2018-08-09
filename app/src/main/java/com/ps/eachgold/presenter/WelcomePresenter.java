package com.ps.eachgold.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.BannerBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.contract.login.WelcomeContract;
import com.ps.eachgold.gate.GateRequest;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;
import com.ps.eachgold.net.headerRequset.BannerRequset;
import com.ps.eachgold.net.headerRequset.BaseImgRequset;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 8146 on 2018/1/23.
 */

public class WelcomePresenter implements WelcomeContract.Presenter {
    private Context mContext;
    private WelcomeContract.View mView;

   // private ApiService mApiService;
    private ApiService mApiService;

    public WelcomePresenter(Context context, WelcomeContract.View view) {
        this.mContext = context;
        this.mView = view;
       // mApiService = NetClient.getInstance().net().create(ApiService.class);
        mApiService = NetClient.getInstance().net().create(ApiService.class);
    }

    @Override
    public void getImgUrl(int type) {
        //获取域名
//        mApiService.getImgUrl()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new MyObserver<String>(mContext, mView) {
//                    @Override
//                    public void onSuccess(String baseUrl) {
//
//                        mView.getImgUrlSuccess(baseUrl);
//                    }
//                });
        //获取域名

        BaseImgRequset requset=new BaseImgRequset();
        requset.setBannerType(type);
        String str = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),str);
        mApiService.getBaseImgUrl(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<String>(mContext, mView) {
                    @Override
                    public void onSuccess(String baseUrl, Header header) {
                        if (mView!=null) {
                            mView.getImgUrlSuccess(baseUrl);
                        }
                    }
                });
    }



    @Override
    public void getAdImg() {
        //获取Banner  1首页banner2信用卡banner3代还banner4启动页
//        mApiService.getBanner(4)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new MyObserver<List<BannerBean>>(mContext, mView) {
//                    @Override
//                    public void onSuccess(List<BannerBean> list) {
//                        if(mView != null){
//                            mView.getAdImgSuccess(list);
//                        }
//                    }
//                });

        //获取Banner  1首页banner2信用卡banner3代还banner4启动页
        BannerRequset requset=new BannerRequset();
        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),userStr);
        mApiService.getBanner(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<List<BannerBean>>(mContext, mView) {
                    @Override
                    public void onSuccess(List<BannerBean> list, Header header) {
                        if(mView != null){
                            mView.getAdImgSuccess(list);
                        }
                    }
                });
    }

    @Override
    public void openGate() {
        GateRequest requset=new GateRequest();
        String str = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),str);
        mApiService.getGate(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<String>(mContext, mView) {
                    @Override
                    public void onSuccess(String baseUrl, Header header) {

                        mView.openGateSuccess(baseUrl);
                    }
                });
    }

    @Override
    public void onDetach() {
        if (mView != null) {
            mView = null;
        }
    }

}
