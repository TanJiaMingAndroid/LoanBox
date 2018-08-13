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
import com.ps.eachgold.bean.BannerBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.contract.main.LoanContract;
import com.ps.eachgold.presenter.LoanPresenter;
import com.ps.eachgold.util.DpPxUtil;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.viewHold.FragmentLoanViewHolder;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HotLoanActivity extends BaseActivity implements LoanContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnMoreListener {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycler_hot_loan)
    EasyRecyclerView recyclerHotLoan;
    @BindView(R.id.swipe_hot_loan)
    SwipeRefreshLayout swipeHotLoan;

    //list适配器
    private RecyclerArrayAdapter<LoanBean> adapter;
    //P层
    private LoanPresenter mPresenter;
    //登录状态
    private Boolean loginFlag;
    //资料填写状态
    private Boolean infoFlag;
    //页面
    private int page = 1;
    //单页数量
    private int size = 10;


    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, HotLoanActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected void initVariables() {
        page = 1;
        size = 10;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new LoanPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_hot_loan;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        //设置标题
        title.setText("热门推荐");
        //初始化列表
        initRecycler();
        //添加监听
        addListener();
        //加载数据
        loadData();
    }

    private void loadData() {

        mPresenter.getList(page, size);
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
        swipeHotLoan.setRefreshing(false);
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
        recyclerHotLoan.setLayoutManager(layoutManager);
        //间隔线的 颜色  宽度 左边距 右边距
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#ECEDF0"), DpPxUtil.dip2px(this, 0.5f), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerHotLoan.addItemDecoration(itemDecoration);
        recyclerHotLoan.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<LoanBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new FragmentLoanViewHolder(parent);
            }
        });
    }

    @Override
    public void addHead() {

    }

    @Override
    public void addFoot() {

    }

    @Override
    public void addListener() {
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        swipeHotLoan.setOnRefreshListener(this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                loginFlag = (boolean) SPutils.get(HotLoanActivity.this, "login", false);
                infoFlag = (boolean) SPutils.get(HotLoanActivity.this, "info", false);
                if (loginFlag) {
                    if (infoFlag) {
                        //保存点击记录
                        //String id= String.valueOf(adapter.getAllData().get(position).getId());
                        String type="SuperMarket";
                        //mPresenter.saveLog(type,id);
                        //跳链接
                        //String mUrl = adapter.getAllData().get(position).getUrl();
                        //H5Activity.createActivity(HotLoanActivity.this, mUrl, "");
                    } else {
                        //资料完善
                        InfoStepOneActivity.createActivity(HotLoanActivity.this);
                    }
                } else {
                    //登录
                    LoginActivity.createActivity(HotLoanActivity.this);
                }

            }
        });
    }

    //这里因为共用LoanContract 此方法在这个Activity不使用
    @Override
    public void getBannerSuccess(List<BannerBean> list) {

    }

    @Override
    public void getListSuccess(List<LoanBean> list, Header header) {
        swipeHotLoan.setRefreshing(false);
        adapter.addAll(list);
        //总共页数
        int total = header.getPage().getCount();
        //当前页数
        int index = header.getPage().getIndex();
        if (index == total) {
            //如果只有一页
            if (page == 1) {
               // adapter.pauseMore();
                adapter.stopMore();
            } else {
                adapter.stopMore();
            }
        }

    }


    @Override
    public void onRefresh() {
        adapter.clear();
        page = 1;
        mPresenter.getList(page, size);
    }

    @Override
    public void onMoreShow() {
        page++;
        mPresenter.getList(page, size);
    }

    @Override
    public void onMoreClick() {

    }

    @OnClick(R.id.left_icon)
    public void onViewClicked() {
        //返回
        finish();
        overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }


}
