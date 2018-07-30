package com.ps.eachgold.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LaberBean;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.bean.Page;
import com.ps.eachgold.contract.person.PersonContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;
import com.ps.eachgold.net.headerRequset.MoreCustomRequest;
import com.ps.eachgold.net.headerRequset.MsgCountRequest;
import com.ps.eachgold.net.headerRequset.MyExclusiveRequest;
import com.ps.eachgold.util.SPutils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 8146 on 2018/2/1.
 */

public class PersonPresenter implements PersonContract.Presenter{
    private Context mContext;
    private PersonContract.View mView;

    private ApiService mApiService;

    public PersonPresenter(Context context, PersonContract.View view) {
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
    public void getMyExclusive() {
        //获取 3个 专属产品
        String phone= (String) SPutils.get(mContext,"phone","");
        MyExclusiveRequest requset=new MyExclusiveRequest();
        requset.setMoblie(phone);
        Page mPage=new Page();
        mPage.setIndex(1);
        mPage.setSize(3);
        requset.getHeader().setPage(mPage);

        String id = (String) SPutils.get(mContext, "sessionid", "");
        requset.getHeader().setSessionid(id);

        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),userStr);

        mApiService.getExclusive(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<List<LoanBean>>(mContext, mView) {
                    @Override
                    public void onSuccess(List<LoanBean> list, Header header) {
                        if(mView != null){
                          mView.getMyExclusiveSuccess(list,header);
                        }
                    }
                });
    }

    @Override
    public void getLabel() {
        //获取查看更多的 -标签
        String phone = (String) SPutils.get(mContext, "phone", "");
        MoreCustomRequest requset = new MoreCustomRequest();
        requset.setMoblie(phone);


        String id = (String) SPutils.get(mContext, "sessionid", "");
        requset.getHeader().setSessionid(id);

        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), userStr);

        mApiService.getCustomLaber(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<List<LaberBean>>(mContext, mView) {
                    @Override
                    public void onSuccess(List<LaberBean> list, Header header) {
                        if (mView != null) {
                            mView.getLabelSuccess(list);
                        }
                    }
                });
    }

    @Override
    public void getMsgCount() {
        //消息数量 -
        String phone = (String) SPutils.get(mContext, "phone", "");
        MsgCountRequest requset = new MsgCountRequest();
        requset.setMoblie(phone);

        String id = (String) SPutils.get(mContext, "sessionid", "");
        requset.getHeader().setSessionid(id);
        String userStr = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), userStr);

        mApiService.getMsgCount(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<String>(mContext, mView) {
                    @Override
                    public void onSuccess(String count, Header header) {
                        if (mView != null) {
                            mView.getMsg(count);
                        }
                    }
                });
    }
}
