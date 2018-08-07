package com.ps.eachgold.activity.creditReport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.bean.GroupBean;
import com.ps.eachgold.contract.creditReport.ExpandReportContract;
import com.ps.eachgold.viewHold.ExPandlistViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ExpandReportActivity extends BaseActivity implements ExpandReportContract.View {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView rightIcon;

    @BindView(R.id.swipe_report)
    SwipeRefreshLayout swipeReport;
    @BindView(R.id.expand_list)
    ExpandableListView expandList;


    private String type;

    private List<GroupBean> group =null;

    private ExPandlistViewHolder adpter;

    //跳转
    public static void createActivity(Context context, boolean isTranslucent, String type) {
        Intent intent = new Intent(context, ExpandReportActivity.class);
        intent.putExtra("TranslucentType", isTranslucent);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            //  isTranslucent = intent.getBooleanExtra("TranslucentType", true);
            type = intent.getStringExtra("type");
        }
        group = new ArrayList<>();
        List<String> s1 = new ArrayList<>();
        s1.add("1.2016年2月3日招商银行发的贷记卡（美元账户），截止2017年2月，信用额度折合人民币20000元，已用额度0.");
        s1.add("2.2016年2月3日招商银行发的贷记卡（美元账户），截止2017年2月，信用额度折合人民币20000元，已用额度0.");
        s1.add("3.2016年2月3日招商银行发的贷记卡（美元账户），截止2017年2月，信用额度折合人民币20000元，已用额度0.");
        s1.add("4.2016年2月3日招商银行发的贷记卡（美元账户），截止2017年2月，信用额度折合人民币20000元，已用额度0.");
        s1.add("5.2016年2月3日招商银行发的贷记卡（美元账户），截止2017年2月，信用额度折合人民币20000元，已用额度0.");

        GroupBean bean1=new GroupBean("发生过逾期的贷记卡明细","20",s1);
        GroupBean bean2=new GroupBean("透支超过60天准贷记卡账户明细","10",s1);
        GroupBean bean3=new GroupBean("从未逾期的贷记卡及透支未超过60天的准贷记卡账户明细","9",s1);

        group.add(bean1);
        group.add(bean2);
        group.add(bean3);

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_expand_report;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        title.setText(type);
        switch (type) {
            case "信用卡记录":
                title.setText(type);
                break;

        }
        adpter=new ExPandlistViewHolder(this,group);
        expandList.setGroupIndicator(null);
        expandList.setAdapter(adpter);
        for(int i = 0; i < adpter.getGroupCount(); i++){
            expandList.expandGroup(i);
        }
    }



    @Override
    public boolean isUseButterKnife() {
        return true;
    }

    @Override
    protected boolean isAllowFullScreen() {
        return false;
    }


    @OnClick(R.id.left_icon)
    public void onViewClicked() {
        finish();
        overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
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

    @Override
    public void initRecycler() {

    }

    @Override
    public void addListener() {

    }



}
