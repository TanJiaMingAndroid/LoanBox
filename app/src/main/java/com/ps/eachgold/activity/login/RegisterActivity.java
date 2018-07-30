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
import com.ps.eachgold.activity.H5Activity;
import com.ps.eachgold.activity.MainActivity;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.RegisterBean;
import com.ps.eachgold.contract.login.RegisterContract;
import com.ps.eachgold.presenter.RegisterPresenter;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.util.T;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterContract.View {


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
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.et_psw_again)
    EditText etPswAgain;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;

    //P层
    private RegisterPresenter mPresenter;
    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initPresenter() {
        mPresenter = new RegisterPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        title.setText("注册");
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
    public String getPsw() {
        return etPsw.getText().toString().trim();
    }

    @Override
    public String getPswAgain() {
        return etPswAgain.getText().toString().trim();
    }

    @Override
    public TextView getSendCode() {
        return tvGetCode;
    }

    @Override
    public void registerSuccess(RegisterBean bean, Header header) {
        tvRegister.setClickable(true);
        //注册成功后 自动登录 本地保存 -登录状态 ；手机号； ID ； 是否有登录密码（验证码注册登录无设置密码情况情况）；完善信息情况
        SPutils.put(this, "login", true);
        SPutils.put(this, "phone", getPhone());
        SPutils.put(this, "sessionid", bean.getSessionid());
        String psw=bean.getUserinfo().getPasswordSign();
        if(psw!=null&&!"".equals(psw)){
            SPutils.put(this,"havePsw",1);
        }else {
            SPutils.put(this,"havePsw",0);
        }
        //登录后是否有认证
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


    @OnClick({R.id.left_icon, R.id.tv_get_code, R.id.tv_register, R.id.tv_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
                break;
            case R.id.tv_get_code:
                //获取验证码
                mPresenter.sendCode();
                break;
            case R.id.tv_register:
                //注册操作
                mPresenter.register();
                break;
            case R.id.tv_agreement:
                //协议
                H5Activity.createActivity(this,"file:///android_asset/xieyi.html","散金侠注册服务协议");
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
