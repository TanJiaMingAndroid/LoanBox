package com.ps.eachgold.activity.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;

import butterknife.ButterKnife;

public class AutoCodeActivity extends BaseActivity {

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
}
