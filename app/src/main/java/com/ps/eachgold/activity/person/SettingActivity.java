package com.ps.eachgold.activity.person;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.activity.MainActivity;
import com.ps.eachgold.bean.RxMsgBean;
import com.ps.eachgold.util.RxBus;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.util.T;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class SettingActivity extends BaseActivity {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.tv_change_psw)
    TextView tvChangePsw;

    @BindView(R.id.tv_exit)
    TextView tvExit;

    //Rxbus  使用完解绑
    private Disposable disposable;
    //类型判断  1 修改密码  0 设置密码
    private int mtype;

    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
        //标题
        title.setText("设置");
        //判断类型
        if (mtype == 1) {
            tvChangePsw.setText("密码修改");
        } else if (mtype == 0) {
            tvChangePsw.setText("设置密码");
        }
        initRx();
    }

    private void initRx() {
        disposable =
                RxBus.getInstance()
                        .toObservable(
                                RxMsgBean.class,
                                new Consumer<RxMsgBean>() {
                                    @Override
                                    public void accept(RxMsgBean eventMsg) throws Exception {
                                        if ("update_psw".equals(eventMsg.getMsg())) {
                                            mtype = (int) SPutils.get(SettingActivity.this, "havePsw", 1);
                                            if (mtype == 1) {
                                                tvChangePsw.setText("密码修改");
                                            } else if (mtype == 0) {
                                                tvChangePsw.setText("设置密码");
                                            }
                                        }
                                    }
                                });

    }

    @Override
    public boolean isUseButterKnife() {
        return true;
    }

    @Override
    protected boolean isAllowFullScreen() {
        return false;
    }

    @OnClick({R.id.left_icon, R.id.tv_change_psw, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                //返回
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
                break;
            case R.id.tv_change_psw:
                //密码修改 1/密码设置0
                mtype = (int) SPutils.get(this, "havePsw", 1);
                ChangePswActivity.createActivity(this, mtype);
                break;

            case R.id.tv_exit:
                //退出
                SPutils.deleteButPhone(this);
                T.showShort("退出成功");
                MainActivity.createActivity(this, 0);
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (RxBus.getInstance().isObserver()) {
            RxBus.getInstance().unregister(disposable);
        }
    }

}
