package com.ps.eachgold.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.login.LoginActivity;
import com.ps.eachgold.activity.person.CollectionActivity;
import com.ps.eachgold.activity.person.FeedBackActivity;
import com.ps.eachgold.activity.person.HelpActivity;
import com.ps.eachgold.activity.person.MessageActivity;
import com.ps.eachgold.activity.person.SettingActivity;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LaberBean;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.bean.RxMsgBean;
import com.ps.eachgold.contract.person.PersonContract;
import com.ps.eachgold.presenter.PersonPresenter;
import com.ps.eachgold.util.RxBus;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.util.StatBarCpmpart;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by 8146 on 2018/1/15.
 * 我的 -Fragment
 */

public class PersonFragment extends BaseFragment implements PersonContract.View {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.iv_person_head)
    ImageView ivPersonHead;
    @BindView(R.id.tv_person_id)
    TextView tvPersonId;
    @BindView(R.id.status_bar)
    LinearLayout statusBar;
    @BindView(R.id.st_person_collection)
    SuperTextView stPersonCollection;
    @BindView(R.id.st_person_message)
    SuperTextView stPersonMessage;
    @BindView(R.id.st_person_help)
    SuperTextView stPersonHelp;
    @BindView(R.id.st_person_feedback)
    SuperTextView stPersonFeedback;
    @BindView(R.id.st_person_setting)
    SuperTextView stPersonSetting;
    Unbinder unbinder1;
    @BindView(R.id.tv_person_info_show)
    TextView tvPersonInfoShow;
    @BindView(R.id.tv_person_id_num)
    TextView tvPersonIdNum;
    @BindView(R.id.tv_person_please_login)
    TextView tvPersonPleaseLogin;

    //
    private String phone;
    //登录状态
    private Boolean loginFlag = true;
    //资料填写状态
    private Boolean infoFlag = true;

    //未读消息数量
    private String num;
    //p层
    private PersonPresenter mPresenter;

    //用于RxBus的内存回收
    private Disposable disposable;

    public static PersonFragment newInstance() {
        PersonFragment fragment = new PersonFragment();
        return fragment;

    }

    @Override
    protected void initVariables() {
        phone = (String) SPutils.get(getActivity(), "phone", "");
        loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
        infoFlag = (boolean) SPutils.get(getActivity(), "info", false);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void initView(Bundle savedInstanceState) {
        // 沉浸式状态栏
        StatBarCpmpart.init(getActivity(), statusBar);
        //隐藏
        leftIcon.setVisibility(View.GONE);
        //设置标题
        title.setText(R.string.person_title);
        //设置右ICON
        rightIcon.setVisibility(View.GONE);
        //未登录 隐藏id和info按钮
        /*tvPersonId.setVisibility(View.INVISIBLE);
        tvPersonIdNum.setVisibility(View.INVISIBLE);*/

        //登录
        loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
        //资料完善状态
        infoFlag = (boolean) SPutils.get(getActivity(), "info", false);
        /*if (loginFlag) {
            tvPersonId.setText("ID:" + phone);
            if (infoFlag) {
                tvPersonMore.setVisibility(View.VISIBLE);
                linNoCustom.setVisibility(View.GONE);
                linCustom.setVisibility(View.VISIBLE);
            } else {
                tvPersonMore.setVisibility(View.GONE);
                linNoCustom.setVisibility(View.VISIBLE);
                linCustom.setVisibility(View.GONE);
            }
            tvPersonLoginType.setText("已登录");
            ;
        } else {
            tvPersonId.setText("");
            tvPersonMore.setVisibility(View.GONE);
            tvPersonLoginType.setText("前去登录");
            linNoCustom.setVisibility(View.VISIBLE);
            linCustom.setVisibility(View.GONE);

        }*/

        initRx();
    }

    //RxBus 事件  总线
    private void initRx() {
        disposable =
                RxBus.getInstance()
                        .toObservable(
                                RxMsgBean.class,
                                new Consumer<RxMsgBean>() {
                                    @Override
                                    public void accept(RxMsgBean eventMsg) throws Exception {
                                        if ("update_login".equals(eventMsg.getMsg())) {
                                            loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
                                            infoFlag = (boolean) SPutils.get(getActivity(), "info", false);
                                            if (loginFlag) {
                                                tvPersonId.setText("ID:" + phone);
                                                /*if (infoFlag) {
                                                    tvPersonMore.setVisibility(View.VISIBLE);
                                                    linNoCustom.setVisibility(View.GONE);
                                                    linCustom.setVisibility(View.VISIBLE);
                                                } else {
                                                    tvPersonMore.setVisibility(View.GONE);
                                                    linNoCustom.setVisibility(View.VISIBLE);
                                                    linCustom.setVisibility(View.GONE);
                                                }
                                                tvPersonLoginType.setText("已登录");*/

                                            } else {
                                                tvPersonId.setText("");
                                                /*tvPersonMore.setVisibility(View.GONE);
                                                tvPersonLoginType.setText("前去登录");
                                                linNoCustom.setVisibility(View.VISIBLE);
                                                linCustom.setVisibility(View.GONE);*/

                                            }
                                        }
                                    }
                                });

    }

    @Override
    protected void initPresenter() {
        mPresenter = new PersonPresenter(getActivity(), this);
    }

    @Override
    protected void loadData() {
        if (loginFlag && infoFlag) {
            //3个专属产品
            mPresenter.getMyExclusive();
            //查看更多标签
            mPresenter.getLabel();

        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main_person;
    }

    @Override
    public boolean isUseBuffer() {
        return true;
    }


    @OnClick({R.id.tv_person_info_show, R.id.right_icon, R.id.iv_person_head, R.id.st_person_collection, R.id.st_person_message, R.id.st_person_help, R.id.st_person_feedback, R.id.st_person_setting,R.id.tv_person_please_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_person_please_login:
                LoginActivity.createActivity(getActivity());
                break;
            case R.id.iv_person_head:
                //头像
                break;
            case R.id.tv_person_info_show:
                //跳转资料填写 如果资料填写完直接跳转展示，若没有，跳转资料填写

                break;
            case R.id.st_person_collection:
                //收藏页
                CollectionActivity.createActivity(getActivity());
                break;
            case R.id.st_person_message:
                //消息中心
                MessageActivity.createActivity(getActivity());
                /*loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
                if (loginFlag) {
                    if (infoFlag) {
                        T.showShort("message");
                        MessageActivity.createActivity(getActivity(),0);
                    } else {
                        //资料完善
                        InfoStepOneActivity.createActivity(getActivity());
                    }
                }*/
                break;
            case R.id.st_person_help:
                //帮助中心
                HelpActivity.createActivity(getActivity());
                break;
            case R.id.st_person_feedback:
                //意见反馈
                FeedBackActivity.createActivity(getActivity());
                /*loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
                if (loginFlag) {
                    if (infoFlag) {
                        T.showShort("feedback");
                        FeedBackActivity.createActivity(getActivity());
                    } else {
                        //资料完善
                        InfoStepOneActivity.createActivity(getActivity());
                    }
                }*/
                break;
            case R.id.st_person_setting:
                //设置
                SettingActivity.createActivity(getActivity());
                break;
            /*case R.id.tv_person_login_type:
                loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
                infoFlag = (boolean) SPutils.get(getActivity(), "info", false);
                //登录
                if (loginFlag) {

                } else {
                    LoginActivity.createActivity(getActivity(), "");
                }

                break;



            case R.id.tv_feedback:
                //意见反馈
                loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
                if (loginFlag) {
                    if (infoFlag) {
                        FeedBackActivity.createActivity(getActivity());
                    } else {
                        //资料完善
                        InfoStepOneActivity.createActivity(getActivity());
                    }
                }
                break;
            case R.id.tv_message:
                loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
                infoFlag = (boolean) SPutils.get(getActivity(), "info", false);
                if (loginFlag) {
                    if (infoFlag) {
                        mPresenter.getMsgCount();
                    } else {
                        //资料完善
                        InfoStepOneActivity.createActivity(getActivity());
                    }
                } else {
                    LoginActivity.createActivity(getActivity(), "");
                }
                break;*/
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (RxBus.getInstance().isObserver()) {
            RxBus.getInstance().unregister(disposable);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

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
    public void getMyExclusiveSuccess(List<LoanBean> list, Header header) {
        String baseUrl = (String) SPutils.get(getContext(), "baseImgUrl", "");

        if (list.size() >= 1) {
           /* GlideApp.with(getContext()).load("http:" + baseUrl + list.get(0).getPic())
                    .into(ivExclusive1);
            tvExclusiveName1.setText(list.get(0).getName());*/
            /*String min = String.valueOf(list.get(0).getMinMoney());
            String max = String.valueOf(list.get(0).getMaxMoney());*/

            // tvExclusiveMoney1.setText(getMinMax(MyUtil.formatToseparano0(min)) + "-" + getMinMax(MyUtil.formatToseparano0(max)));

            //clickUrl1 = list.get(0).getUrl();
        }
        if (list.size() >= 2) {
           /* GlideApp.with(getContext()).load("http:" + baseUrl + list.get(1).getPic())
                    .into(ivExclusive2);

            tvExclusiveName2.setText(list.get(1).getName());*/
           /* String min2 = String.valueOf(list.get(1).getMinMoney());
            String max2 = String.valueOf(list.get(1).getMaxMoney());*/
            //tvExclusiveMoney2.setText(getMinMax(MyUtil.formatToseparano0(min2)) + "-" + getMinMax(MyUtil.formatToseparano0(max2)));
            // clickUrl2 = list.get(1).getUrl();

        }

        if (list.size() >= 3) {

           /* GlideApp.with(getContext()).load("http:" + baseUrl + list.get(2).getPic())
                    .into(ivExclusive3);*/


            //tvExclusiveName3.setText(list.get(2).getName());
            /*String min3 = String.valueOf(list.get(2).getMinMoney());
            String max3 = String.valueOf(list.get(2).getMaxMoney());
*/
            //tvExclusiveMoney3.setText(getMinMax(MyUtil.formatToseparano0(min3)) + "-" + getMinMax(MyUtil.formatToseparano0(max3)));


            //clickUrl3 = list.get(2).getUrl();
        }


    }

    @Override
    public void getLabelSuccess(List<LaberBean> list) {
        //查看更多标签

    }

    @Override
    public void getMsg(String count) {
        num = count;
        //MessageActivity.createActivity(getActivity(), Integer.parseInt(num));
        MessageActivity.createActivity(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
