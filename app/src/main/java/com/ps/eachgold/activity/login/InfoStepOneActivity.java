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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.activity.MainActivity;
import com.ps.eachgold.bean.UserInfoBean;
import com.ps.eachgold.contract.loan.InfoStepOneContract;
import com.ps.eachgold.presenter.InfoStepOnePresenter;
import com.ps.eachgold.util.IDCard;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.util.T;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InfoStepOneActivity extends BaseActivity implements InfoStepOneContract.View {


    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.rb_sex_man)
    RadioButton rbSexMan;
    @BindView(R.id.rb_sex_woman)
    RadioButton rbSexWoman;
    @BindView(R.id.rg_sex)
    RadioGroup rgSex;
    @BindView(R.id.et_idCard_Num)
    EditText etIdCardNum;
    @BindView(R.id.et_phone)
    TextView etPhone;
    @BindView(R.id.tv_spinner)
    TextView tvId;
    @BindView(R.id.tv_edu)
    TextView tvEdu;
    @BindView(R.id.tv_next)
    TextView tvNext;

    //P层
    private InfoStepOnePresenter mPresenter;
    //身份列表
    private List<String> identitys;
    //教育程度列表
    private List<String> edus;
    //身份-对应数据 默认0
    private int identityID=0;
    //教育程度-对应数据 默认0
    private int edusID=0;
    //传值 （实体类）
    private UserInfoBean bean;
    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, InfoStepOneActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initVariables() {

        bean=new UserInfoBean();

        identitys = new ArrayList<>();
        identitys.add("上班族");
        identitys.add("个体户");
        identitys.add("企业主");
        identitys.add("自由职业者");

        edus = new ArrayList<>();
        edus.add("高中/中专以下");
        edus.add("高中/中专");
        edus.add("大专");
        edus.add("本科");
        edus.add("研究生及以上");
    }

    @Override
    protected void initPresenter() {
        mPresenter = new InfoStepOnePresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_info_step_one;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this);// 沉浸式状态栏
        title.setText("资料填写");
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    if(s.length()<2){
                        T.showShort("姓名不能小于2个字符");
                    }
            }
        });

        String phone= (String) SPutils.get(this,"phone","");
        etPhone.setText(phone);

        //身份证 正确 关联男女
        etIdCardNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==18){
                    String id= s.toString();
                    try {
                        if (!IDCard.IDCardValidate(id)) {
                            T.showShort("身份证不正确");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
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

    @Override
    public void getError(Throwable e) {

    }

    @Override
    public void showMyProgressDialog(String message) {
    }

    @Override
    public void hiddenProgressDialog() {

    }


    public void next() {
        //姓名
        String name = etName.getText().toString().trim();
        if (name == null || "".equals(name)) {
            T.showShort("请输入姓名");
            return;
        }
        //性别
        int sex = 1;
        switch (rgSex.getCheckedRadioButtonId()) {
            case R.id.rb_sex_man:
                sex = 1;
                break;
            case R.id.rb_sex_woman:
                sex = 2;
                break;
        }
        //身份证
        String num = etIdCardNum.getText().toString().trim();
        String idCard="";
        try {
            if (IDCard.IDCardValidate(num)) {
                idCard=num;
            }else {
                if(num==null||"".equals(num)){
                    T.showShort("请输入身份证");
                }else {
                    T.showShort("身份证不正确");
                }
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //身份职业
        if(identityID==0){
            T.showShort("请选择身份职业");
            return;
        }
        //教育程度
        if(edusID==0){
            T.showShort("请选择教育程度");
            return;
        }
        //设置值
        bean.setName(name);
        bean.setSex(sex);
        bean.setIdCard(idCard);
        bean.setIdentityID(identityID);
        bean.setEdusID(edusID);
        //跳转
        Intent intent = new Intent(this, InfoStepTwoActivity.class);
        intent.putExtra("userInfo", bean);
        startActivityForResult(intent, 0);
    }


    @OnClick({R.id.left_icon, R.id.tv_edu, R.id.tv_next, R.id.tv_spinner})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                //返回
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
                MainActivity.createActivity(this,0);
                break;
            case R.id.tv_spinner:

                OptionsPickerView iidentitysOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = identitys.get(options1);

                        tvId.setText(tx);
                        tvId.setTextColor(ContextCompat.getColor(InfoStepOneActivity.this,R.color.black));
                        identityID=options1+1;
                    }
                }).build();
                iidentitysOptions.setPicker(identitys);
                iidentitysOptions.show();
                break;
            case R.id.tv_edu:
                OptionsPickerView eduOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = edus.get(options1);
                        tvEdu.setText(tx);
                        tvEdu.setTextColor(ContextCompat.getColor(InfoStepOneActivity.this,R.color.black));
                        edusID=options1+1;
                    }
                }).build();
                eduOptions.setPicker(edus);
                eduOptions.show();
                break;
            case R.id.tv_next:
                //传递数据 到第二部
                next();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         bean = (UserInfoBean) data.getExtras().getSerializable("bean");//得到新Activity 关闭后返回的数据
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

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //返回
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
            MainActivity.createActivity(this,0);
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }


}
