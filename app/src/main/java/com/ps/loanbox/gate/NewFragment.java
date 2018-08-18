package com.ps.loanbox.gate;

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
import com.ps.loanbox.bean.Header;
import com.ps.loanbox.fragment.BaseFragment;
import com.ps.loanbox.util.DpPxUtil;
import com.ps.loanbox.util.SPutils;
import com.ps.loanbox.util.StatBarCpmpart;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 8146 on 2018/2/9.
 */

public class NewFragment extends BaseFragment implements NewContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnMoreListener {

    @BindView(R.id.status_bar)
    LinearLayout statusBar;
    @BindView(R.id.recycler_instead)
    EasyRecyclerView recyclerInstead;
    @BindView(R.id.swipe_instead)
    SwipeRefreshLayout swipeInstead;
    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;



    //图片默认域名
    private String baseImgUrl;
    //list适配器
    private RecyclerArrayAdapter<NewBean> adapter;
    //P层
    private NewPresenter mPresenter;
    //页面
    private int page = 1;
    //单页数量
    private int size = 10;

    private  String type;


    public static NewFragment getInstance(String type) {
        NewFragment fragment = new NewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    protected void initVariables() {
        Bundle bundle = getArguments();
        type = bundle.getString("type");
        baseImgUrl = (String) SPutils.get(getActivity(), "baseImgUrl", "");
        page = 1;
        size = 10;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatBarCpmpart.init(getActivity(), statusBar);
        leftIcon.setVisibility(View.GONE);
        switch (type){

            case "1":
                title.setText("新闻");
                break;
            case "2":
                title.setText("经济");
                break;
            case "3":
                title.setText("财经");
                break;
        }

        //初始化列表
        initRecycler();
        //添加监听
        addListener();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new NewPresenter(getActivity(), this);
    }

    @Override
    protected void loadData() {

        mPresenter.getList(type,page, size);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main_instead;
    }

    @Override
    public boolean isUseBuffer() {
        return true;
    }

    @Override
    public void getError(Throwable e) {
        if (swipeInstead.isRefreshing()) {
            swipeInstead.setRefreshing(false);
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerInstead.setLayoutManager(layoutManager);
        //间隔线的 颜色  宽度 左边距 右边距
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#ECEDF0"), DpPxUtil.dip2px(getActivity(), 0.5f), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerInstead.addItemDecoration(itemDecoration);
        recyclerInstead.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<NewBean>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new NewViewHolder(parent);
            }
        });
    }



    @Override
    public void addListener() {
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        swipeInstead.setOnRefreshListener(this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //跳链接
                    NewActivity.createActivity(getActivity(),String.valueOf(adapter.getAllData().get(position).getId()));
            }
        });
//        banner.setOnPageClickListener(new CustomBanner.OnPageClickListener<String>() {
//            @Override
//            public void onPageClick(int position, String str) {
//                //position 轮播图的第几个项
//                //str 轮播图当前项对应的数据
//                if(bannerlist.get(position).getUrl()!=null&&!"".equals(bannerlist.get(position).getUrl())){
//                    H5Activity.createActivity(getActivity(),bannerlist.get(position).getUrl(),"");
//                }
//            }
//        });

    }



    @Override
    public void getListSuccess(List<NewBean> list, Header header) {
        swipeInstead.setRefreshing(false);
        adapter.addAll(list);
        int total = header.getPage().getCount();
        int index = header.getPage().getIndex();
        if (index == total) {
            if (page == 1) {
                //  adapter.pauseMore();
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
        mPresenter.getList(type,page, size);

    }

    @Override
    public void onMoreShow() {
        page++;
        mPresenter.getList(type,page, size);
    }

    @Override
    public void onMoreClick() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }

}

