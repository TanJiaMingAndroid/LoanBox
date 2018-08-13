package com.ps.eachgold.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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
import com.ps.eachgold.activity.credit.ProductDetailActivity;
import com.ps.eachgold.bean.BankCardBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.contract.main.CreditContract;
import com.ps.eachgold.popupWindow.WaittingProgressPop;
import com.ps.eachgold.presenter.credit.CreditPresenter;
import com.ps.eachgold.util.DpPxUtil;
import com.ps.eachgold.util.StatBarCpmpart;
import com.ps.eachgold.viewHold.FragmentCreditViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 8146 on 2018/1/15.
 * daik Fragment
 */

public class CreditFragment extends BaseFragment implements CreditContract.View, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.status_bar)
    LinearLayout statusBar;
    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    Unbinder unbinder;
    @BindView(R.id.recycler_credit)
    EasyRecyclerView recyclerCredit;
    @BindView(R.id.swipe_credit)
    SwipeRefreshLayout swipeCredit;


    //list适配器-卡片
    private RecyclerArrayAdapter<LoanBean> creditAdapter;

    //银行卡列表页面
    private int page = 1;
    //单银行卡列表页面展示数
    private int size = 3;

    private int sort = 0;
    //p层
    private CreditPresenter mPresenter;
    private WaittingProgressPop waittingProgressPop;
    String[] titles = {"Semua", "Jumlah pinjaman tinggi", "Suku bunga rendah", "Waktu pinjaman panjang"};

    //
    public static CreditFragment newInstance() {
        CreditFragment fragment = new CreditFragment();
        return fragment;
    }

    @Override
    protected void initVariables() {
        page = 1;
        size = 3;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatBarCpmpart.init(getActivity(), statusBar);
        leftIcon.setVisibility(View.GONE);
        title.setText("Pinjaman");
        //初始化列表
        initRecycler();
        //初始化头部银行列表
        initHeadRecycler();
        //添加头部
        addHead();
        //添加椒图
        addFoot();
        //添加监听
        addListener();
        //tab
        for (int i = 0; i < titles.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(titles[i]);
            tabLayout.addTab(tab);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                creditAdapter.clear();//每次清空避免数据累加
                sort = tab.getPosition();
                //0 默认 1额度 2利率 3期限
                mPresenter.getProductList(page,size,sort);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    @Override
    protected void initPresenter() {
        mPresenter = new CreditPresenter(getActivity(), this);
    }

    @Override
    protected void loadData() {
        mPresenter.getProductList(page,size,sort);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main_credit;
    }

    @Override
    public boolean isUseBuffer() {
        return true;
    }

    @Override
    public void getError(Throwable e) {
        if (swipeCredit.isRefreshing()) {
            swipeCredit.setRefreshing(false);
        }
    }

    @Override
    public void showMyProgressDialog(String message) {
        waittingProgressPop = new WaittingProgressPop(getActivity(), message);
        waittingProgressPop.showPopupWindow();
    }

    @Override
    public void hiddenProgressDialog() {
        if (waittingProgressPop != null) {
            waittingProgressPop.dismiss();
        }
    }


    @Override
    public void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerCredit.setLayoutManager(layoutManager);
        //间隔线的 颜色  宽度 左边距 右边距
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#ECEDF0"), DpPxUtil.dip2px(getActivity(), 0.5f), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerCredit.addItemDecoration(itemDecoration);
        recyclerCredit.setAdapterWithProgress(creditAdapter = new RecyclerArrayAdapter<LoanBean>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new FragmentCreditViewHolder(parent);
            }
        });

        //recyclerCredit.getRecyclerView().setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void addHead() {

       /* bankAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return viewHeader;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });*/
    }

    private void initHeadRecycler() {
        /*viewHeader = LayoutInflater.from(getActivity()).inflate(R.layout.head_fragment_credit, null);
        banner = viewHeader.findViewById(R.id.banner);
        linMore = viewHeader.findViewById(R.id.lin_more);//查看更多
        recyclerHead.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerHead.setAdapterWithProgress(bankAdapter = new RecyclerArrayAdapter<BankBean>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new FragmentCreditHeadViewHolder(parent, 1);
            }
        });*/

    }

    @Override
    public void addFoot() {
        /*viewFooter = LayoutInflater.from(getActivity()).inflate(R.layout.foot_fragment_credit, recyclerCredit, false);
        tvMore = viewFooter.findViewById(R.id.tv_credit_more); //查看更多

        creditAdapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return viewFooter;
            }

            @Override
            public void onBindView(View footerView) {

            }
        });*/
    }

    @Override
    public void addListener() {
        swipeCredit.setOnRefreshListener(this);
        creditAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ProductDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getProductListSuccess(List<LoanBean> list, Header header) {
        //返回的list列表数据
        swipeCredit.setRefreshing(false);
        creditAdapter.addAll(list);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }

    @Override
    public void onRefresh() {
        creditAdapter.clear();
        mPresenter.getProductList(page, size,sort);
        //刷新时复原
        /*linMore.setVisibility(View.VISIBLE);
        tvMore.setVisibility(View.GONE);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
