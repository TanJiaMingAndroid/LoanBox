package com.ps.eachgold.activity.loan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.activity.credit.BankCreditListActivity;
import com.ps.eachgold.bean.BankBean;
import com.ps.eachgold.contract.loan.HandleCardContract;
import com.ps.eachgold.presenter.HandleCardPresenter;
import com.ps.eachgold.util.RSAUtil;
import com.ps.eachgold.viewHold.FragmentCreditHeadViewHolder;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;



/**
 * Created by 8146 on 2017/1/12.
 * 我要办卡-页面
 * From-LoanFragment-我要办卡
 */

public class HandleCardActivity extends BaseActivity implements HandleCardContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycler_handle_card)
    EasyRecyclerView recyclerHandleCard;
    @BindView(R.id.swipe_handle_card)
    SwipeRefreshLayout swipeHandleCard;
    //P层
    private HandleCardPresenter mPresenter;
    //list适配器
    private RecyclerArrayAdapter<BankBean> adapter;
    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, HandleCardActivity.class);
        context.startActivity(intent);
    }

    //
    @Override
    protected void initVariables() {
    }

    //P层
    @Override
    protected void initPresenter() {
        mPresenter = new HandleCardPresenter(this, this);
    }

    //加载XML
    @Override
    public int getLayout() {
        return R.layout.activity_handle_card;
    }

    //初始化界面
    @Override
    protected void initView(Bundle savedInstanceState) {
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        //设置标题
        title.setText("我要办卡");
        //初始化列表
        initRecycler();
        //添加监听
        addListener();
        //加载数据
        loadData();
    }

    private void loadData() {
        mPresenter.getBankList();
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
        if (swipeHandleCard.isRefreshing()) {
            swipeHandleCard.setRefreshing(false);
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
        recyclerHandleCard.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerHandleCard.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<BankBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new FragmentCreditHeadViewHolder(parent,1);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(3));
        recyclerHandleCard.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void addListener() {
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //保存点击记录
                String bankid= String.valueOf(adapter.getAllData().get(position).getId());
                String type="bank";
                mPresenter.saveLog(type,bankid);
                //获取名字
                String name= adapter.getAllData().get(position).getBank();
                int id= adapter.getAllData().get(position).getId();
                String url=adapter.getAllData().get(position).getQueryUrl();
                BankCreditListActivity.createActivity(HandleCardActivity.this, name,String.valueOf(id),url);
            }
        });
        //下拉刷新
        swipeHandleCard.setOnRefreshListener(this);
    }

    @Override
    public void getBankListSuccess(List<BankBean> list) {
        swipeHandleCard.setRefreshing(false);
        adapter.addAll(list);
    }

    @OnClick(R.id.left_icon)
    public void onViewClicked() {
        //返回
        finish();
        overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
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
        adapter.clear();
        loadData();
    }
}
