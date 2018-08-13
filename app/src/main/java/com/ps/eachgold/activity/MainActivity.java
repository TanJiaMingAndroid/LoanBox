package com.ps.eachgold.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ps.eachgold.R;
import com.ps.eachgold.contract.main.MainContract;
import com.ps.eachgold.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 8146 on 2017/1/12.
 * 主页-页面
 */
public class  MainActivity extends BaseActivity implements MainContract.View, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.mainFragmentLayout)
    FrameLayout mainFragmentLayout;
    @BindView(R.id.rb_loan)
    RadioButton rbLoan;
    @BindView(R.id.rb_credit)
    RadioButton rbCredit;
    @BindView(R.id.rb_person)
    RadioButton rbPerson;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    private MainPresenter mPresenter;
    //当前页面位置
    private int pagePos;
    //再按一次退出 功能记录时间
    long lastTime;


    //跳转
    public static void createActivity(Context context, int pagePos) {
        Intent intent = new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);;
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
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //主页显示位置 0 贷款 1 信用卡 2代还  3我的
        pagePos=getIntent().getIntExtra("pagePos",0);
        //默认显示  贷款
        mPresenter.showCurrenterFragment("FRAGMENT_LOAN");


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
            /*case 2:
                rbInstead.setChecked(true);
                break;*/
            case 3:
                rbPerson.setChecked(true);
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
                mPresenter.showCurrenterFragment("FRAGMENT_LOAN");
                //transaction.replace(R.id.mainFragmentLayout,indexFragment);
                //transaction.commit();
                break;
            case R.id.rb_credit:
                pagePos = 1;
//                transaction.remove(indexFragment);
//                transaction.commit();
                mPresenter.showCurrenterFragment("FRAGMENT_CREDIT");
                break;
            /*case R.id.rb_instead:
                pagePos = 2;
                mPresenter.showCurrenterFragment("FRAGMENT_INSTEAD");
                break;*/
            case R.id.rb_person:
                pagePos = 3;
//                transaction.remove(indexFragment);
//                transaction.commit();
                mPresenter.showCurrenterFragment("FRAGMENT_PERSON");
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
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                lastTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
