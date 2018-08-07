package com.ps.eachgold.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoginBean;
import com.ps.eachgold.contract.login.BindTelContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindTelActivity extends BaseActivity implements BindTelContract.View {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.iv_bind_tel_submit)
    ImageView ivBindTelSubmit;

    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, BindTelActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_bind_tel;
    }
    @OnClick({R.id.iv_bind_tel_submit,R.id.left_icon})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.left_icon:
                finish();
                break;
            case R.id.iv_bind_tel_submit:
                //跳转到验证码页面
                AutoCodeActivity.createActivity(this);
                break;
        }
    }
    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public boolean isUseButterKnife() {
        return false;
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
        return null;
    }

    @Override
    public void loginSuccess(LoginBean bean, Header header) {

    }
}
