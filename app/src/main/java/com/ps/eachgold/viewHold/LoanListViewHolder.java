package com.ps.eachgold.viewHold;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.ps.eachgold.R;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.util.GlideApp;
import com.ps.eachgold.util.MyUtil;
import com.ps.eachgold.util.SPutils;

/**
 * Created by 8146 on 2018/1/18.
 */

public class LoanListViewHolder extends BaseViewHolder<LoanBean> {


    ImageView ivImage;

    TextView tvName;

    TextView tvNameContent;

    TextView tvElementsMain;

    TextView tvElementsMainValue;

    TextView tvApply;

    TextView tvType;

    TextView tvElements1;

    TextView tvElements1Value;

    TextView tvElements2;

    TextView tvElements2Value;

    String type;

    public LoanListViewHolder(ViewGroup parent, String mtype) {
        super(parent, R.layout.viewholder_loan_list);

        ivImage = $(R.id.iv_image);
        tvName = $(R.id.tv_name);
        tvNameContent = $(R.id.tv_name_content);
        tvElementsMain = $(R.id.tv_elements_main);
        tvElementsMainValue = $(R.id.tv_elements_main_value);
        tvApply = $(R.id.tv_apply);
        tvType = $(R.id.tv_type);
        tvElements1 = $(R.id.tv_elements_1);
        tvElements1Value = $(R.id.tv_elements_1_value);
        tvElements2 = $(R.id.tv_elements_2);
        tvElements2Value = $(R.id.tv_elements_2_value);
        type = mtype;
    }

    @Override
    public void setData(LoanBean data) {
        super.setData(data);
        //图片
        String baseUrl = (String) SPutils.get(getContext(), "baseImgUrl", "");


        GlideApp.with(getContext()).load("http:" + baseUrl + data.getPic())
                .placeholder(R.mipmap.credit_glide)
                .error(R.mipmap.credit_glide)
                .into(ivImage);

        //名字
        tvName.setText(data.getName());
        //内容
        tvNameContent.setText(data.getPresentation());
        String mortgage = "无";
        switch (data.getMortgage()) {
            case 1:
                mortgage = "车抵";
                break;
            case 2:
                mortgage = "房抵";
                break;
            case 4:
                mortgage = "无";
                break;
        }
        tvType.setText(mortgage);

        String lendingTime = String.valueOf(data.getLendingTime());
        if (data.getLendingTime() < 1) {
            lendingTime = MyUtil.formatToseparano0(String.valueOf(data.getLendingTime() * 60)) + "分钟";
        } else if (data.getLendingTime() >= 1 && data.getLendingTime() < 24) {
            lendingTime = MyUtil.formatToseparano0(String.valueOf(data.getLendingTime())) + "小时";
        } else if (data.getLendingTime() >= 24) {
            lendingTime = MyUtil.formatToseparano0(String.valueOf(data.getLendingTime() / 24)) + "小时";
        }
        String interest = "";

        String min = String.valueOf(data.getMinMoney());
        String max = String.valueOf(data.getMaxMoney());
        if (min != null && !"".equals(min)) {
            if (min.length() > 4) {
                min = min.substring(0, 1) + "万";
            }
        } else {
            min = "";
        }
        if (max != null && !"".equals(max)) {
            if (max.length() > 4) {
                max = max.substring(0, 1) + "万";
            }
        } else {
            max = "";
        }
        String money = min + "-" + max;
        if (data.getMinInterest() == data.getMaxInterest()) {
            interest = data.getMinInterest() + "%";
        } else {
            interest = data.getMinInterest() + "%-" + data.getMaxInterest() + "%";
        }


        switch (type) {
            case "额度高":
                //主体 额度minMoney-maxMoney
                //副标题    速度lendingTime  利率 minInterest -maxInterest
                tvElementsMain.setText("可贷额度:");
                tvElementsMainValue.setText(money);
                tvElements1.setText("放款速度:");
                tvElements1Value.setText(lendingTime);
                tvElements2.setText("日利率");
                tvElements2Value.setText(interest);

                break;
            case "利息低":
                //主体 利率minInterest -maxInterest
                //副标题  速度lendingTime 额度minMoney-maxMoney
                tvElementsMain.setText("日利率");
                tvElementsMainValue.setText(interest);
                tvElements1.setText("放款速度");
                tvElements1Value.setText(lendingTime);
                tvElements2.setText("可贷额度");
                tvElements2Value.setText(money);
                break;
            case "放款快":
                //主体 速度 速度lendingTime
                //副标题    额度 minMoney-maxMoney  利率 minInterest -maxInterest
                tvElementsMain.setText("放款速度");
                tvElementsMainValue.setText(lendingTime);
                tvElements1.setText("可贷额度");
                tvElements1Value.setText(money);
                tvElements2.setText("日利率");
                tvElements2Value.setText(interest);
                break;
        }

    }
}

