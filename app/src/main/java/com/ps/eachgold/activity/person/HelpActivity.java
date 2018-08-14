package com.ps.eachgold.activity.person;/**
 * Created by 8657 on 2018/8/1.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.contract.person.HelpContract;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * creat by tanjiaming at 2018/8/1
 */
public class HelpActivity extends BaseActivity implements HelpContract.View {


    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;

    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, HelpActivity.class);
        context.startActivity(intent);
    }
    @OnClick(R.id.left_icon)
    public void onViewClicked() {
        finish();
        overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
    }


    @Override
    protected void initVariables() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_person_help;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        title.setText("帮助中心");

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
    public String getText() {
        return null;
    }

    @Override
    public void commitSuccess(String result) {

    }


}
