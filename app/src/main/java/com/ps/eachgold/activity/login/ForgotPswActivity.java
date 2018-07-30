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
import com.ps.eachgold.contract.login.ForgotPswContract;
import com.ps.eachgold.presenter.ForgotPswPresenter;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgotPswActivity extends BaseActivity implements ForgotPswContract.View {


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
    @BindView(R.id.tv_check)
    TextView tvCheck;

    //P层
    private ForgotPswPresenter mPresenter;

    public static ForgotPswActivity instance;

    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, ForgotPswActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ForgotPswPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_forgot_psw;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        instance = this;
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        title.setText("忘记密码");
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
    //几个get 方法方便P层  取得值
    @Override
    public String getPhone() {
        return etPhone.getText().toString().trim();
    }

    @Override
    public String getCode() {
        return etCode.getText().toString().trim();
    }

    @Override
    public TextView getSendCode() {
        return tvGetCode;
    }

    @Override
    public void checkSuccess() {

    }

    @OnClick({R.id.left_icon, R.id.tv_get_code, R.id.tv_check})
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
            case R.id.tv_check:
                //下一步
                mPresenter.check();
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
