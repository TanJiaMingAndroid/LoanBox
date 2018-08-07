package com.ps.eachgold.activity.person;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.bean.RxMsgBean;
import com.ps.eachgold.contract.person.ChangePswContract;
import com.ps.eachgold.presenter.ChangePswPresenter;
import com.ps.eachgold.util.RxBus;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.util.T;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.OnClick;


public class ChangePswActivity extends BaseActivity implements ChangePswContract.View {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.et_psw_again)
    EditText etPswAgain;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;


    private ChangePswPresenter mPresenter;
    //类型判断  1 有密码-密码修改 0  无密码  密码设置
    private int mType;

    public static void createActivity(Context context, int type) {
        Intent intent = new Intent(context, ChangePswActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        Intent intent = getIntent();
        mType = intent.getIntExtra("type", 1);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ChangePswPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_change_psw;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        if (mType == 1) {
            title.setText("修改密码");
            tv1.setText("原密码:");
            tv2.setText("新密码:");
        } else if (mType == 0) {
            title.setText("设置密码");
            tv1.setText("密码设置:");
            tv2.setText("确认密码:");
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

    @Override
    public String getPsw() {
        return etPsw.getText().toString().trim();
    }

    @Override
    public String getPswAgain() {
        return etPswAgain.getText().toString().trim();
    }

    @Override
    public void changeSuccess() {
        T.showShort("修改成功");
        //保存状态
        SPutils.put(this, "havePsw", 1);
        //事件总线-传递给Setting 刷新 显示
        RxBus.getInstance().post(new RxMsgBean("update_psw"));
        finish();
    }

    @Override
    public void setPswSuccess() {
        T.showShort("设置成功");
        //保存状态
        SPutils.put(this, "havePsw", 1);
        //事件总线-传递给Setting 刷新 显示
        RxBus.getInstance().post(new RxMsgBean("update_psw"));
        finish();
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
                //确定
                if (mType == 1) {
                    mPresenter.changePsw();
                } else if (mType == 0) {
                    mPresenter.setPsw();
                }
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
