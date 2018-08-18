package com.ps.loanbox.activity.person;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.loanbox.R;
import com.ps.loanbox.activity.BaseActivity;
import com.ps.loanbox.activity.login.LoginActivity;
import com.ps.loanbox.util.ActivityCollector;
import com.ps.loanbox.util.SPutils;
import com.ps.loanbox.util.VerisonUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingActivity extends BaseActivity {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView rightIcon;

    @BindView(R.id.tv_exit)
    TextView tvExit;
    @BindView(R.id.tv_clien_verson)
    TextView tvClienVerson;


    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //标题
        title.setText("Pengaturan");
        tvClienVerson.setText("v"+VerisonUtil.getClientVersion());

    }


    @Override
    public boolean isUseButterKnife() {
        return true;
    }

    @Override
    protected boolean isAllowFullScreen() {
        return false;
    }

    @OnClick({R.id.left_icon, R.id.tv_change_psw, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                //返回
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
                break;
            case R.id.tv_exit:
                SPutils.clear(this);
                ActivityCollector.getInstance().finish();
                LoginActivity.createActivity(this);
                break;
        }
    }


}
