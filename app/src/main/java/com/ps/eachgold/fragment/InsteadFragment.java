package com.ps.eachgold.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.donkingliang.banner.CustomBanner;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.H5Activity;
import com.ps.eachgold.activity.login.InfoStepOneActivity;
import com.ps.eachgold.activity.login.LoginActivity;
import com.ps.eachgold.bean.BannerBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.contract.main.InsteadContract;
import com.ps.eachgold.presenter.InsteadPresenter;
import com.ps.eachgold.util.DpPxUtil;
import com.ps.eachgold.util.GlideApp;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.util.StatBarCpmpart;
import com.ps.eachgold.viewHold.FragmentInsteadViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 8146 on 2018/1/15.
 * 代还信用卡-Fragment
 */

public class InsteadFragment extends BaseFragment implements InsteadContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnMoreListener {

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
    private RecyclerArrayAdapter<LoanBean> adapter;
    //list头部View
    private View viewHeader;
    //banner
    private CustomBanner banner;
    //P层
    private InsteadPresenter mPresenter;
    //登录状态
    private Boolean loginFlag;
    //资料填写状态
    private Boolean infoFlag;
    //页面
    private int page = 1;
    //单页数量
    private int size = 10;
    //banner
    private List<BannerBean> bannerlist;

    private ImageView  ivOneBanner;

    public static InsteadFragment newInstance() {
        InsteadFragment fragment = new InsteadFragment();
        return fragment;
    }

    @Override
    protected void initVariables() {
        baseImgUrl = (String) SPutils.get(getActivity(), "baseImgUrl", "");
        bannerlist=new ArrayList<>();
        page = 1;
        size = 10;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatBarCpmpart.init(getActivity(), statusBar);
        leftIcon.setVisibility(View.GONE);
        title.setText("信用卡代还");
        //初始化列表
        initRecycler();
        //添加头部
        addHead();
        //添加监听
        addListener();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new InsteadPresenter(getActivity(), this);
    }

    @Override
    protected void loadData() {
        mPresenter.getBanner();
        mPresenter.getList(page, size);
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
        recyclerInstead.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<LoanBean>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new FragmentInsteadViewHolder(parent);
            }
        });
    }

    @Override
    public void addHead() {
        viewHeader = LayoutInflater.from(getActivity()).inflate(R.layout.head_fragment_instead, null);
        banner = viewHeader.findViewById(R.id.banner);
        ivOneBanner=viewHeader.findViewById(R.id.iv_one_banner);;
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return viewHeader;
            }

            @Override
            public void onBindView(View headerView) {

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
                loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
                infoFlag = (boolean) SPutils.get(getActivity(), "info", false);
                if (loginFlag) {
                    if (infoFlag) {
                        //保存点击记录
                        String id= String.valueOf(adapter.getAllData().get(position).getId());
                        String type="Repayment  ";
                        mPresenter.saveLog(type,id);
                        //跳链接
                        String mUrl = adapter.getAllData().get(position).getUrl();
                        H5Activity.createActivity(getActivity(), mUrl, "");
                    } else {
                        //资料完善
                        InfoStepOneActivity.createActivity(getActivity());
                    }
                } else {
                    //登录
                    LoginActivity.createActivity(getActivity(), "");
                }

            }
        });
        banner.setOnPageClickListener(new CustomBanner.OnPageClickListener<String>() {
            @Override
            public void onPageClick(int position, String str) {
                //position 轮播图的第几个项
                //str 轮播图当前项对应的数据
                if(bannerlist.get(position).getUrl()!=null&&!"".equals(bannerlist.get(position).getUrl())){
                    H5Activity.createActivity(getActivity(),bannerlist.get(position).getUrl(),"");
                }
            }
        });

    }

    @Override
    public void getBannerSuccess(List<BannerBean> list) {
        bannerlist.clear();
        bannerlist=list;
        if (list != null && list.size() > 0) {
            initBanner(list);
            if(list.size()==1){
                ivOneBanner.setVisibility(View.VISIBLE);
                banner.setVisibility(View.GONE);
                String baseUrl = (String) SPutils.get(getContext(), "baseImgUrl", "");
                GlideApp.with(getActivity()).load("http:" + baseUrl + list.get(0).getPic())
                        .placeholder(R.mipmap.banner_glide)
                        .error(R.mipmap.banner_glide)
                        .into(ivOneBanner);
            }else {
                ivOneBanner.setVisibility(View.GONE);
                banner.setVisibility(View.VISIBLE);
            }
        } else {
            initBanner2();
        }

    }

    @Override
    public void getListSuccess(List<LoanBean> list, Header header) {
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
        mPresenter.getList(page, size);
        mPresenter.getBanner();
    }

    @Override
    public void onMoreShow() {
        page++;
        mPresenter.getList(page, size);
    }

    @Override
    public void onMoreClick() {

    }

    private void initBanner2() {

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.mipmap.banner_glide);


        banner.setPages(new CustomBanner.ViewCreator<Integer>() {
            @Override
            public View createView(Context context, int position) {
                //这里返回的是轮播图的项的布局 支持任何的布局
                //position 轮播图的第几个项
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int position, Integer data) {
                //在这里更新轮播图的UI
                //position 轮播图的第几个项
                //view 轮播图当前项的布局 它是createView方法的返回值
                //data 轮播图当前项对应的数据

                GlideApp.with(context).load(data)
                        .placeholder(R.mipmap.banner_glide)
                        .error(R.mipmap.banner_glide)
                        .into((ImageView) view);

            }
        }, images).startTurning(3000);

    }

    private void initBanner(List<BannerBean> list) {

        ArrayList<String> images = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            images.add(list.get(i).getPic());
        }


        banner.setPages(new CustomBanner.ViewCreator<String>() {
            @Override
            public View createView(Context context, int position) {
                //这里返回的是轮播图的项的布局 支持任何的布局
                //position 轮播图的第几个项
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int position, String data) {
                //在这里更新轮播图的UI
                //position 轮播图的第几个项
                //view 轮播图当前项的布局 它是createView方法的返回值
                //data 轮播图当前项对应的数
                String baseUrl = (String) SPutils.get(getContext(), "baseImgUrl", "");

                GlideApp.with(context).load("http:" + baseUrl + data)
                        .placeholder(R.mipmap.banner_glide)
                        .error(R.mipmap.banner_glide)
                        .into((ImageView) view);
            }
        }, images).startTurning(3000);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }

}
