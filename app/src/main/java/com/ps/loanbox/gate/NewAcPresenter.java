package com.ps.loanbox.gate;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ps.loanbox.bean.Header;
import com.ps.loanbox.net.ApiService;
import com.ps.loanbox.net.MyObserver3;
import com.ps.loanbox.net.NetClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 8146 on 2018/2/9.
 */

public class NewAcPresenter implements NewAcContract.Presenter {

    private Context mContext;
    private NewAcContract.View mView;

    private ApiService mApiService;

    public NewAcPresenter(Context context, NewAcContract.View view) {
        this.mContext = context;
        this.mView = view;
        mApiService = NetClient.getInstance().net().create(ApiService.class);
    }


    @Override
    public void onDetach() {
        if (mView != null) {
            mView = null;
        }
    }

    @Override
    public void getData(String id) {

        DetailRequest requset=new DetailRequest();
        requset.setId(id);
        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),userStr);
        mApiService.getDetail(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<NewDetailBean>(mContext, mView) {
                    @Override
                    public void onSuccess(NewDetailBean bean , Header header) {
                        if(mView != null){
                          mView.getData(bean,header);
                        }
                    }
                });
    }
}

