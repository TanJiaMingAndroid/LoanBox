package com.ps.eachgold.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.activity.MainActivity;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoginBean;
import com.ps.eachgold.contract.login.CodeLoginContract;
import com.ps.eachgold.presenter.CodeLoginPresenter;
import com.ps.eachgold.util.SPutils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 8146 on 2017/1/12.
 * 验证码登录-页面
 */
public class CodeLoginActivity extends BaseActivity implements CodeLoginContract.View {


    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_psw_login)
    TextView tvPswLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    //电话号
    private String phone;
    //p层
    private CodeLoginPresenter mPresenter;
    //最后点击的时间（防重复点击）
    private long lastClickTime = 0;

    //跳转
    public static void createActivity(Context context, String phone) {
        Intent intent = new Intent(context, CodeLoginActivity.class);
        intent.putExtra("phoneNumber", phone);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            phone = getIntent().getStringExtra("phoneNumber");
        }

    }

    @Override
    protected void initPresenter() {
        mPresenter = new CodeLoginPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_code_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        title.setText("验证码登录");
        etPhone.setText(phone);
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
    public String getCode() {
        return etCode.getText().toString().trim();
    }

    @Override
    public TextView getLoginTv() {
        return tvLogin;
    }

    @Override
    public TextView getSendCode() {
        return tvGetCode;
    }

    @Override
    public void loginSuccess(LoginBean bean, Header header) {
        //本地保存 -登录状态 ；手机号； ID ； 是否有登录密码（验证码注册登录无设置密码情况情况）；完善信息情况
        SPutils.put(this, "login", true);
        SPutils.put(this, "phone", getPhone());
        SPutils.put(this, "sessionid", bean.getSessionid());
        String psw=bean.getUserinfo().getPasswordSign();
        if(psw!=null&&!"".equals(psw)){
            SPutils.put(this,"havePsw",1);
        }else {
            SPutils.put(this,"havePsw",0);
        }
        //是否有认证 完善信息
        if(bean.getUserinfo().getIsAuth()==-1){
            //未认证
            InfoStepOneActivity.createActivity(this);
            finish();
        }else {
            //已填写
            SPutils.put(this, "info", true);
            MainActivity.createActivity(this, 0);
        }
    }


    @OnClick({R.id.left_icon, R.id.tv_get_code, R.id.tv_login, R.id.tv_psw_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                //返回
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
                break;
            case R.id.tv_get_code:
                //获取验证码
//
//                long currentTime = Calendar.getInstance().getTimeInMillis();
//                if (currentTime - lastClickTime > MyAppConfig.MIN_CLICK_DELAY_TIME) {
//                    lastClickTime = currentTime;
//                    //防重复点击，验证是否已注册
//                    mPresenter.checkIsRegister(phone);
//                }
                mPresenter.sendCode();
                break;
            case R.id.tv_login:
                //登录
                mPresenter.login();
                break;
            case R.id.tv_psw_login:
                //跳转密码登录
                LoginActivity.createActivity(this, getPhone());
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
}
