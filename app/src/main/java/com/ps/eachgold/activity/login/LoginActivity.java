package com.ps.eachgold.activity.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.activity.MainActivity;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoginBean;
import com.ps.eachgold.contract.login.LoginContract;
import com.ps.eachgold.net.ApiService;
import com.ps.eachgold.net.MyObserver3;
import com.ps.eachgold.net.headerRequset.LoginRequest;
import com.ps.eachgold.net.headerRequset.TelLoginRequest;
import com.ps.eachgold.presenter.LoginPresenter;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.util.T;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * Created by 8146 on 2017/1/12.
 * 登录-页面
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {
    private Context mContext;

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_login_agreement)
    TextView tvLoginAgreement;
    @BindView(R.id.bt_login_tel)
    Button btLoginTel;
    @BindView(R.id.et_login_tel)
    EditText etLoginTel;
    @BindView(R.id.bt_facebook_login)
    LoginButton btFacebookLogin;


    private LoginPresenter mPresenter;
    private ApiService mApiService;


    private String phone;
    private CallbackManager callbackManager;
    //private LoginButton btFacebookLogin;
    private LoginContract.View mView;

    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        //intent.putExtra("phoneNumber", phone);
        context.startActivity(intent);


    }

    @Override
    protected void initVariables() {
        Intent intent = getIntent();

    }

    @Override
    protected void initPresenter() {
        mPresenter = new LoginPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    private void facebookLogin() {
        callbackManager = CallbackManager.Factory.create();
        btFacebookLogin.setReadPermissions(Arrays.asList("public_profile", "email"));
        btFacebookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String facebookId = loginResult.getAccessToken().getUserId();
                String facebookUser = Profile.getCurrentProfile().getName();
                @SuppressLint("MissingPermission")
                String  imei = ((TelephonyManager) getApplicationContext().getSystemService(TELEPHONY_SERVICE)).getDeviceId();
                LoginRequest requset = new LoginRequest();
                requset.setImei(imei);
                requset.setTermType(android.os.Build.MODEL);
                requset.setAppName("LoanBox");
                requset.setAppPackage(getPackageName());
                requset.setFacebookId(Long.parseLong(facebookId));
                requset.setFacebookUser(facebookUser);
                requset.setRegChannel("facebook");
                String userStr = JSON.toJSONString(requset);
                Log.e("111",userStr);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), userStr);
                mApiService.login(requestBody)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new MyObserver3<LoginBean>(mContext, mView) {
                            @Override
                            public void onSuccess(LoginBean result, Header header) {
                                Log.e("111",result.getSessionid());
                                //mView.fbloginSuccess(result,header);
                                BindTelActivity.createActivity(mContext);
                            }
                        });
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        facebookLogin();
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        //leftIcon.setVisibility(View.INVISIBLE);
        title.setText(R.string.app_name);
        title.setTextColor(Color.parseColor("#FF000000"));
        SpannableString spannableString = new SpannableString("Masuk untuk setuju  《***** Perjanjian Layanan》");
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFFF7A3F")), 20, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLoginAgreement.setText(spannableString);

    }

    @Override
    public boolean isUseButterKnife() {
        return true;
    }

    @Override
    protected boolean isAllowFullScreen() {
        return false;
    }

    @Override
    public void getError(Throwable e) {
        //tvLogin.setClickable(true);
    }

    @Override
    public void showMyProgressDialog(String message) {

    }

    @Override
    public void hiddenProgressDialog() {

    }


    @Override
    public String getPhone() {
        return etLoginTel.getText().toString().trim();

    }

    @Override
    public void fbloginSuccess(LoginBean bean, Header header) {
        //本地保存 -登录状态 ；手机号； ID ； 是否有登录密码（验证码注册登录无设置密码情况情况）；完善信息情况
        SPutils.put(this, "login", true);
        SPutils.put(this, "sessionid", bean.getSessionid());
        String psw = bean.getUserinfo().getPasswordSign();
        if (psw != null && !"".equals(psw)) {
            SPutils.put(this, "havePsw", 1);
        } else {
            SPutils.put(this, "havePsw", 0);
        }
        //是否有认证 完善信息
        if (bean.getUserinfo().getIsAuth() == -1) {
            //未认证
            InfoStepOneActivity.createActivity(this);
            finish();
        } else {
            SPutils.put(this, "info", true);
            //已填写
            MainActivity.createActivity(this, 0);
        }
    }

    @Override
    public void telloginSuccess(Header header) {

    }


    //点击事件
    @OnClick({R.id.left_icon, R.id.tv_login_agreement, R.id.bt_login_tel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                //返回
                finish();
                //overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
                break;
            case R.id.bt_facebook_login:

                facebookLogin();
                break;
            case R.id.tv_login_agreement:
                //show agreement
                break;
            case R.id.bt_login_tel:
                if (!TextUtils.isEmpty(getPhone())) {
                    mPresenter.telLogin();
                    //跳转到验证码界面
                    AutoCodeActivity.createActivity(this);
                } else {
                    Toast.makeText(this, "手机号为空或者输入不正确", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
