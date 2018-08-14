package com.ps.eachgold.activity.person;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectionActivity extends BaseActivity {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.recycler_message)
    EasyRecyclerView recyclerMessage;
    @BindView(R.id.swipe_message)
    SwipeRefreshLayout swipeMessage;
    @BindView(R.id.lin_no_data)
    LinearLayout linNoData;


    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, CollectionActivity.class);
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
        return R.layout.activity_collection;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        title.setText("我的收藏");
        swipeMessage.setVisibility(View.GONE);
        linNoData.setVisibility(View.VISIBLE);

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
