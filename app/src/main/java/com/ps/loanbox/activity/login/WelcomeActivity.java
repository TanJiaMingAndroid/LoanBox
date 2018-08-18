package com.ps.loanbox.activity.login;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ps.loanbox.R;
import com.ps.loanbox.activity.BaseActivity;
import com.ps.loanbox.activity.MainActivity;
import com.ps.loanbox.bean.BannerBean;
import com.ps.loanbox.bean.WelcomeBean;
import com.ps.loanbox.contract.login.WelcomeContract;
import com.ps.loanbox.gate.Main2Activity;
import com.ps.loanbox.presenter.WelcomePresenter;
import com.ps.loanbox.util.SPutils;
import com.yanzhenjie.permission.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 8146 on 2017/1/12.
 * 启动页-页面
 * <p>
 * <p>
 * 因上架问题。
 */
public class WelcomeActivity extends BaseActivity {


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    finishWelcom();
                    break;
            }
        }
    };


    private void finishWelcom() {
        MainActivity.createActivity(this, 0);
        finish();
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        handler.sendEmptyMessageDelayed(0, 3000);

    }

    @Override
    public boolean isUseButterKnife() {
        return true;
    }

    @Override
    protected boolean isAllowFullScreen() {
        return true;
    }


}
