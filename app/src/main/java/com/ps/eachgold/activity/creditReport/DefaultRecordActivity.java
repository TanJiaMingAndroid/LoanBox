package com.ps.eachgold.activity.creditReport;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.bean.BaseBean;
import com.ps.eachgold.contract.creditReport.DefaultRecordContract;
import com.ps.eachgold.util.DpPxUtil;
import com.ps.eachgold.viewHold.DefaultRecordViewHolder;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DefaultRecordActivity extends BaseActivity implements DefaultRecordContract.View {


    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.recycler_default_record)
    EasyRecyclerView recyclerDefaultRecord;
    @BindView(R.id.swipe_default_record)
    SwipeRefreshLayout swipeDefaultRecord;


    TextView tvCivil;
    TextView tvMandatory;
    TextView administrative;
    TextView tvTax;
    TextView tvTelecom;
    //list尾部View
    private View viewFooter;

    private RecyclerArrayAdapter<BaseBean> adapter;

    //跳转
    public static void createActivity(Context context, boolean isTranslucent) {
        Intent intent = new Intent(context, DefaultRecordActivity.class);
        intent.putExtra("TranslucentType", isTranslucent);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_default_record;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        title.setText("违约记录");
        initRecycler();
        //伪造数据
        addFooter();
        adapter.clear();
        List<BaseBean> lists = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            BaseBean baseBean = new BaseBean();
            lists.add(baseBean);
        }
        adapter.addAll(lists);
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
    public void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerDefaultRecord.setLayoutManager(layoutManager);
        //间隔线的 颜色  宽度 左边距 右边距
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#F3F3F7"), DpPxUtil.dip2px(this, 7.0f), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerDefaultRecord.addItemDecoration(itemDecoration);
        recyclerDefaultRecord.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<BaseBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new DefaultRecordViewHolder(parent);
            }
        });
    }

    @Override
    public void addFooter() {
        viewFooter = LayoutInflater.from(this).inflate(R.layout.foot_activity_default_record, null);
        tvCivil = viewFooter.findViewById(R.id.tv_civil); //民事
        tvMandatory = viewFooter.findViewById(R.id.tv_mandatory);//强制
        administrative = viewFooter.findViewById(R.id.administrative); //行政
        tvTax = viewFooter.findViewById(R.id.tv_tax); //欠税
        tvTelecom = viewFooter.findViewById(R.id.tv_telecom); //电信
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return viewFooter;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
    }

    @Override
    public void addListener() {

    }


    @OnClick(R.id.left_icon)
    public void onViewClicked() {
        finish();
    }


}
