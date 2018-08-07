package com.ps.eachgold.activity.credit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
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
import com.ps.eachgold.activity.H5Activity;
import com.ps.eachgold.activity.login.InfoStepOneActivity;
import com.ps.eachgold.activity.login.LoginActivity;
import com.ps.eachgold.bean.BankCardBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.contract.credit.BankCreditListContract;
import com.ps.eachgold.presenter.credit.BankCreditListPresenter;
import com.ps.eachgold.util.DpPxUtil;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.viewHold.FragmentCreditViewHolder;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by 8146 on 2017/1/12.
 * 单个银卡所有卡-页面
 * From-CreditFragment-所有银行
 */

public class BankCreditListActivity extends BaseActivity implements BankCreditListContract.View, RecyclerArrayAdapter.OnMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycler_bank_credit_list)
    EasyRecyclerView recyclerBankCreditList;
    @BindView(R.id.swipe_bank_credit_list)
    SwipeRefreshLayout swipeBankCreditList;
    @BindView(R.id.tv_search_progress)
    TextView tvSearchProgress;

    //银行名称
    private String bankName;
    //银行id
    private String bankId;
    //银行进度查询url
    private String url;
    //list适配器
    private RecyclerArrayAdapter<BankCardBean> adapter;
    //当前页数
    private int page = 0;//默认值
    //单页显示多少
    private int size = 10;//默认值0
    //登录状态
    private Boolean loginFlag;
    //资料填写状态
    private Boolean infoFlag;
    //P层
    private BankCreditListPresenter mPresenter;

    //跳转
    public static void createActivity(Context context, String bankName, String bankId, String url) {
        Intent intent = new Intent(context, BankCreditListActivity.class);
        intent.putExtra("bankName", bankName);
        intent.putExtra("bankId", bankId);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    //初始化变量，intent携带的数据和Activity的变量
    @Override
    protected void initVariables() {
        page = 1;
        Intent intent = getIntent();
        if (intent != null) {
            bankName = intent.getStringExtra("bankName");
            bankId = intent.getStringExtra("bankId");
            url = intent.getStringExtra("url");
        }
    }

    //初始化P层
    @Override
    protected void initPresenter() {
        mPresenter = new BankCreditListPresenter(this, this);
    }

    //加载XML
    @Override
    public int getLayout() {
        return R.layout.activity_bank_credit_list;
    }

    //初始化视图
    @Override
    protected void initView(Bundle savedInstanceState) {
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        //设置标题
        title.setText(bankName);
        //因中国银行链接问题，所以屏蔽链接跳转，以后可能修改。
        if ("中国银行".equals(bankName)) {
            tvSearchProgress.setVisibility(View.GONE);
        }
        //初始化列表
        initRecycler();
        //添加监听  点击 /下拉刷新，上拉加载
        addListener();
        //加载数据
        loadData();

    }

    //加载数据
    protected void loadData() {
        mPresenter.getList(bankId, page, size);
    }

    @Override
    public boolean isUseButterKnife() {
        return true;
    }

    @Override
    protected boolean isAllowFullScreen() {
        return false;
    }

    //错误处理
    @Override
    public void getError(Throwable e) {
        if (swipeBankCreditList.isRefreshing()) {
            swipeBankCreditList.setRefreshing(false);
        }
    }

    //网络请求时 加载框的 显示与 隐藏
    @Override
    public void showMyProgressDialog(String message) {

    }

    @Override
    public void hiddenProgressDialog() {

    }

    @Override
    public void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerBankCreditList.setLayoutManager(layoutManager);
        //DividerDecoration的4个参数: 间隔线的颜色、宽度、左边距、右边距。
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#ECEDF0"), DpPxUtil.dip2px(this, 0.5f), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerBankCreditList.addItemDecoration(itemDecoration);
        recyclerBankCreditList.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<BankCardBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new FragmentCreditViewHolder(parent);
            }
        });
    }

    @Override
    public void addListener() {
        //上拉加载
        adapter.setMore(R.layout.view_more, this);
        //加载到底
        adapter.setNoMore(R.layout.view_nomore);
        //下拉刷新
        swipeBankCreditList.setOnRefreshListener(this);
        //列表点击
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                loginFlag = (boolean) SPutils.get(BankCreditListActivity.this, "login", false);
                infoFlag = (boolean) SPutils.get(BankCreditListActivity.this, "info", false);
                if (loginFlag) {
                    if (infoFlag) {
                        //保存点击记录
                        String id = String.valueOf(adapter.getAllData().get(position).getId());
                        String type = "Card";
                        mPresenter.saveLog(type, id);
                        //跳连接
                        String mUrl = adapter.getAllData().get(position).getUrl();
                        H5Activity.createActivity(BankCreditListActivity.this, mUrl, "");
                    } else {
                        //资料完善
                        InfoStepOneActivity.createActivity(BankCreditListActivity.this);
                    }
                } else {
                    //登录
                    LoginActivity.createActivity(BankCreditListActivity.this, "");
                }
            }
        });
    }

    //获取数据成功
    @Override
    public void getBankCardListSuccess(List<BankCardBean> list, Header header) {
        swipeBankCreditList.setRefreshing(false);
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

    //下拉刷新
    @Override
    public void onRefresh() {
        adapter.clear();
        page = 1;
        loadData();
    }

    //上拉加载
    @Override
    public void onMoreShow() {
        page++;
        loadData();
    }

    //上拉点击加载 （需求不需要）
    @Override
    public void onMoreClick() {

    }


    @OnClick({R.id.left_icon, R.id.tv_search_progress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                //返回
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
                break;
            case R.id.tv_search_progress:
                //进度查询
                H5Activity.createActivity(this, url, "");
                break;
        }
    }

    //销毁时 解绑-防内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }


}
