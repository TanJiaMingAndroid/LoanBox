package com.ps.eachgold.activity.credit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.activity.H5Activity;
import com.ps.eachgold.activity.login.InfoStepOneActivity;
import com.ps.eachgold.activity.login.LoginActivity;
import com.ps.eachgold.bean.BankBean;
import com.ps.eachgold.bean.BaseBean;
import com.ps.eachgold.contract.credit.CardProgressContract;
import com.ps.eachgold.presenter.CardProgressPresenter;
import com.ps.eachgold.util.DpPxUtil;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.util.T;
import com.ps.eachgold.viewHold.FragmentCreditHeadViewHolder;
import com.ps.eachgold.viewHold.FragmentCreditViewHolder;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by 8146 on 2017/1/12.
 * 办卡进度-页面
 * From-1.CreditFragment-中部-进度
 * 2.PersonFragment-进度查询
 */

public class CardPregressActivity extends BaseActivity implements CardProgressContract.View {


    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycler_card_progress)
    EasyRecyclerView recyclerCardProgress;
    @BindView(R.id.swipe_card_progress)
    SwipeRefreshLayout swipeCardProgress;
    //登录状态
    private Boolean loginFlag;
    //资料填写状态
    private Boolean infoFlag;
    //list适配器
    private RecyclerArrayAdapter<BankBean> adapter;
    //P层
    private CardProgressPresenter mPresenter;
    //跳转
    public static void createActivity(Context context, String type) {
        Intent intent = new Intent(context, CardPregressActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    //初始化变量
    @Override
    protected void initVariables() {

    }

    //初始化P层
    @Override
    protected void initPresenter() {
        mPresenter = new CardProgressPresenter(this, this);
    }

    //加载XML
    @Override
    public int getLayout() {
        return R.layout.activity_card_pregress;
    }

    //初始化视图
    @Override
    protected void initView(Bundle savedInstanceState) {
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        //设置标题
        title.setText("办卡进度查询");
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

    }

    // 加载框的显示隐藏
    @Override
    public void showMyProgressDialog(String message) {

    }

    @Override
    public void hiddenProgressDialog() {

    }

    @Override
    public void initRecycler() {
        recyclerCardProgress.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerCardProgress.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<BankBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new FragmentCreditHeadViewHolder(parent,0);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(3));
        recyclerCardProgress.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void addListener() {
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                loginFlag = (boolean) SPutils.get(CardPregressActivity.this, "login", false);
                infoFlag = (boolean) SPutils.get(CardPregressActivity.this, "info", false);
                if (loginFlag) {
                    if (infoFlag) {
                        //跳链接
                        String mUrl=adapter.getAllData().get(position).getQueryUrl();
                        H5Activity.createActivity(CardPregressActivity.this,mUrl,"");
                    } else {
                        //资料完善
                        InfoStepOneActivity.createActivity(CardPregressActivity.this);
                    }
                } else {
                    //登录
                    LoginActivity.createActivity(CardPregressActivity.this, "");
                }
            }
        });
    }

    @Override
    public void getBankListSuccess(List<BankBean> list) {
        List<BankBean> list2=new ArrayList<>();
        for(int i=0; i<list.size();i++){
            if("中国银行".equals(list.get(i).getBank())){
            }else {
                list2.add(list.get(i));
            }
        }
        adapter.addAll(list2);
    }

    //销毁时解绑防止内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }

    @OnClick(R.id.left_icon)
    public void onViewClicked() {
        //返回
        finish();
        overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
    }
}
