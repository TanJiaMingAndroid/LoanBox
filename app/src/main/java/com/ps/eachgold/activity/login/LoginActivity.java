package com.ps.eachgold.activity.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.activity.MainActivity;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoginBean;
import com.ps.eachgold.contract.login.LoginContract;
import com.ps.eachgold.presenter.LoginPresenter;
import com.ps.eachgold.util.SPutils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by 8146 on 2017/1/12.
 * 登录-页面
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_code_login)
    TextView tvCodeLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_login_agreement)
    TextView tvLoginAgreement;


    private LoginPresenter mPresenter;


    private String phone;

    //跳转
    public static void createActivity(Context context, String phone) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("phoneNumber", phone);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            phone = intent.getStringExtra("phoneNumber");
        }
    }

    @Override
    protected void initPresenter() {
        mPresenter = new LoginPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        leftIcon.setVisibility(View.INVISIBLE);
        title.setText(R.string.login_title);
        SpannableString spannableString = new SpannableString("Masuk untuk setuju  《***** Perjanjian Layanan》");
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF292929")), 20, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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
        tvLogin.setClickable(true);
    }

    @Override
    public void showMyProgressDialog(String message) {

    }

    @Override
    public void hiddenProgressDialog() {

    }

    @Override
    public String getPhone() {
        return etPhone.getText().toString().trim();
    }

    @Override
    public String getPsw() {
        return etPsw.getText().toString().trim();
    }

    @Override
    public void loginSuccess(LoginBean bean, Header header) {
        //本地保存 -登录状态 ；手机号； ID ； 是否有登录密码（验证码注册登录无设置密码情况情况）；完善信息情况
        SPutils.put(this, "login", true);
        SPutils.put(this, "phone", getPhone());
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

    //点击事件
    @OnClick({R.id.left_icon, R.id.tv_forget, R.id.tv_login, R.id.tv_code_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                //返回
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
                break;
            case R.id.tv_forget:
                //忘记密码
                ForgotPswActivity.createActivity(this);
                break;
            case R.id.tv_login:
                //登录
                mPresenter.login();
                break;
            case R.id.tv_code_login:
                //验证码登录
                CodeLoginActivity.createActivity(this, getPhone());
                finish();
                break;
            case R.id.tv_register:
                //注册
                RegisterActivity.createActivity(this);
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
