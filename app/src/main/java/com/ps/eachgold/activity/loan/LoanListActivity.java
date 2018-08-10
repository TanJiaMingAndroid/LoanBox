package com.ps.eachgold.activity.loan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.activity.H5Activity;
import com.ps.eachgold.activity.login.InfoStepOneActivity;
import com.ps.eachgold.activity.login.LoginActivity;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.contract.loan.LoanListContract;
import com.ps.eachgold.presenter.LoanListPresenter;
import com.ps.eachgold.util.DpPxUtil;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.viewHold.LoanListViewHolder;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 8146 on 2017/1/12.
 * 贷款列表-核心需求-页面
 * From- 贷款LoanFragment
 */
public class LoanListActivity extends BaseActivity implements LoanListContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnMoreListener {


    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycle_loan)
    EasyRecyclerView recycleLoan;
    @BindView(R.id.swipe_loan)
    SwipeRefreshLayout swipeLoan;

    //list适配器
    private RecyclerArrayAdapter<LoanBean> adapter;
    //类型（Intent 传参）  额度高 利息低  放款快
    private String type;
    //类型 high low fast
    private String loanType;
    //登录状态
    private Boolean loginFlag;
    //资料填写状态
    private Boolean infoFlag;
    //p层
    private LoanListPresenter mPresenter;
    //页面
    private int page = 1;
    //单页数量
    private int size = 10;
    //跳转
    public static void createActivity(Context context, String type) {
        Intent intent = new Intent(context, LoanListActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getStringExtra("type");
            switch (type) {
                case "额度高":
                    loanType = "high";
                    break;
                case "利息低":
                    loanType = "low";
                    break;
                case "放款快":
                    loanType = "fast";
                    break;
            }
        }
        page = 1;
        size = 10;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new LoanListPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_loan;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        //设置标题
        title.setText(type);
        //初始化列表
        initRecycler();
        //添加监听
        addListener();
        //加载数据
        loadData();
    }

    private void loadData() {
        mPresenter.getList(loanType,page,size);
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
        if(swipeLoan.isRefreshing()){
            swipeLoan.setRefreshing(false);
        }
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
        recycleLoan.setLayoutManager(layoutManager);
        //间隔线的 颜色  宽度 左边距 右边距
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#ECEDF0"), DpPxUtil.dip2px(this, 7.0f), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recycleLoan.addItemDecoration(itemDecoration);
        recycleLoan.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<LoanBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new LoanListViewHolder(parent,type);
            }
        });
    }

    @Override
    public void addListener() {
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        swipeLoan.setOnRefreshListener(this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                loginFlag = (boolean) SPutils.get(LoanListActivity.this, "login", false);
                infoFlag = (boolean) SPutils.get(LoanListActivity.this, "info", false);
                if (loginFlag) {
                    if (infoFlag) {
                        //保存点击记录
                        //String id= String.valueOf(adapter.getAllData().get(position).getId());
                        String type="SuperMarket";
                        //mPresenter.saveLog(type,id);
                        //跳链接
                        //String url=adapter.getAllData().get(position).getUrl();
                        //H5Activity.createActivity(LoanListActivity.this,url,"");
                    } else {
                        //资料完善
                        InfoStepOneActivity.createActivity(LoanListActivity.this);
                    }
                } else {
                    //登录
                    LoginActivity.createActivity(LoanListActivity.this, "");
                }

            }
        });
    }

    @Override
    public void getListSuccess(List<LoanBean> list, Header header) {
        swipeLoan.setRefreshing(false);
        adapter.addAll(list);
        int total=header.getPage().getCount();
        int index=header.getPage().getIndex();
        if (index==total) {
            if (page == 1) {
                //adapter.pauseMore();
                adapter.stopMore();
            } else {
                adapter.stopMore();
            }
        }
    }


    @OnClick(R.id.left_icon)
    public void onViewClicked() {
        //返回
        finish();
        overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
    }

    @Override
    public void onRefresh() {
        adapter.clear();
        page = 1;
        mPresenter.getList(loanType,page, size);
    }

    @Override
    public void onMoreShow() {
        page++;
        mPresenter.getList(loanType,page, size);
    }

    @Override
    public void onMoreClick() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }

}
