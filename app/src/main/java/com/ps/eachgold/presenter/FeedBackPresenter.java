package com.ps.eachgold.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.contract.person.FeedBackContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.NetClient;
import com.ps.eachgold.net.headerRequset.FeedBackRequest;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.util.StringUtils;
import com.ps.eachgold.util.T;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 8146 on 2018/1/16.
 */

public class FeedBackPresenter implements FeedBackContract.Presenter {
    private Context mContext;
    private FeedBackContract.View mView;
    private ApiService mApiService;

    public FeedBackPresenter(Context context, FeedBackContract.View view) {
        this.mContext = context;
        this.mView = view;
        mApiService = NetClient.getInstance().net().create(ApiService.class);
    }

    @Override
    public void commitText() {
        String text = mView.getText();
        if (StringUtils.isEmpty(text)) {
            T.showShort("请勿提交空信息!");
            return;
        }

        FeedBackRequest requset=new FeedBackRequest();
        requset.setContent(text);
        requset.setPictureUrl(null);
        requset.setUserId(null);
        String id = (String) SPutils.get(mContext, "sessionid", "");
        requset.getHeader().setSessionid(id);
        String str = JSON.toJSONString(requset);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),str);

                mApiService.saveFeedback(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver3<String>(mContext, mView) {
                    @Override
                    public void onSuccess(String result, Header header) {
                        mView.commitSuccess(result);
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
