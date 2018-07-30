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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.bean.BaseBean;
import com.ps.eachgold.contract.creditReport.CreditReportContract;
import com.ps.eachgold.util.DpPxUtil;
import com.ps.eachgold.viewHold.CreditReportViewHolder;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CreditReportActivity extends BaseActivity implements CreditReportContract.View,View.OnClickListener{


    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycler_credit_report)
    EasyRecyclerView recyclerCreditReport;
    @BindView(R.id.swipe_credit_report)
    SwipeRefreshLayout swipeCreditReport;

    TextView tvCreditNum;
    LinearLayout linCredit;
    TextView tvHouseNum;
    LinearLayout linHouse;
    TextView tvOtherNum;
    LinearLayout linOther;
    TextView tvPublicNum;
    LinearLayout linPublic;
    TextView tvSerchNum;
    LinearLayout linSearch;

    //list适配器
    private RecyclerArrayAdapter<BaseBean> adapter;
    //list头部View
    private View viewHeader;


    //跳转
    public static void createActivity(Context context, boolean isTranslucent) {
        Intent intent = new Intent(context, CreditReportActivity.class);
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
        return R.layout.activity_credit_report;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        title.setText("信用报告");
        //初始化列表
        initRecycler();
        //添加头部
        addHead();
        //添加监听
        addListener();
        //伪造数据
        adapter.clear();
        List<BaseBean> lists = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
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
        recyclerCreditReport.setLayoutManager(layoutManager);
        //间隔线的 颜色  宽度 左边距 右边距
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#ECEDF0"), DpPxUtil.dip2px(this, 0.5f), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerCreditReport.addItemDecoration(itemDecoration);
        recyclerCreditReport.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<BaseBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new CreditReportViewHolder(parent);
            }
        });
    }

    @Override
    public void addHead() {
        viewHeader = LayoutInflater.from(this).inflate(R.layout.head_activity_credit_report, null);

        tvCreditNum = viewHeader.findViewById(R.id.tv_credit_num); //信用记录
        linCredit = viewHeader.findViewById(R.id.lin_credit);
        tvHouseNum = viewHeader.findViewById(R.id.tv_house_num); //房贷
        linHouse = viewHeader.findViewById(R.id.lin_house);
        tvOtherNum = viewHeader.findViewById(R.id.tv_other_num); //其他
        linOther = viewHeader.findViewById(R.id.lin_other);
        tvPublicNum = viewHeader.findViewById(R.id.tv_public_num);  //公共
        linPublic = viewHeader.findViewById(R.id.lin_public);
        tvSerchNum = viewHeader.findViewById(R.id.tv_serch_num); //次数
        linSearch = viewHeader.findViewById(R.id.lin_search);
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return viewHeader;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
    }

    @Override
    public void addListener() {

        linCredit.setOnClickListener(this);
        linHouse.setOnClickListener(this);
        linOther.setOnClickListener(this);
        linPublic.setOnClickListener(this);
        linSearch.setOnClickListener(this);

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
             switch (position){
                 case 0:
                     //违约逾期
                        DefaultRecordActivity.createActivity(CreditReportActivity.this,true);
                     break;
                 case 1:
                     //信用类型
                     break;
                 case 2:
                     //履约能力
                     break;
                 case 3:
                     //信用历史
                     break;
                 case 4:
                     //查询频次
                     break;
             }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_icon://返回
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
                break;
            case R.id.lin_credit://信用记录
                ExpandReportActivity.createActivity(CreditReportActivity.this,true,"信用卡记录");
                break;
            case R.id.lin_house://房贷
                ExpandReportActivity.createActivity(CreditReportActivity.this,true,"房贷记录");
                break;
            case R.id.lin_other://其他
                ExpandReportActivity.createActivity(CreditReportActivity.this,true,"其他贷款记录");
                break;
            case R.id.lin_public://公共
                ExpandReportActivity.createActivity(CreditReportActivity.this,true,"公共记录");
                break;
            case R.id.lin_search://次数
                ExpandReportActivity.createActivity(CreditReportActivity.this,true,"查询频次");
                break;
        }
    }
}
