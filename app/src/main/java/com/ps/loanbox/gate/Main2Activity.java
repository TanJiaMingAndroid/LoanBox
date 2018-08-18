package com.ps.loanbox.gate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ps.loanbox.R;
import com.ps.loanbox.activity.BaseActivity;
import com.ps.loanbox.contract.main.MainContract;
import com.ps.loanbox.presenter.MainPresenter;

import butterknife.BindView;

public class Main2Activity extends BaseActivity implements MainContract.View, RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.rb_loan)
    RadioButton rbLoan;
    @BindView(R.id.rb_credit)
    RadioButton rbCredit;
    @BindView(R.id.rb_instead)
    RadioButton rbInstead;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    @BindView(R.id.mainFragmentLayout)
    FrameLayout mainFragmentLayout;
    @BindView(R.id.line)
    View line;
    private MainPresenter mPresenter;
    //当前页面位置
    private int pagePos;
    //再按一次退出 功能记录时间
    long lastTime;
    //跳转
    public static void createActivity(Context context, int pagePos) {
        Intent intent = new Intent(context, Main2Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);;
        intent.putExtra("pagePos", pagePos);
        context.startActivity(intent);
    }
    @Override
    protected void initVariables() {

    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //主页显示位置 0 贷款 1 信用卡 2代还  3我的
        pagePos=getIntent().getIntExtra("pagePos",0);
        //默认显示  贷款
        mPresenter.showCurrenterFragment("FRAGMENT_NEW1");
        //底部监听
        rgMain.setOnCheckedChangeListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        switch (pagePos) {
            case 0:
                rbLoan.setChecked(true);
                break;
            case 1:
                rbCredit.setChecked(true);
                break;
            case 2:
                rbInstead.setChecked(true);
                break;

        }
    }

    @Override
    public FragmentManager getMySupportManager() {
        return getSupportFragmentManager();
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_loan:
                pagePos = 0;
                mPresenter.showCurrenterFragment("FRAGMENT_NEW1");
                break;
            case R.id.rb_credit:
                pagePos = 1;
                mPresenter.showCurrenterFragment("FRAGMENT_NEW2");
                break;
            case R.id.rb_instead:
                pagePos = 2;
                mPresenter.showCurrenterFragment("FRAGMENT_NEW3");
                break;
        }
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

    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }
    @Override

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - lastTime > 2000) {
                Toast.makeText(Main2Activity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                lastTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
        return true;
    }
}
