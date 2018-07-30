package com.ps.eachgold.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.H5Activity;
import com.ps.eachgold.activity.credit.CardPregressActivity;
import com.ps.eachgold.activity.loan.LoanListActivity;
import com.ps.eachgold.activity.login.InfoStepOneActivity;
import com.ps.eachgold.activity.login.LoginActivity;
import com.ps.eachgold.activity.person.FeedBackActivity;
import com.ps.eachgold.activity.person.MessageActivity;
import com.ps.eachgold.activity.person.MoreCustomActivity;
import com.ps.eachgold.activity.person.SettingActivity;
import com.ps.eachgold.bean.Header;
import com.ps.eachgold.bean.LaberBean;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.bean.RxMsgBean;
import com.ps.eachgold.contract.person.PersonContract;
import com.ps.eachgold.presenter.PersonPresenter;
import com.ps.eachgold.util.GlideApp;
import com.ps.eachgold.util.MyUtil;
import com.ps.eachgold.util.RxBus;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.util.StatBarCpmpart;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
    @BindView(R.id.tv_person_login_type)
    TextView tvPersonLoginType;
    @BindView(R.id.tv_person_more)
    TextView tvPersonMore;
    @BindView(R.id.tv_person_custom)
    TextView tvPersonCustom;
    @BindView(R.id.lin_person_exclusive_1)
    LinearLayout linPersonExclusive1;
    @BindView(R.id.lin_person_exclusive_2)
    LinearLayout linPersonExclusive2;
    @BindView(R.id.lin_person_exclusive_3)
    LinearLayout linPersonExclusive3;
    @BindView(R.id.lin_person_report)
    LinearLayout linPersonReport;
    @BindView(R.id.lin_person_credit)
    LinearLayout linPersonCredit;
    @BindView(R.id.lin_person_social)
    LinearLayout linPersonSocial;
    @BindView(R.id.lin_person_fund)
    LinearLayout linPersonFund;
    @BindView(R.id.tv_feedback)
    TextView tvFeedback;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.lin_no_custom)
    LinearLayout linNoCustom;
    @BindView(R.id.lin_custom)
    LinearLayout linCustom;
    @BindView(R.id.status_bar)
    LinearLayout statusBar;
    @BindView(R.id.iv_exclusive_1)
    ImageView ivExclusive1;
    @BindView(R.id.tv_exclusive_name_1)
    TextView tvExclusiveName1;
    @BindView(R.id.tv_exclusive_money_1)
    TextView tvExclusiveMoney1;
    @BindView(R.id.iv_exclusive_2)
    ImageView ivExclusive2;
    @BindView(R.id.tv_exclusive_name_2)
    TextView tvExclusiveName2;
    @BindView(R.id.tv_exclusive_money_2)
    TextView tvExclusiveMoney2;
    @BindView(R.id.iv_exclusive_3)
    ImageView ivExclusive3;
    @BindView(R.id.tv_exclusive_name_3)
    TextView tvExclusiveName3;
    @BindView(R.id.tv_exclusive_money_3)
    TextView tvExclusiveMoney3;
    Unbinder unbinder;

    //
    private String phone;
    //登录状态
    private Boolean loginFlag;
    //资料填写状态
    private Boolean infoFlag;
    //专属标签1
    private String laber1;
    //专属标签2
    private String laber2;
    //未读消息数量
    private String num;
    //p层
    private PersonPresenter mPresenter;
    // 我的界面 专属  推荐 的 3个
    private String clickUrl1;
    private String clickUrl2;
    private String clickUrl3;
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
        title.setText("我的");
        //设置右ICON
        rightIcon.setImageResource(R.mipmap.icon_person_setting);
        //登录
        loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
        //资料完善状态
        infoFlag = (boolean) SPutils.get(getActivity(), "info", false);
        if (loginFlag) {
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

        }

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

    @OnClick({R.id.right_icon, R.id.iv_person_head, R.id.tv_person_login_type, R.id.tv_person_more, R.id.tv_person_custom, R.id.lin_person_exclusive_1, R.id.lin_person_exclusive_2, R.id.lin_person_exclusive_3, R.id.lin_person_report, R.id.lin_person_credit, R.id.lin_person_social, R.id.lin_person_fund, R.id.tv_feedback, R.id.tv_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_icon:
                SettingActivity.createActivity(getActivity());
                //设置
                break;
            case R.id.iv_person_head:
                //头像
                break;
            case R.id.tv_person_login_type:
                loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
                infoFlag = (boolean) SPutils.get(getActivity(), "info", false);
                //登录
                if (loginFlag) {

                } else {
                    LoginActivity.createActivity(getActivity(), "");
                }

                break;
            case R.id.tv_person_more:
                //专属-查看更多
                MoreCustomActivity.createActivity(getContext(), laber1, laber2);
                break;
            case R.id.tv_person_custom:
                loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
                infoFlag = (boolean) SPutils.get(getActivity(), "info", false);
                //专属-定制
                if (loginFlag) {
                    InfoStepOneActivity.createActivity(getActivity());
                } else {
                    LoginActivity.createActivity(getActivity(), "");
                }
                break;
            case R.id.lin_person_exclusive_1:
                //专属 -1
                if (clickUrl1 != null && !"".equals(clickUrl1)) {
                    H5Activity.createActivity(getActivity(), clickUrl1, "");
                }
                break;
            case R.id.lin_person_exclusive_2:
                //专属 -2
                if (clickUrl2 != null && !"".equals(clickUrl2)) {
                    H5Activity.createActivity(getActivity(), clickUrl2, "");
                }
                break;
            case R.id.lin_person_exclusive_3:
                //专属 -3
                if (clickUrl3 != null && !"".equals(clickUrl3)) {
                    H5Activity.createActivity(getActivity(), clickUrl3, "");
                }
                break;
            case R.id.lin_person_report:
                //个人工具-征信查询
                loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
                if (loginFlag) {
                    if (infoFlag) {
                        //String url="https://ipcrs.pbccrc.org.cn/page/login";
                        String url = "https://api.51datakey.com/h5/importV3/index.html#/zhengxin?userId=671d12d5e4b65399628be7b72dc81f15&apiKey=6ec9c57bca7e441887c16fa22bd79aed&backUrl=http%3A%2F%2Fwww.xianjincard.com&showTitleBar=NO&_k=ub5890";
                        H5Activity.createActivity(getActivity(), url, "");
                    } else {
                        //资料完善
                        InfoStepOneActivity.createActivity(getActivity());
                    }

                }
                //  CreditReportActivity.createActivity(getActivity(), true);
                break;
            case R.id.lin_person_credit:
                //个人工具-办卡进度
                loginFlag = (boolean) SPutils.get(getActivity(), "login", false);
                if (loginFlag) {
                    if (infoFlag) {
                        CardPregressActivity.createActivity(getActivity(), "");
                    } else {
                        //资料完善
                        InfoStepOneActivity.createActivity(getActivity());
                    }


                }

                break;
            case R.id.lin_person_social:
                //个人工具-社保查询
                break;
            case R.id.lin_person_fund:
                //个人工具-公积金查询
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
            GlideApp.with(getContext()).load("http:" + baseUrl + list.get(0).getPic())
                    .into(ivExclusive1);
            tvExclusiveName1.setText(list.get(0).getName());
            String min = String.valueOf(list.get(0).getMinMoney());
            String max = String.valueOf(list.get(0).getMaxMoney());

            tvExclusiveMoney1.setText(getMinMax(MyUtil.formatToseparano0(min)) + "-" + getMinMax(MyUtil.formatToseparano0(max)));

            clickUrl1 = list.get(0).getUrl();
        }
        if (list.size() >= 2) {
            GlideApp.with(getContext()).load("http:" + baseUrl + list.get(1).getPic())
                    .into(ivExclusive2);

            tvExclusiveName2.setText(list.get(1).getName());
            String min2 = String.valueOf(list.get(1).getMinMoney());
            String max2 = String.valueOf(list.get(1).getMaxMoney());
            tvExclusiveMoney2.setText(getMinMax(MyUtil.formatToseparano0(min2)) + "-" + getMinMax(MyUtil.formatToseparano0(max2)));
            clickUrl2 = list.get(1).getUrl();

        }

        if (list.size() >= 3) {

            GlideApp.with(getContext()).load("http:" + baseUrl + list.get(2).getPic())
                    .into(ivExclusive3);


            tvExclusiveName3.setText(list.get(2).getName());
            String min3 = String.valueOf(list.get(2).getMinMoney());
            String max3 = String.valueOf(list.get(2).getMaxMoney());

            tvExclusiveMoney3.setText(getMinMax(MyUtil.formatToseparano0(min3)) + "-" + getMinMax(MyUtil.formatToseparano0(max3)));


            clickUrl3 = list.get(2).getUrl();
        }


    }

    @Override
    public void getLabelSuccess(List<LaberBean> list) {
        //查看更多标签
        laber1 = list.get(0).getLabelName();
        laber2 = list.get(1).getLabelName();
    }

    @Override
    public void getMsg(String count) {
        num = count;
        MessageActivity.createActivity(getActivity(), Integer.parseInt(num));
    }

    public String getMinMax(String count) {
        String num = count;

        if (count != null && !"".equals(count)) {
            if (count.length() > 4) {
                num = count.substring(0, 1) + "万";
            }
        } else {
            num = count;
        }

        return num;
    }


}
