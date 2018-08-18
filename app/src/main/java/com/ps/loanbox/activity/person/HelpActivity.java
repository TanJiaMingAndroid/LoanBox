package com.ps.loanbox.activity.person;/**
 * Created by 8657 on 2018/8/1.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ps.loanbox.R;
import com.ps.loanbox.activity.BaseActivity;
import com.ps.loanbox.adapter.HelpAdapter;
import com.ps.loanbox.bean.HelpBean;
import com.ps.loanbox.contract.person.HelpContract;
import com.ps.loanbox.presenter.person.HelpPresenter;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * creat by tanjiaming at 2018/8/1
 */
public class HelpActivity extends BaseActivity implements HelpContract.View {
    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.lv_help)
    ListView lvHelp;
    @BindView(R.id.tv_help_phone_tip)
    TextView tvHelpPhoneTip;
    private HelpAdapter adapter;
    private HelpPresenter mPresenter;

    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, HelpActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initVariables() {

    }

    @Override
    protected void initPresenter() {
        mPresenter = new HelpPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_person_help;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        title.setText("Pusat Bantu");
        mPresenter.getHelp();

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

    @Override
    public void getHelpSuccess(List<HelpBean> helpBeanList) {
        if (adapter == null) {
            adapter = new HelpAdapter(helpBeanList, this);
            lvHelp.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }



    @OnClick({R.id.left_icon, R.id.tv_help_phone_tip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                finish();
                break;
            case R.id.tv_help_phone_tip:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0216007999"));
                startActivity(intent);
                break;
        }
    }
}
