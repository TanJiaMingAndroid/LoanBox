package com.ps.loanbox.activity.person;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.ps.loanbox.R;
import com.ps.loanbox.activity.BaseActivity;
import com.ps.loanbox.bean.Header;
import com.ps.loanbox.bean.MessageBean;
import com.ps.loanbox.contract.person.MessageContract;
import com.ps.loanbox.presenter.MessagePresenter;
import com.ps.loanbox.util.DpPxUtil;
import com.ps.loanbox.viewHold.MessageViewHolder;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity implements MessageContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnMoreListener {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycler_message)
    EasyRecyclerView recyclerMessage;
    @BindView(R.id.swipe_message)
    SwipeRefreshLayout swipeMessage;
    @BindView(R.id.lin_no_data)
    LinearLayout linNoData;


    //适配器
    private RecyclerArrayAdapter<MessageBean> adapter;
    //P
    private MessagePresenter mPresenter;
    //页面
    private int page = 1;
    //单页数量
    private int size = 10;
    //count  新消息数量
    private int num;

    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, MessageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        num = getIntent().getIntExtra("count", 0);
        page = 1;
        size = 10;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MessagePresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        title.setText("Notifikasi");
        //默认无数据 显示隐藏
        swipeMessage.setVisibility(View.GONE);
        linNoData.setVisibility(View.VISIBLE);
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
        swipeMessage.setRefreshing(false);
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
        recyclerMessage.setLayoutManager(layoutManager);
        //间隔线的 颜色  宽度 左边距 右边距
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#ECEDF0"), DpPxUtil.dip2px(this, 10), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerMessage.addItemDecoration(itemDecoration);
        recyclerMessage.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<MessageBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new MessageViewHolder(parent,num);
            }
        });
    }

    @Override
    public void addListener() {
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        swipeMessage.setOnRefreshListener(this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
    }

    @Override
    public void getListSuccess(List<MessageBean> list, Header header) {
        swipeMessage.setRefreshing(false);
        int total = header.getPage().getCount();
        int index = header.getPage().getIndex();
        if(total>0){
            swipeMessage.setVisibility(View.VISIBLE);
            linNoData.setVisibility(View.GONE);
            adapter.addAll(list);
            if (index == total) {
                if (page == 1) {
                    //adapter.pauseMore(); 使用则 列表底部 不显示 无更多文字
                    adapter.stopMore();
                } else {
                    adapter.stopMore();
                }
            }
        }else {
            swipeMessage.setVisibility(View.GONE);
            linNoData.setVisibility(View.VISIBLE);
        }

    }

    @OnClick(R.id.left_icon)
    public void onViewClicked() {
        finish();
        overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
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
