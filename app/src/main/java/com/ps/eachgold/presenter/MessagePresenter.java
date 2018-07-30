package com.ps.eachgold.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.MessageBean;
import com.ps.eachgold.bean.Page;
import com.ps.eachgold.contract.person.MessageContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;
import com.ps.eachgold.net.headerRequset.MessageRequest;
import com.ps.eachgold.util.SPutils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 8146 on 2018/2/1.
 */

public class MessagePresenter implements MessageContract.Presenter {


    private Context mContext;
    private MessageContract.View mView;



    private ApiService mApiService;

    public MessagePresenter(Context context, MessageContract.View view) {
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
    public void getList(int page,int size) {

        String phone= (String) SPutils.get(mContext,"phone","");
        MessageRequest requset=new MessageRequest();
        requset.setMoblie(phone);
        Page mPage=new Page();
        mPage.setIndex(page);
        mPage.setSize(size);
        requset.getHeader().setPage(mPage);

        String id = (String) SPutils.get(mContext, "sessionid", "");
        requset.getHeader().setSessionid(id);
        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),userStr);
        mApiService.getMsg(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<List<MessageBean>>(mContext, mView) {
                    @Override
                    public void onSuccess(List<MessageBean> list, Header header) {
                        if(mView != null){
                            mView.getListSuccess(list,header);
                        }
                    }
                });
    }
}

