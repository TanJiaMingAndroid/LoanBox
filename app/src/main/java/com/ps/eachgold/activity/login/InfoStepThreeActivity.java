package com.ps.eachgold.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.activity.MainActivity;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.UserInfoBean;
import com.ps.eachgold.contract.loan.InfoStepThreeContract;
import com.ps.eachgold.presenter.InfoStepThreePresenter;
import com.ps.eachgold.util.SPutils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class InfoStepThreeActivity extends BaseActivity implements InfoStepThreeContract.View {


    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.iv_gif)
    ImageView ivGif;
    //P层
    private InfoStepThreePresenter mPresenter;
    //数据实体类
    private UserInfoBean bean;
    //跳转
    public static void createActivity(Context context, UserInfoBean bean) {
        Intent intent = new Intent(context, InfoStepThreeActivity.class);
        intent.putExtra("userInfo", bean);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        bean = (UserInfoBean) getIntent().getSerializableExtra("userInfo");
    }

    @Override
    protected void initPresenter() {
        mPresenter = new InfoStepThreePresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_info_step_three;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        title.setText("资料填写");
        //默认加载GIF
        RequestOptions options = new RequestOptions()
                .centerCrop()
                //.placeholder(R.mipmap.ic_launcher_round)
                .error(android.R.drawable.stat_notify_error)
                .priority(Priority.HIGH)
                //.skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        Glide.with(this)
                .load(R.mipmap.loading)
                .apply(options)
                .into(ivGif);

        mPresenter.saveInfo(bean);
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
                finish();
    }

    @Override
    public void showMyProgressDialog(String message) {

    }

    @Override
    public void hiddenProgressDialog() {

    }

    @OnClick({R.id.left_icon, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                //返回
                break;
            case R.id.tv_next:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
        if (countDownTimer != null)
            countDownTimer.cancel();
    }

    @Override
    public void saveInfoSuccess(String result, Header header) {
        SPutils.put(this, "info", true);
        countDownTimer.start();
    }

    private CountDownTimer countDownTimer = new CountDownTimer(3400, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            MainActivity.createActivity(InfoStepThreeActivity.this, 3);
        }
    };


}
