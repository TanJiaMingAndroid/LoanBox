package com.ps.eachgold.gate;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.util.T;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewActivity extends BaseActivity implements NewAcContract.View {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.tv_c_title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.tv_source)
    TextView tvSource;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    WebView tvContent;
    @BindView(R.id.activity_qqq)
    LinearLayout activityQqq;

    private String id;

    private NewAcPresenter mPresenter;

    //跳转
    public static void createActivity(Context context, String id) {
        Intent intent = new Intent(context, NewActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        id = getIntent().getStringExtra("id");
    }

    @Override
    protected void initPresenter() {
        mPresenter = new NewAcPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_new;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPresenter.getData(id);
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
    public void getData(NewDetailBean bean, Header header) {
        title.setText(bean.getTitle());
        tvSource.setText("来源:" + bean.getSource());
        tvTime.setText("时间:" + bean.getTime());
       // tvContent.setText(Html.fromHtml(bean.getContent()));
        tvContent.loadDataWithBaseURL(null,bean.getContent() , "text/html", "utf-8", null);

    }

    @OnClick(R.id.left_icon)
    public void onViewClicked() {
        finish();
    }
}
