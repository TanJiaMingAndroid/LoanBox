package com.ps.eachgold.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jyn.vcview.VerificationCodeView;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.activity.MainActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AutoCodeActivity extends BaseActivity {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.verificationcodeview)
    VerificationCodeView verificationcodeview;
    @BindView(R.id.bt_login_code_go)
    Button btLoginCodeGo;

    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, AutoCodeActivity.class);
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
        return R.layout.activity_auto_code;
    }
    @OnClick(R.id.bt_login_code_go)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.bt_login_code_go:
                //跳转到验证码页面
                MainActivity.createActivity(this,0);
                break;
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏

    }

    @Override
    public boolean isUseButterKnife() {
        return false;
    }

    @Override
    protected boolean isAllowFullScreen() {
        return false;
    }
}
