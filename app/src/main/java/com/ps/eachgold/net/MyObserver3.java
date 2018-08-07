package com.ps.eachgold.net;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.CallSuper;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ps.eachgold.activity.login.LoginActivity;
import com.ps.eachgold.bean.BaseHeaderBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.RxMsgBean;
import com.ps.eachgold.contract.ImpBaseView;
import com.ps.eachgold.util.L;
import com.ps.eachgold.util.RxBus;
import com.ps.eachgold.util.SPutils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.adapter.rxjava2.HttpException;

/**
 * Created by 8146 on 2018/1/31.
 */

public abstract class MyObserver3<T> implements Observer<BaseHeaderBean<T>> {


    private final String TAG = MyObserver.class.getSimpleName();

    private Context mContext;
    //Mvp
    private ImpBaseView impBaseView;

    private static Gson gson = new Gson();

    //自定义的业务逻辑，成功返回积极数据
    private final int RESPONSE_CODE_OK = 200;
    //返回数据失败,严重的错误
    private final int RESPONSE_CODE_FAILED = -1;
    //错误处理-code
    private int errorCode;
    //错误处理-文字
    private String errorMsg = "未知的错误！";
    //错误处理-文字-一般网络异常
    public static final String errorMsg_SocketTimeoutException = "网络链接超时，请检查您的网络状态，稍后重试！";
    public static final String errorMsg_ConnectException = "网络链接异常，请检查您的网络状态";
    public static final String errorMsg_UnknownHostException = "网络异常，请检查您的网络状态";

    private Disposable mDisposable;


    /**
     * 根据具体的Api 业务逻辑去重写 onSuccess 方法！Error 是选择重写，but 必须Super ！
     *
     * @param t
     */
    public abstract void onSuccess(T t, Header header);

    /**
     * 不显示进度框
     */
    public MyObserver3() {

    }

    public MyObserver3(Context mContext, ImpBaseView impBaseView) {
        this.mContext = mContext;
        this.impBaseView = impBaseView;
    }

    /**
     * 需要显示进度框
     *
     * @param mContext
     */
    public MyObserver3(Context mContext, ImpBaseView impBaseView, String message) {
        this.mContext = mContext;
        this.impBaseView = impBaseView;
        if (message != null || !"".equals(message)) {
            impBaseView.showMyProgressDialog(message);
        }
    }

    @Override
    public final void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public final void onNext(BaseHeaderBean<T> response) {
        mDisposable.dispose();
        if (impBaseView != null) {
            impBaseView.hiddenProgressDialog();
        }

        if (response.getHeader().getCode() == 0) {
                    onSuccess(response.getData(), response.getHeader());
        } else {
            onFailure(response.getHeader().getCode(), response.getHeader().getMsg());
        }

    }

    @Override
    public final void onError(Throwable t) {
        mDisposable.dispose();
        L.e("error======", t.toString());
        if (impBaseView != null) {
            impBaseView.getError(t);
            impBaseView.hiddenProgressDialog();
        }
        if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            errorCode = httpException.code();
            errorMsg = httpException.getMessage();
            getErrorMsg(httpException);
        } else if (t instanceof SocketTimeoutException) {  //VPN open
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = errorMsg_SocketTimeoutException;
        } else if (t instanceof ConnectException) {
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = errorMsg_ConnectException;
        } else if (t instanceof UnknownHostException) {
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = errorMsg_UnknownHostException;
        }
        // .....其他的异常处理
        onFailure(errorCode, errorMsg);
    }

    /**
     * 简单的把Dialog 处理掉
     */
    @Override
    public final void onComplete() {
    }

    /**
     * Default error dispose!
     * 一般的就是 AlertDialog 或 SnackBar
     *
     * @param code
     * @param message
     */
    @CallSuper  //if overwrite,you should let it run.
    public void onFailure(int code, String message) {
        if (code == RESPONSE_CODE_FAILED && mContext != null) {
            //HttpUiTips.alertTip(mContext, message, code);
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            //Toast.makeText(mContext, message + "   code=" + code, Toast.LENGTH_SHORT).show();
        } else {
            disposeEorCode(message, code);
        }
    }

    /**
     * 对通用问题的统一拦截处理
     *
     * @param code
     */
    private final void disposeEorCode(String message, int code) {
        switch (code) {
            case 101:
            case 401:
                break;

        }
        boolean loginFlag = (boolean) SPutils.get(mContext, "login", false);
        if(loginFlag&&message.contains("用户未登录")){
            Toast.makeText(mContext, "登录已过期", Toast.LENGTH_SHORT).show();
            SPutils.deleteButPhone(mContext);
            Intent intent = new Intent();
            intent.setClass(mContext, LoginActivity.class);
            RxBus.getInstance().post(new RxMsgBean("update_login"));
            mContext.startActivity(intent);

        }else {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(mContext, message + "   code=" + code, Toast.LENGTH_SHORT).show();

    }


    /**
     * 获取详细的错误的信息 errorCode,errorMsg ,   这里和Api 结构有关，这里和Api 结构有关 ！
     * 以登录的时候的Grant_type 故意写错为例子,http 应该是直接的返回401=httpException.code()
     * 但是是怎么导致401的？我们的服务器会在respose.errorBody 中的content 中说明
     */
    private final void getErrorMsg(HttpException httpException) {
        String errorBodyStr = "";
//        try {
//            errorBodyStr = TextUtils.convertUnicode(httpException.response().errorBody().string());
//        } catch (IOException ioe) {
//            Log.e("errorBodyStr ioe:", ioe.toString());
//        }
//        try {
//            HttpResponse errorResponse = gson.fromJson(errorBodyStr, HttpResponse.class);
//            if (null != errorResponse) {
//                errorCode = errorResponse.getCode();
//                errorMsg = errorResponse.getError();
//            } else {
//                errorCode = RESPONSE_CODE_FAILED;
//                errorMsg = "ErrorResponse is null";
//            }
//        } catch (Exception jsonException) {
//            errorCode = RESPONSE_CODE_FAILED;
//            errorMsg = "http请求错误Json 信息异常";
//            jsonException.printStackTrace();
//        }
    }

}

