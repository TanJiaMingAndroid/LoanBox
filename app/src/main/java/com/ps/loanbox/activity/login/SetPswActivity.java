package com.ps.loanbox.activity.login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.loanbox.R;
import com.ps.loanbox.activity.BaseActivity;
import com.ps.loanbox.contract.login.SetPswContract;
import com.ps.loanbox.presenter.SetPswPresenter;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class SetPswActivity extends BaseActivity implements SetPswContract.View {
    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.et_psw_again)
    EditText etPswAgain;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    //p层
    private SetPswPresenter mPresenter;
    //电话号码
    private String phone;
    //跳转
    public static void createActivity(Context context,String phone) {
        Intent intent = new Intent(context, SetPswActivity.class);
        intent.putExtra("phoneNumber", phone);
        context.startActivity(intent);
    }


    @Override
    protected void initVariables() {
        phone = getIntent().getStringExtra("phoneNumber");
    }

    @Override
    protected void initPresenter() {
        mPresenter = new SetPswPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_set_psw;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        title.setText("设置密码");
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
    //------------- 获得 密码--再次的密码
    @Override
    public String getPsw() {
        return etPsw.getText().toString().trim();
    }

    @Override
    public String getPswAgain() {
        return etPswAgain.getText().toString().trim();
    }


    @Override
    public void sureSuccess() {
        //回到登录
        //清除 其他页面
        LoginActivity.createActivity(this);
        finish();
        ForgotPswActivity.instance.finish();
    }

    @OnClick({R.id.left_icon, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                //返回
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
                break;
            case R.id.tv_sure:
                //确认修改
                mPresenter.sure(phone);
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
