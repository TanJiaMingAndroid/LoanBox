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
import com.ps.eachgold.contract.person.FeedBackContract;
import com.ps.eachgold.presenter.FeedBackPresenter;
import com.ps.eachgold.util.T;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 8146 on 2017/1/12.
 * 意见反馈-页面
 */
public class FeedBackActivity extends BaseActivity implements FeedBackContract.View {


    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    //P层
    private FeedBackPresenter mPresenter;
    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, FeedBackActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initPresenter() {
        mPresenter = new FeedBackPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        title.setText("意见反馈");
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
        return etText.getText().toString().trim();
    }


    @Override
    public void commitSuccess(String result) {
        T.showShort("提交成功");
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
                //提交
                mPresenter.commitText();
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
