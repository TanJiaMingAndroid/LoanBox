package com.ps.eachgold.activity.person;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.activity.login.LoginActivity;
import com.ps.eachgold.bean.RxMsgBean;
import com.ps.eachgold.fragment.CustomFragment;
import com.ps.eachgold.util.RxBus;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.viewHold.CustomVpAdapter;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MoreCustomActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rb_loan)
    RadioButton rbLoan;
    @BindView(R.id.rb_credit)
    RadioButton rbCredit;
    @BindView(R.id.rg_custom)
    RadioGroup rgCustom;
    @BindView(R.id.vp_custom)
    ViewPager vpCustom;

    //Fragment 列表
    private List<Fragment> mFragments;
    //ViewPager 适配器
    private CustomVpAdapter mAdapter;
    //我的专属Tab 标题
    private String laber1, laber2;


    /*public static void createActivity(Context context, String laber1, String laber2) {
        Intent intent = new Intent(context, MoreCustomActivity.class);
        intent.putExtra("laber1", laber1);
        intent.putExtra("laber2", laber2);
        context.startActivity(intent);
    }*/
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, MoreCustomActivity.class);
        /*intent.putExtra("laber1", laber1);
        intent.putExtra("laber2", laber2);*/
        context.startActivity(intent);
    }
    @Override
    protected void initVariables() {
        Intent intent = getIntent();
        laber1 = intent.getStringExtra("laber1");
        laber2 = intent.getStringExtra("laber2");
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_more_custom;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
       // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        rbLoan.setText(laber1);
        rbCredit.setText(laber2);
        //初始化
        mFragments = new ArrayList<>();
        mFragments.add(CustomFragment.getInstance(laber1));
        mFragments.add(CustomFragment.getInstance(laber2));
        vpCustom.setOffscreenPageLimit(3);
        mAdapter = new CustomVpAdapter(getSupportFragmentManager(), mFragments);
        vpCustom.setAdapter(mAdapter);
        // 监听
        rgCustom.setOnCheckedChangeListener(this);
        vpCustom.addOnPageChangeListener(this);
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_loan:
                vpCustom.setCurrentItem(0);
                break;
            case R.id.rb_credit:
                vpCustom.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                rgCustom.check(R.id.rb_loan);
                break;
            case 1:
                rgCustom.check(R.id.rb_credit);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.left_icon)
    public void onViewClicked() {
        finish();
        overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
    }

}
