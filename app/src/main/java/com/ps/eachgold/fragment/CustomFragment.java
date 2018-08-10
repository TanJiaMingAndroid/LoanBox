package com.ps.eachgold.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.H5Activity;
import com.ps.eachgold.bean.BankCardBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.contract.person.CustomContract;
import com.ps.eachgold.presenter.CustomPresenter;
import com.ps.eachgold.util.DpPxUtil;
import com.ps.eachgold.viewHold.FragmentCreditViewHolder;
import com.ps.eachgold.viewHold.FragmentInsteadViewHolder;
import com.ps.eachgold.viewHold.FragmentLoanViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 8146 on 2018/1/16.
 * 查看更多- 我的专属
 */

public class CustomFragment extends BaseFragment implements CustomContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_custom)
    EasyRecyclerView recyclerCustom;
    @BindView(R.id.swipe_custom)
    SwipeRefreshLayout swipeCustom;

    //参数   SuperMarket Repayment Card
    private String laberName;
    //接受Intent的参数  贷款产品  信用卡代还 信用卡产品
    private String type;
    // 贷款类  列表适配器
    private RecyclerArrayAdapter<LoanBean> loanAdapter;
    //信用卡类  列表适配器
    private RecyclerArrayAdapter<BankCardBean> bankCardadapter;
    //P层
    private CustomPresenter mPresenter;

    public static CustomFragment getInstance(String type) {
        CustomFragment fragment = new CustomFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initVariables() {
        Bundle bundle = getArguments();
        type = bundle.getString("type");
        switch (type) {
            case "贷款产品":
                laberName = "SuperMarket";
                break;
            case "信用卡代还":
                laberName = "Repayment";
                break;
            case "信用卡产品":
                laberName = "Card";
                break;
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //初始化列表
        initRecycler();
        //添加监听
        addListener();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new CustomPresenter(getActivity(), this);
    }

    @Override
    protected void loadData() {
        switch (type) {
            case "贷款产品":
                mPresenter.getList(laberName);
                break;
            case "信用卡代还":
                mPresenter.getList(laberName);
                break;
            case "信用卡产品":
                mPresenter.getCardList(laberName);
                break;
        }

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_person_custom;
    }

    @Override
    public boolean isUseBuffer() {
        return true;
    }


    @Override
    public void getError(Throwable e) {
        swipeCustom.setRefreshing(false);
    }

    @Override
    public void showMyProgressDialog(String message) {

    }

    @Override
    public void hiddenProgressDialog() {

    }

    @Override
    public void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerCustom.setLayoutManager(layoutManager);
        //间隔线的 颜色  宽度 左边距 右边距
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#ECEDF0"), DpPxUtil.dip2px(getActivity(), 0.5f), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerCustom.addItemDecoration(itemDecoration);

        switch (type) {
            case "贷款产品":
                recyclerCustom.setAdapterWithProgress(loanAdapter = new RecyclerArrayAdapter<LoanBean>(getActivity()) {
                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        return new FragmentLoanViewHolder(parent);
                    }
                });
                break;
            case "信用卡代还":
                recyclerCustom.setAdapterWithProgress(loanAdapter = new RecyclerArrayAdapter<LoanBean>(getActivity()) {
                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        return new FragmentInsteadViewHolder(parent);
                    }
                });
                break;
            case "信用卡产品":
                recyclerCustom.setAdapterWithProgress(bankCardadapter = new RecyclerArrayAdapter<BankCardBean>(getActivity()) {
                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        return new FragmentCreditViewHolder(parent);
                    }
                });
                break;
        }
    }

    @Override
    public void addListener() {
        swipeCustom.setOnRefreshListener(this);



    }

    @Override
    public void getListSuccess(List<LoanBean> list, Header header) {
        swipeCustom.setRefreshing(false);
        loanAdapter.clear();
        loanAdapter.addAll(list);
    }

    @Override
    public void getCardListSuccess(List<BankCardBean> list, Header header) {
        swipeCustom.setRefreshing(false);
        bankCardadapter.clear();
        bankCardadapter.addAll(list);
    }

    @Override
    public void onRefresh() {
        switch (type) {
            case "贷款产品":
                loanAdapter.clear();
                mPresenter.getList(laberName);
                break;
            case "信用卡代还":
                loanAdapter.clear();
                mPresenter.getList(laberName);
                break;
            case "信用卡产品":
                bankCardadapter.clear();
                mPresenter.getCardList(laberName);
                break;
        }

    }


}
