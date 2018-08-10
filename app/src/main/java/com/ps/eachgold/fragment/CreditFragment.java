package com.ps.eachgold.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkingliang.banner.CustomBanner;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.ps.eachgold.MyAppConfig;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.H5Activity;
import com.ps.eachgold.activity.credit.BankCreditListActivity;
import com.ps.eachgold.activity.credit.BankHotListActivity;
import com.ps.eachgold.activity.credit.CardPregressActivity;
import com.ps.eachgold.activity.login.InfoStepOneActivity;
import com.ps.eachgold.activity.login.LoginActivity;
import com.ps.eachgold.bean.BankBean;
import com.ps.eachgold.bean.BankCardBean;
import com.ps.eachgold.bean.BannerBean;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.contract.main.CreditContract;
import com.ps.eachgold.popupWindow.WaittingProgressPop;
import com.ps.eachgold.presenter.credit.CreditPresenter;
import com.ps.eachgold.util.DpPxUtil;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.util.StatBarCpmpart;
import com.ps.eachgold.viewHold.FragmentCreditHeadViewHolder;
import com.ps.eachgold.viewHold.FragmentCreditViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 8146 on 2018/1/15.
 * daik Fragment
 */

public class CreditFragment extends BaseFragment implements CreditContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_credit)
    EasyRecyclerView recyclerCredit;
    @BindView(R.id.swipe_credit)
    SwipeRefreshLayout swipeCredit;
    @BindView(R.id.status_bar)
    LinearLayout statusBar;
    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    Unbinder unbinder;

    //list适配器-卡片
    private RecyclerArrayAdapter<BankCardBean> creditAdapter;
    //list头部View
    private View viewHeader;
    //list尾部View
    private View viewFooter;
    //Header banner
    private CustomBanner banner;
    // Header 圆形 银行的列表
    private EasyRecyclerView recyclerHead;
    //查看办卡进度
    private RelativeLayout cardProgress;
    //头部 银行 查看更多
    private LinearLayout linMore;
    //查看更多
    private TextView tvMore;
    //银行适配器-圆形银行
    private RecyclerArrayAdapter<BankBean> bankAdapter;
    //银行只显示6
    private List<BankBean> bankListSix;
    //银行所有
    private List<BankBean> bankList;
    //银行卡列表页面
    private int page = 1;
    //单银行卡列表页面展示数
    private int size = 3;
    //p层
    private CreditPresenter mPresenter;
    //登录状态
    private Boolean loginFlag;
    //资料填写状态
    private Boolean infoFlag;
    //图片默认域名
    private String baseImgUrl;
    //进度框
    private WaittingProgressPop waittingProgressPop;
    //防止快速点击
    private long lastClickTime = 0;//点击的时间
    // banner 的数据
    private List<BannerBean> bannerlist;
    //默认状态下 无banner 时 只显示一二
    private ImageView ivOneBanner;
    String [] sort = {"Semua","Jumlah pinjaman tinggi","Suku bunga rendah","Waktu pinjaman panjang"};

    //
    public static CreditFragment newInstance() {
        CreditFragment fragment = new CreditFragment();
        return fragment;
    }

    @Override
    protected void initVariables() {
        page = 1;
        size = 3;
        bankList = new ArrayList<>();
        bankListSix = new ArrayList<>();
        bannerlist = new ArrayList<>();
        baseImgUrl = (String) SPutils.get(getActivity(), "baseImgUrl", "");
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
        //初始化头部银行列表
        initHeadRecycler();
        //添加椒图
        addFoot();
        //添加监听
        addListener();
        for (int i = 0; i < sort.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(sort[i]);
            // tab.setIcon(R.mipmap.ic_launcher);//icon会显示在文字上面
            tabLayout.addTab(tab);
        }


    }

    @Override
    protected void initPresenter() {
        mPresenter = new CreditPresenter(getActivity(), this);
    }

    @Override
    protected void loadData() {
        //Banner
        mPresenter.getBanner();
        //银行列表
        mPresenter.getBankList();
        // 银行卡列表
        mPresenter.getCardList(page, size);
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
        recyclerCredit.setAdapterWithProgress(creditAdapter = new RecyclerArrayAdapter<BankCardBean>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new FragmentCreditViewHolder(parent);
            }
        });

        recyclerCredit.getRecyclerView().setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void addHead() {

        bankAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
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
                Glide.with(context).load(data).into((ImageView) view);
            }
        }, images).startTurning(3000);

    }

    private void initBanner(List<BannerBean> list) {
        ArrayList<String> images = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
           // images.add(list.get(i).getPic());
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
                //data 轮播图当前项对应的数据

                String baseUrl = (String) SPutils.get(getContext(), "baseImgUrl", "");

               /* GlideApp.with(context).load("http:" + baseUrl + data)
                        .placeholder(R.mipmap.banner_glide)
                        .error(R.mipmap.banner_glide)
                        .into((ImageView) view);*/
            }
        }, images).startTurning(3000);

    }

    private void initHeadRecycler() {
        viewHeader = LayoutInflater.from(getActivity()).inflate(R.layout.head_fragment_credit, null);
        banner = viewHeader.findViewById(R.id.banner);
        recyclerHead = viewHeader.findViewById(R.id.recycler_head);
        cardProgress = viewHeader.findViewById(R.id.rl_card_progress);//办卡进度
        linMore = viewHeader.findViewById(R.id.lin_more);//查看更多
        ivOneBanner = viewHeader.findViewById(R.id.iv_one_banner);//只有一个banner时
        recyclerHead.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerHead.setAdapterWithProgress(bankAdapter = new RecyclerArrayAdapter<BankBean>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new FragmentCreditHeadViewHolder(parent, 1);
            }
        });

    }

    @Override
    public void addFoot() {
        viewFooter = LayoutInflater.from(getActivity()).inflate(R.layout.foot_fragment_credit, recyclerCredit, false);
        tvMore = viewFooter.findViewById(R.id.tv_credit_more); //查看更多

        creditAdapter.addFooter(new RecyclerArrayAdapter.ItemView() {
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
        //banner  切换
        banner.setOnPageClickListener(new CustomBanner.OnPageClickListener<String>() {
            @Override
            public void onPageClick(int position, String str) {
                //position 轮播图的第几个项
                //str 轮播图当前项对应的数据
            }
        });

        bankAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                long currentTime = Calendar.getInstance().getTimeInMillis();
                if (currentTime - lastClickTime > MyAppConfig.MIN_CLICK_DELAY_TIME) {
                    //保存点击记录
                    lastClickTime = currentTime;
                    String bankid = String.valueOf(bankAdapter.getAllData().get(position).getId());
                    String type = "bank";
                    mPresenter.saveLog(type, bankid);
                    //银行跳转
                    String name = bankAdapter.getAllData().get(position).getBank();
                    int id = bankAdapter.getAllData().get(position).getId();
                    String url = bankAdapter.getAllData().get(position).getQueryUrl();
                    BankCreditListActivity.createActivity(getActivity(), name, String.valueOf(id), url);
                }

            }
        });
        // 银行默认 收缩状态下 的 查看更多
        linMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看更多银行
                linMore.setVisibility(View.GONE);
                //加载全部
                bankAdapter.clear();
                bankAdapter.addAll(bankList);
            }
        });
        cardProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //办卡进度查询  需要判断登录？
                CardPregressActivity.createActivity(getActivity(), "");
            }
        });
        creditAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
                infoFlag = (boolean) SPutils.get(getActivity(), "info", false);
                // 热门卡跳转
                if (loginFlag) {
                    if (infoFlag) {
                        //保存点击记录
                        String id = String.valueOf(creditAdapter.getAllData().get(position).getId());
                        String type = "Card";
                        mPresenter.saveLog(type, id);
                        //跳链接
                        String mUrl = creditAdapter.getAllData().get(position).getUrl();
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
        //页面底部的查看更多
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //底部查看更多跳转
                BankHotListActivity.createActivity(getActivity());
            }
        });
        //下拉刷新
        swipeCredit.setOnRefreshListener(this);

        banner.setOnPageClickListener(new CustomBanner.OnPageClickListener<String>() {
            @Override
            public void onPageClick(int position, String str) {
                //position 轮播图的第几个项
                //str 轮播图当前项对应的数据
                if (bannerlist.get(position).getImgUrl() != null && !"".equals(bannerlist.get(position).getImgUrl())) {
                    H5Activity.createActivity(getActivity(), bannerlist.get(position).getImgUrl(), "");
                }
            }
        });
    }

    @Override
    public void getBannerSuccess(List<BannerBean> list) {
        bannerlist.clear();
        bannerlist = list;
        if (list != null && list.size() > 0) {
            initBanner(list);
            if (list.size() == 1) {
                ivOneBanner.setVisibility(View.VISIBLE);
                banner.setVisibility(View.GONE);
                String baseUrl = (String) SPutils.get(getContext(), "baseImgUrl", "");
                /*GlideApp.with(getActivity()).load("http:" + baseUrl + list.get(0).getPic())
                        .placeholder(R.mipmap.banner_glide)
                        .error(R.mipmap.banner_glide)
                        .into(ivOneBanner);*/
            } else {
                ivOneBanner.setVisibility(View.GONE);
                banner.setVisibility(View.VISIBLE);
            }
        } else {
            initBanner2();
        }
    }

    @Override
    public void getBankListSuccess(List<BankBean> list) {
        bankList.clear();
        bankListSix.clear();
        bankList = list;
        for (int i = 0; i < 6; i++) {
            bankListSix.add(list.get(i));
        }
        bankAdapter.clear();//因为在头部 出现 嵌套问题 卸载这里
        bankAdapter.addAll(bankListSix);

    }


    @Override
    public void getBankCardListSuccess(List<BankCardBean> list, Header header) {
        swipeCredit.setRefreshing(false);
        creditAdapter.addAll(list);
        tvMore.setVisibility(View.VISIBLE);
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
        mPresenter.getBankList();
        mPresenter.getBanner();
        mPresenter.getCardList(page, size);
        //刷新时复原
        linMore.setVisibility(View.VISIBLE);
        tvMore.setVisibility(View.GONE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
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
