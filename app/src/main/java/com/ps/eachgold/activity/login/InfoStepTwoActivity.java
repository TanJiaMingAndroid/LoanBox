package com.ps.eachgold.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.bean.UserInfoBean;
import com.ps.eachgold.contract.loan.InfoStepTwoContract;
import com.ps.eachgold.presenter.InfoStepTwoPresenter;
import com.ps.eachgold.util.T;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InfoStepTwoActivity extends BaseActivity implements InfoStepTwoContract.View {


    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.rb_card_1)
    RadioButton rbCard1;
    @BindView(R.id.rb_card_0)
    RadioButton rbCard0;
    @BindView(R.id.rg_card)
    RadioGroup rgCard;
    @BindView(R.id.tv_social_security)
    TextView tvSocialSecurity;
    @BindView(R.id.tv_fund)
    TextView tvFund;
    @BindView(R.id.tv_car_production)
    TextView tvCarProduction;
    @BindView(R.id.tv_house_production)
    TextView tvHouseProduction;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.et_card_num)
    EditText etCardNum;
    @BindView(R.id.tv_card_high)
    TextView tvCardHigh;
    @BindView(R.id.lin_credit_card)
    LinearLayout linCreditCard;

    //P层
    private InfoStepTwoPresenter mPresenter;
    //用户信息表
    private UserInfoBean bean;
    //有无信用卡 2 无  1有
    private int creditFlag = 2;
    //月收入列表
    private List<String> MonthIncomeList;
    //月收入列表对应参数ID
    private int monthId = 0;
    //信用卡张数
    private int creditCount = 0;
    //信用卡额度表
    private List<String> CreditLinesList;
    //信用卡对应参数ID；
    private int creditId = 0;
    //社保列表
    private List<String> SocialSecurityList;
    //社保列表对应参数ID；
    private int socialId = 0;
    //公积金列表
    private List<String> FundList;
    //公积金列表对应参数ID；
    private int fundId = 0;
    //车产列表
    private List<String> CarProductionList;
    //车产列表对应参数ID；
    private int carId = 0;
    //房产列表
    private List<String> HouseProductionList;
    //房产列表对应参数ID；
    private int houseId = 0;

    private Intent intent;

    //跳转
    public static void createActivity(Context context, UserInfoBean bean) {
        Intent intent = new Intent(context, InfoStepTwoActivity.class);
        intent.putExtra("userInfo", bean);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        intent = getIntent();
        bean = (UserInfoBean) intent.getSerializableExtra("userInfo");
        MonthIncomeList = new ArrayList<>();
        MonthIncomeList.add("3000以下");
        MonthIncomeList.add("3000-5000");
        MonthIncomeList.add("5000-10000");
        MonthIncomeList.add("10000-15000");
        MonthIncomeList.add("15000以上");

        CreditLinesList = new ArrayList<>();
        CreditLinesList.add("5000以下");
        CreditLinesList.add("5000-1万");
        CreditLinesList.add("1万-3万");
        CreditLinesList.add("3万-5万");
        CreditLinesList.add("5万-10万");
        CreditLinesList.add("10万以上");

        SocialSecurityList = new ArrayList<>();
        SocialSecurityList.add("无");
        SocialSecurityList.add("有 本地社保");
        SocialSecurityList.add("有 外地社保");

        FundList = new ArrayList<>();
        FundList.add("无");
        FundList.add("有 本地公积金");
        FundList.add("有 外地公积金");

        HouseProductionList = new ArrayList<>();
        HouseProductionList.add("无");
        HouseProductionList.add("准备购买");
        HouseProductionList.add("有房 无贷款");
        HouseProductionList.add("有房 按揭贷款");
        HouseProductionList.add("有房 已被抵押");

        CarProductionList = new ArrayList<>();
        CarProductionList.add("无");
        CarProductionList.add("准备购买");
        CarProductionList.add("有车 无贷款");
        CarProductionList.add("有车 按揭贷款");
        CarProductionList.add("有车 但已抵押");


    }

    @Override
    protected void initPresenter() {
        mPresenter = new InfoStepTwoPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_info_step_two;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        title.setText("资料填写");
        //选择有信用卡 才显示（默认无-隐藏）
        linCreditCard.setVisibility(View.GONE);

        rgCard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_card_1:
                        linCreditCard.setVisibility(View.VISIBLE);
                        creditFlag = 1;
                        bean.setCreditFlag(creditFlag);
                        break;
                    case R.id.rb_card_0:
                        linCreditCard.setVisibility(View.GONE);
                        creditFlag = 2;
                        bean.setCreditFlag(creditFlag);
                        break;
                }
            }
        });
        etCardNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String a = s.toString();
                creditCount = Integer.valueOf(a);
                bean.setCreditCount(creditCount);
            }
        });

        //从第二步返回第一步修改，再返回第二步时， 获取第一步传过来的之前第二步的保留数据 进行赋值
        if (bean.getMonthId() != 0) {
            tvMonth.setText(MonthIncomeList.get(bean.getMonthId() - 1));
            tvMonth.setTextColor(ContextCompat.getColor(this, R.color.black));
        }
        if (bean.getCreditFlag() != 0) {
            if (bean.getCreditFlag() == 1) {
                rbCard1.setChecked(true);
            } else if (bean.getCreditFlag() == 2) {
                rbCard0.setChecked(true);
            }
        } else {
            rbCard0.setChecked(true);
        }
        if (bean.getCreditCount() != 0) {
            etCardNum.setText(String.valueOf(bean.getCreditCount()));
            etCardNum.setTextColor(ContextCompat.getColor(this, R.color.black));
        }
        if (bean.getCreditId() != 0) {
            tvCardHigh.setText(CreditLinesList.get(bean.getCreditId() - 1));
            tvCardHigh.setTextColor(ContextCompat.getColor(this, R.color.black));
        }
        if (bean.getSocialId() != 0) {
            tvSocialSecurity.setTextColor(ContextCompat.getColor(this, R.color.black));
            if (bean.getSocialId() == -1) {
                tvSocialSecurity.setText(SocialSecurityList.get(0));
            } else {
                tvSocialSecurity.setText(SocialSecurityList.get(bean.getSocialId()));
            }
        }
        if (bean.getFundId() != 0) {
            tvFund.setTextColor(ContextCompat.getColor(this, R.color.black));
            if (bean.getFundId() == -1) {
                tvFund.setText(FundList.get(0));
            } else {
                tvFund.setText(FundList.get(bean.getFundId()));
            }

        }
        if (bean.getHouseId() != 0) {
            tvHouseProduction.setTextColor(ContextCompat.getColor(this, R.color.black));
            if (bean.getHouseId() == -1) {
                tvHouseProduction.setText(HouseProductionList.get(0));
            } else {
                tvHouseProduction.setText(HouseProductionList.get(bean.getHouseId()));
            }

        }
        if (bean.getCarId() != 0) {
            tvCarProduction.setTextColor(ContextCompat.getColor(this, R.color.black));
            if (bean.getCarId() == -1) {
                tvCarProduction.setText(CarProductionList.get(0));
            } else {
                tvCarProduction.setText(CarProductionList.get(bean.getCarId()));
            }

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
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            intent.putExtra("bean", bean);
            setResult(RESULT_OK, intent);
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @OnClick({R.id.left_icon, R.id.tv_month, R.id.tv_card_high, R.id.tv_social_security, R.id.tv_fund, R.id.tv_car_production, R.id.tv_house_production, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                intent.putExtra("bean", bean);
                setResult(RESULT_OK, intent);
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
                break;
            case R.id.tv_month:
                //月收入
                OptionsPickerView month = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = MonthIncomeList.get(options1);
                        tvMonth.setText(tx);
                        tvMonth.setTextColor(ContextCompat.getColor(InfoStepTwoActivity.this, R.color.black));
                        monthId = options1 + 1;
                        bean.setMonthId(monthId);

                    }
                }).build();
                month.setPicker(MonthIncomeList);
                month.show();
                break;
            case R.id.tv_card_high:
                //信用卡
                OptionsPickerView card = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = CreditLinesList.get(options1);
                        tvCardHigh.setText(tx);
                        tvCardHigh.setTextColor(ContextCompat.getColor(InfoStepTwoActivity.this, R.color.black));
                        creditId = options1 + 1;
                        bean.setCreditId(creditId);

                    }
                }).build();
                card.setPicker(CreditLinesList);
                card.show();
                break;
            case R.id.tv_social_security:
                OptionsPickerView social = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = SocialSecurityList.get(options1);
                        tvSocialSecurity.setText(tx);
                        tvSocialSecurity.setTextColor(ContextCompat.getColor(InfoStepTwoActivity.this, R.color.black));
                        if (options1 == 0) {
                            socialId = -1;
                        } else {
                            socialId = options1;
                        }
                        bean.setSocialId(socialId);
                    }
                }).build();
                social.setPicker(SocialSecurityList);
                social.show();
                break;
            case R.id.tv_fund:
                OptionsPickerView fund = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = FundList.get(options1);
                        tvFund.setText(tx);
                        tvFund.setTextColor(ContextCompat.getColor(InfoStepTwoActivity.this, R.color.black));
                        if (options1 == 0) {
                            fundId = -1;
                        } else {
                            fundId = options1;
                        }
                        bean.setFundId(fundId);
                    }
                }).build();
                fund.setPicker(FundList);
                fund.show();
                break;
            case R.id.tv_car_production:
                OptionsPickerView car = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = CarProductionList.get(options1);
                        tvCarProduction.setText(tx);
                        tvCarProduction.setTextColor(ContextCompat.getColor(InfoStepTwoActivity.this, R.color.black));
                        if (options1 == 0) {
                            carId = -1;
                        } else {
                            carId = options1;
                        }
                        bean.setCarId(carId);
                    }
                }).build();
                car.setPicker(CarProductionList);
                car.show();
                break;
            case R.id.tv_house_production:
                final OptionsPickerView house = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = HouseProductionList.get(options1);
                        tvHouseProduction.setText(tx);
                        tvHouseProduction.setTextColor(ContextCompat.getColor(InfoStepTwoActivity.this, R.color.black));
                        if (options1 == 0) {
                            houseId = -1;
                        } else {
                            houseId = options1;
                        }
                        bean.setHouseId(houseId);

                    }
                }).build();
                house.setPicker(HouseProductionList);
                house.show();
                break;
            case R.id.tv_next:
                next();

                break;
        }
    }

    private void next() {

        //月收入
        if (bean.getMonthId() == 0 && monthId == 0) {
            T.showShort("请选择月收入");
            return;
        }
        //信用卡
        if (bean.getCreditFlag() == 0 && creditFlag == 1) {

            String count = etCardNum.getText().toString().trim();
            if (count == null || "".equals(count)) {
                T.showShort("请输入信用卡张数");
                return;
            }
            if (creditId == 0) {
                T.showShort("请选择信用卡额度");
                return;
            }
            creditCount = Integer.valueOf(count);
        }
        //社保
        if (bean.getSocialId() == 0 && socialId == 0) {
            T.showShort("请选择社保情况");
            return;
        }
        //公积金
        if (bean.getFundId() == 0 && fundId == 0) {
            T.showShort("请选择公积金情况");
            return;
        }
        //房产
        if (bean.getHouseId() == 0 && houseId == 0) {
            T.showShort("请选择房产情况");
            return;
        }
        //车产
        if (bean.getCarId() == 0 && carId == 0) {
            T.showShort("请选择车产情况");
            return;
        }
        if(monthId!=0){
            bean.setMonthId(monthId);
        }
       if(creditFlag!=0){
           bean.setCreditFlag(creditFlag);
       }
       if(creditCount!=0){
           bean.setCreditCount(creditCount);
       }
       if(creditId!=0){
           bean.setCreditId(creditId);
       }
       if(socialId!=0){
           bean.setSocialId(socialId);
       }
       if(fundId!=0){
           bean.setFundId(fundId);
       }
       if(houseId!=0){
           bean.setHouseId(houseId);
       }
       if(carId!=0){
           bean.setCarId(carId);
       }
        InfoStepThreeActivity.createActivity(this, bean);
    }


}
