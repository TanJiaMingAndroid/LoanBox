package com.ps.eachgold.activity.credit;

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
import com.ps.eachgold.activity.login.InfoStepOneActivity;
import com.ps.eachgold.activity.login.LoginActivity;
import com.ps.eachgold.bean.BankCardBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.contract.credit.BankHotListContract;
import com.ps.eachgold.presenter.credit.BankHotListPresenter;
import com.ps.eachgold.util.DpPxUtil;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.viewHold.FragmentCreditViewHolder;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by 8146 on 2017/1/12.
 * 热门卡片-页面
 * From-CreditFragment-底部-查看更多
 */

public class BankHotListActivity extends BaseActivity implements BankHotListContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnMoreListener {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycle_hot_card)
    EasyRecyclerView recycleHotCard;
    @BindView(R.id.swipe_hot_card)
    SwipeRefreshLayout swipeHotCard;

    //list 适配器
    private RecyclerArrayAdapter<BankCardBean> adapter;
    //P层
    private BankHotListPresenter mPresenter;
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
        Intent intent = new Intent(context, BankHotListActivity.class);
        context.startActivity(intent);
    }

    //初始化变量，intent携带的数据和Activity的变量
    @Override
    protected void initVariables() {
        page = 1;
        size = 10;
        loginFlag = (boolean) SPutils.get(this, "login", false);
        infoFlag = (boolean) SPutils.get(this, "info", false);
    }

    //初始化P层
    @Override
    protected void initPresenter() {
        mPresenter = new BankHotListPresenter(this, this);
    }

    //加载XML
    @Override
    public int getLayout() {
        return R.layout.activity_bank_hot_list;
    }

    //初始化视图
    @Override
    protected void initView(Bundle savedInstanceState) {
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        // 设置标题
        title.setText("热门卡片");
        //初始化列表
        initRecycler();
        //添加监听
        addListener();
        //加载数据
        loadData();

    }

    private void loadData() {
        mPresenter.getCardList(page, size);
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
        if(swipeHotCard.isRefreshing()){
            swipeHotCard.setRefreshing(false);
        }
    }

    //网络请求时加载框 -根据需求是否使用
    @Override
    public void showMyProgressDialog(String message) {

    }

    @Override
    public void hiddenProgressDialog() {

    }

    @Override
    public void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleHotCard.setLayoutManager(layoutManager);
        //间隔线的 颜色  宽度 左边距 右边距
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#ECEDF0"), DpPxUtil.dip2px(this, 0.5f), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recycleHotCard.addItemDecoration(itemDecoration);
        recycleHotCard.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<BankCardBean>(this) {
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
        swipeHotCard.setOnRefreshListener(this);
        //列表点击
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (loginFlag) {
                    if (infoFlag) {
                        //保存点击记录
                        String id= String.valueOf(adapter.getAllData().get(position).getId());
                        String type="Card";
                        mPresenter.saveLog(type,id);
                        //跳连接
                    } else {
                        //资料完善
                        InfoStepOneActivity.createActivity(BankHotListActivity.this);
                    }
                } else {
                    //登录
                    LoginActivity.createActivity(BankHotListActivity.this);
                }
            }
        });
    }

    //数据请求成功
    @Override
    public void getBankCardListSuccess(List<BankCardBean> list, Header header) {
        swipeHotCard.setRefreshing(false);
        adapter.addAll(list);
        //总共页数
        int total = header.getPage().getCount();
        //当前页数
        int index = header.getPage().getIndex();
        if (index==total) {
            //只有一页
            if (page == 1) {
               // adapter.pauseMore();
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

    //下拉刷新
    @Override
    public void onRefresh() {
        adapter.clear();
        page = 1;
        mPresenter.getCardList(page, size);
    }

    //上拉加载
    @Override
    public void onMoreShow() {
        page++;
        mPresenter.getCardList(page, size);
    }

    @Override
    public void onMoreClick() {

    }

    //销毁时解除绑定
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }
}
