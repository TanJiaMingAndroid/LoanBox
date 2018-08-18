package com.ps.loanbox.activity.loan;

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
import com.ps.loanbox.R;
import com.ps.loanbox.activity.BaseActivity;
import com.ps.loanbox.activity.login.InfoStepOneActivity;
import com.ps.loanbox.activity.login.LoginActivity;
import com.ps.loanbox.bean.Header;
import com.ps.loanbox.bean.ProductBean;
import com.ps.loanbox.contract.loan.RepayCardContract;
import com.ps.loanbox.presenter.RepayCardPresenter;
import com.ps.loanbox.util.DpPxUtil;
import com.ps.loanbox.util.SPutils;
import com.ps.loanbox.viewHold.RepayCardViewHolder;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by 8146 on 2017/1/12.
 * 帮你还卡-页面
 * From-LoanFragment-帮你还卡
 */
public class  RepayCardActivity extends BaseActivity implements RepayCardContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnMoreListener {


    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycler_repay_card)
    EasyRecyclerView recyclerRepayCard;
    @BindView(R.id.swipe_repay_card)
    SwipeRefreshLayout swipeRepayCard;

    //list适配器
    private RecyclerArrayAdapter<ProductBean> adapter;

    //登录状态
    private Boolean loginFlag;
    //资料填写状态
    private Boolean infoFlag;
    //页面
    private int page = 1;
    //单页数量
    private int size = 10;
    //P层
    private RepayCardPresenter mPresenter;
    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, RepayCardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        page=1;
        size=10;
    }

    @Override
    protected void initPresenter() {
        mPresenter=new RepayCardPresenter(this,this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_repay_card;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        //设置标题
        title.setText("帮你还卡");
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
        swipeRepayCard.setRefreshing(false);
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
        recyclerRepayCard.setLayoutManager(layoutManager);
        //间隔线的 颜色  宽度 左边距 右边距
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#ECEDF0"), DpPxUtil.dip2px(this, 7.0f), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerRepayCard.addItemDecoration(itemDecoration);
        recyclerRepayCard.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<ProductBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new RepayCardViewHolder(parent);
            }
        });
    }

    @Override
    public void addListener() {
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        swipeRepayCard.setOnRefreshListener(this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                loginFlag = (boolean) SPutils.get(RepayCardActivity.this, "login", false);
                infoFlag = (boolean) SPutils.get(RepayCardActivity.this, "info", false);
                if (loginFlag) {
                    if(infoFlag){
                        //保存点击记录
                        //String id= String.valueOf(adapter.getAllData().get(position).getId());
                        String type="Repayment";
                        //mPresenter.saveLog(type,id);
                        //跳链接
                        //String mUrl=adapter.getAllData().get(position).getUrl();
                       // H5Activity.createActivity(RepayCardActivity.this,mUrl,"");

                    }else {
                        //资料完善
                        InfoStepOneActivity.createActivity(RepayCardActivity.this);
                    }
                } else {
                    //登录
                    LoginActivity.createActivity(RepayCardActivity.this);
                }
            }
        });
    }

    @Override
    public void getListSuccess(List<ProductBean> list, Header header) {
        swipeRepayCard.setRefreshing(false);
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
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
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
}
