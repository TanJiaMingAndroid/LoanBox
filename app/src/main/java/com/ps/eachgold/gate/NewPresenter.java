package com.ps.eachgold.gate;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.Page;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 8146 on 2018/2/9.
 */

public class NewPresenter implements NewContract.Presenter {

    private Context mContext;
    private NewContract.View mView;

    private ApiService mApiService;

    public NewPresenter(Context context, NewContract.View view) {
        this.mContext = context;
        this.mView = view;
        mApiService = NetClient.getInstance().net().create(ApiService.class);
    }


    @Override
    public void getList(String category,int page, int size) {


        LaberRequest requset=new LaberRequest();
        requset.setCategory(category);
        Page mPage=new Page();
        mPage.setIndex(page);
        mPage.setSize(size);
        requset.getHeader().setPage(mPage);

        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),userStr);
        mApiService.getTypeList(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<List<NewBean>>(mContext, mView) {
                    @Override
                    public void onSuccess(List<NewBean> list , Header header) {
                        if(mView != null){
                            mView.getListSuccess(list,header);
                        }
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

