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
import com.ps.eachgold.MyAppConfig;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.H5Activity;
import com.ps.eachgold.activity.loan.HandleCardActivity;
import com.ps.eachgold.activity.loan.HotLoanActivity;
import com.ps.eachgold.activity.loan.LoanListActivity;
import com.ps.eachgold.activity.loan.RepayCardActivity;
import com.ps.eachgold.activity.login.InfoStepOneActivity;
import com.ps.eachgold.activity.login.LoginActivity;
import com.ps.eachgold.bean.BannerBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.contract.main.LoanContract;
import com.ps.eachgold.presenter.LoanPresenter;
import com.ps.eachgold.util.DpPxUtil;
import com.ps.eachgold.util.GlideApp;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.util.StatBarCpmpart;
import com.ps.eachgold.viewHold.FragmentLoanViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 8146 on 2018/1/15.
 * 贷款 -Fragment
 */

public class LoanFragment extends BaseFragment implements LoanContract.View, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_loan)
    EasyRecyclerView recyclerLoan;
    @BindView(R.id.swipe_loan)
    SwipeRefreshLayout swipeLoan;
    @BindView(R.id.status_bar)
    LinearLayout statusBar;
    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;

    //P层
    private LoanPresenter mPresenter;
    //list适配器
    private RecyclerArrayAdapter<LoanBean> adapter;
    //list头部View
    private View viewHeader;
    //额度高
    private CustomBanner banner;
    //额度高
    private LinearLayout linHighAmount;
    //利息低
    private LinearLayout linLowInterest;
    //放款快
    private LinearLayout linFastLending;
    //我要办卡
    private TextView tvHandleCard;
    //帮你换卡
    private TextView tvRepayCard;
    //登录状态
    private Boolean loginFlag;
    //资料填写状态
    private Boolean infoFlag;
    //图片默认域名
    private String baseImgUrl;
    //list尾部View
    private View viewFooter;
    //底部查看更多
    private TextView tvMore;
    //页面
    private int page = 1;
    //单页数量
    private int size = 5;

    private List<BannerBean> bannerlist;

    private long lastClickTime = 0;//点击的时间

    private ImageView  ivOneBanner;

    public static LoanFragment newInstance() {
        LoanFragment fragment = new LoanFragment();
        return fragment;
    }

    @Override
    protected void initVariables() {
        bannerlist = new ArrayList<>();
        baseImgUrl = (String) SPutils.get(getActivity(), "baseImgUrl", "");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        // ViewPager+Fragment沉浸式状态栏
        StatBarCpmpart.init(getActivity(), statusBar);
        leftIcon.setVisibility(View.GONE);
        title.setText("index");
        //初始化列表
        initRecycler();
        //添加头部
        addHead();
        //添加尾部
        addFoot();
        //添加监听
        addListener();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new LoanPresenter(getActivity(), this);
    }

    @Override
    protected void loadData() {
        mPresenter.getBanner();
        mPresenter.getList(page, size);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main_loan;
    }

    @Override
    public boolean isUseBuffer() {
        return true;
    }


    @Override
    public void getError(Throwable e) {
        swipeLoan.setRefreshing(false);
        initBanner2();
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
        recyclerLoan.setLayoutManager(layoutManager);
        //间隔线的 颜色  宽度 左边距 右边距
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#ECEDF0"), DpPxUtil.dip2px(getActivity(), 0.5f), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerLoan.addItemDecoration(itemDecoration);
        recyclerLoan.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<LoanBean>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new FragmentLoanViewHolder(parent);
            }
        });
    }

    @Override
    public void addHead() {
        viewHeader = LayoutInflater.from(getActivity()).inflate(R.layout.head_fragment_main_loan, null);
        banner = viewHeader.findViewById(R.id.banner); //额度高
        linHighAmount = viewHeader.findViewById(R.id.lin_high_amount); //额度高
        linLowInterest = viewHeader.findViewById(R.id.lin_low_interest);//利息低
        linFastLending = viewHeader.findViewById(R.id.lin_fast_lending); //放款快
        tvHandleCard = viewHeader.findViewById(R.id.tv_handle_card); //我要办卡
        tvRepayCard = viewHeader.findViewById(R.id.tv_repay_card); //帮你换卡

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

    private void initBanner2() {
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.mipmap.banner_glide);
        banner.setPages(new CustomBanner.ViewCreator<Integer>() {
            @Override
            public View createView(Context context, int position) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int position, Integer data) {
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
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int position, String data) {
                String baseUrl = (String) SPutils.get(getContext(), "baseImgUrl", "");
                GlideApp.with(context).load("http:" + baseUrl + data)
                        .placeholder(R.mipmap.banner_glide)
                        .error(R.mipmap.banner_glide)
                        .into((ImageView) view);
            }
        }, images).startTurning(3000);

    }


    @Override
    public void addFoot() {
        viewFooter = LayoutInflater.from(getActivity()).inflate(R.layout.foot_fragment_credit, recyclerLoan, false);
        tvMore = viewFooter.findViewById(R.id.tv_credit_more); //查看更多

        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return viewFooter;
            }

            @Override
            public void onBindView(View footerView) {

            }
        });
    }

    @Override
    public void addListener() {
        swipeLoan.setOnRefreshListener(this);
        linHighAmount.setOnClickListener(this);
        linLowInterest.setOnClickListener(this);
        linFastLending.setOnClickListener(this);
        tvHandleCard.setOnClickListener(this);
        tvRepayCard.setOnClickListener(this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
                infoFlag = (boolean) SPutils.get(getActivity(), "info", false);
                if (loginFlag) {
                    if (infoFlag) {
                        //保存点击记录
                        String id = String.valueOf(adapter.getAllData().get(position).getId());
                        String type = "SuperMarket";
                        mPresenter.saveLog(type, id);
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
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //底部查看更多跳转
                //热门推荐
                HotLoanActivity.createActivity(getActivity());
            }
        });
        banner.setOnPageClickListener(new CustomBanner.OnPageClickListener<String>() {
            @Override
            public void onPageClick(int position, String str) {
                //position 轮播图的第几个项
                //str 轮播图当前项对应的数据
                if(bannerlist.get(position).getUrl()!=null&&!"".equals(bannerlist.get(position).getUrl())){
                    H5Activity.createActivity(getActivity(), bannerlist.get(position).getUrl(), "");
                }
            }
        });
        //为空点击
        //recyclerLoan.getEmptyView().findViewById()
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.lin_high_amount://额度高
                long  currentTime1 = Calendar.getInstance().getTimeInMillis();
                if (currentTime1 - lastClickTime > MyAppConfig.MIN_CLICK_DELAY_TIME) {
                    //保存点击记录
                    lastClickTime = currentTime1;
                    LoanListActivity.createActivity(getActivity(), "额度高");
                }

                break;
            case R.id.lin_low_interest://利息低
                long currentTime2 = Calendar.getInstance().getTimeInMillis();
                if (currentTime2 - lastClickTime > MyAppConfig.MIN_CLICK_DELAY_TIME) {
                    //保存点击记录
                    lastClickTime = currentTime2;
                    LoanListActivity.createActivity(getActivity(), "利息低");
                }
                break;
            case R.id.lin_fast_lending://放款快
                long currentTime3 = Calendar.getInstance().getTimeInMillis();
                if (currentTime3 - lastClickTime > MyAppConfig.MIN_CLICK_DELAY_TIME) {
                    //保存点击记录
                    lastClickTime = currentTime3;
                    LoanListActivity.createActivity(getActivity(), "放款快");
                }
                break;
            case R.id.tv_handle_card://我要办卡
                long  currentTime4 = Calendar.getInstance().getTimeInMillis();
                if (currentTime4 - lastClickTime > MyAppConfig.MIN_CLICK_DELAY_TIME) {
                    //保存点击记录
                    lastClickTime = currentTime4;
                    HandleCardActivity.createActivity(getActivity());
                }
                break;
            case R.id.tv_repay_card://帮你还卡
                long  currentTime5 = Calendar.getInstance().getTimeInMillis();
                if (currentTime5 - lastClickTime > MyAppConfig.MIN_CLICK_DELAY_TIME) {
                    //保存点击记录
                    lastClickTime = currentTime5;
                    RepayCardActivity.createActivity(getActivity());
                }
                break;
        }
    }

    @Override
    public void getBannerSuccess(List<BannerBean> list) {
        bannerlist.clear();
        bannerlist = list;
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
        swipeLoan.setRefreshing(false);
        //伪造数据
        adapter.addAll(list);

    }

    @Override
    public void onRefresh() {
        adapter.clear();
        mPresenter.getList(page, size);
        mPresenter.getBanner();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }

    }


}
