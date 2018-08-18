package com.ps.loanbox.activity.person;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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
import com.ps.loanbox.activity.credit.ProductDetailActivity;
import com.ps.loanbox.bean.Header;
import com.ps.loanbox.bean.ProductBean;
import com.ps.loanbox.contract.person.CollectionContract;
import com.ps.loanbox.presenter.person.CollectionPresenter;
import com.ps.loanbox.util.DpPxUtil;
import com.ps.loanbox.viewHold.FragmentLoanViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CollectionActivity extends BaseActivity implements CollectionContract.View, RecyclerArrayAdapter.OnMoreListener{
    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycler_message)
    EasyRecyclerView recyclerMessage;
    @BindView(R.id.lin_no_data)
    LinearLayout linNoData;

    //适配器
    private RecyclerArrayAdapter<ProductBean> adapter;
    //P
    private CollectionPresenter mPresenter;



    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, CollectionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initPresenter() {
        mPresenter = new CollectionPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        title.setText("我的收藏");
        //初始化列表
        initRecycler();
        //加载数据
        loadData();
    }

    private void loadData() {
        mPresenter.getCollection();
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

    public void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerMessage.setLayoutManager(layoutManager);
        //间隔线的 颜色  宽度 左边距 右边距
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#CCCCCC"), DpPxUtil.dip2px(this, 1), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerMessage.addItemDecoration(itemDecoration);
            recyclerMessage.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<ProductBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new FragmentLoanViewHolder(parent);
            }
        });
    }

    public void addListener() {
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ProductDetailActivity.createActivity(CollectionActivity.this,adapter.getItem(position).getProductId());
            }
        });
    }

    @Override
    public void getCollectionSuccess(List<ProductBean> list, Header header) {
        Log.e("CollectionActivity","getCollectionSuccess(CollectionActivity.java:152)"+list.size());
        if(list.size()>0){
            recyclerMessage.setVisibility(View.VISIBLE);
            linNoData.setVisibility(View.GONE);
            adapter.addAll(list);

        }else {
            linNoData.setVisibility(View.VISIBLE);
            recyclerMessage.setVisibility(View.GONE);
            hiddenProgressDialog();
        }

    }

    @OnClick(R.id.left_icon)
    public void onViewClicked() {
        finish();
        overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
    }

    @Override
    public void onMoreShow() {
        mPresenter.getCollection();
    }

    @Override
    public void onMoreClick() {

    }



}
