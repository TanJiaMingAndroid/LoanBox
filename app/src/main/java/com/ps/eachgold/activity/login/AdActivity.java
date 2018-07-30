package com.ps.eachgold.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdActivity extends BaseActivity {

    @BindView(R.id.sp_bg)
    ImageView spBg;
    @BindView(R.id.sp_jump_btn)
    Button spJumpBtn;

    //图片地址
    private String imgUrl;
    //图片点击后 跳转的地址  预留
    private String clickUrl;
    //所有图片地址的基础域名（加在图片地址前）详情询问后台
    private String baseUrl;
    //由于CountDownTimer有一定的延迟，所以这里设置3400
    private CountDownTimer countDownTimer;
    //跳转
    public static void createActivity(Context context, String imgUrl, String clickUrl,String baseUrl) {
        Intent intent = new Intent(context, AdActivity.class);
        intent.putExtra("imgUrl", imgUrl);
        intent.putExtra("clickUrl", clickUrl);
        intent.putExtra("baseUrl", baseUrl);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            imgUrl = intent.getStringExtra("imgUrl");
            clickUrl = intent.getStringExtra("clickUrl");
            baseUrl = intent.getStringExtra("baseUrl");
        }
        countDownTimer = new CountDownTimer(3400, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                spJumpBtn.setText("跳过(" + millisUntilFinished / 1000 + "s)");
            }

            @Override
            public void onFinish() {
                spJumpBtn.setText("跳过(" + 0 + "s)");
                gotoNext();
            }
        };
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_ad;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if(imgUrl!=null){
            Glide.with(this)
                    .load("http:" + baseUrl +imgUrl)
                    .into(spBg);
        }else {

        }
        spJumpBtn.setVisibility(View.VISIBLE);
        countDownTimer.start();
    }

    private void gotoNext() {
        countDownTimer.cancel();
        MainActivity.createActivity(this, 0);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public boolean isUseButterKnife() {
        return true;
    }

    @Override
    protected boolean isAllowFullScreen() {
        return false;
    }
    
    @OnClick({R.id.sp_bg, R.id.sp_jump_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sp_bg:
                gotoWebActivity();
                break;
            case R.id.sp_jump_btn:
                gotoNext();
                break;
        }
    }

    private void gotoWebActivity() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null)
            countDownTimer.cancel();
    }
}
