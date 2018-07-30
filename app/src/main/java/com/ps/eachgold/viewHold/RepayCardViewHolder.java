package com.ps.eachgold.viewHold;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.ps.eachgold.R;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.util.GlideApp;
import com.ps.eachgold.util.MyUtil;
import com.ps.eachgold.util.SPutils;

/**
 * Created by 8146 on 2018/1/18.
 */

public class RepayCardViewHolder extends BaseViewHolder<LoanBean> {


    ImageView ivImage;

    TextView tvName;

    TextView tvNameContent;

    TextView tvElementsMainValue;

    TextView tvApply;

    TextView tvElements1;

    TextView tvElements1Value;

    TextView tvElements2;

    TextView tvElements2Value;

    public RepayCardViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_repay_card);

        ivImage = $(R.id.iv_image);
        tvName = $(R.id.tv_name);
        tvNameContent = $(R.id.tv_name_content);
        tvElementsMainValue = $(R.id.tv_elements_main_value);
        tvApply = $(R.id.tv_apply);

        tvElements1 = $(R.id.tv_elements_1);
        tvElements1Value = $(R.id.tv_elements_1_value);
        tvElements2 = $(R.id.tv_elements_2);
        tvElements2Value = $(R.id.tv_elements_2_value);

    }

    @Override
    public void setData(LoanBean data) {
        super.setData(data);

        String baseUrl = (String) SPutils.get(getContext(), "baseImgUrl", "");

        GlideApp.with(getContext()).load("http:"+baseUrl+data.getPic())
                .placeholder(R.mipmap.credit_glide)
                .error(R.mipmap.credit_glide)
                .into(ivImage);
        tvName.setText(data.getName());

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
        tvElementsMainValue.setText(min + "-" + max);
        tvNameContent.setText(data.getPresentation());


        String lendingTime=String.valueOf(data.getLendingTime());
        if(data.getLendingTime()<1){
            lendingTime= MyUtil.formatToseparano0(String.valueOf(data.getLendingTime()*60))+"分钟";
        }else if(data.getLendingTime()>=1&&data.getLendingTime()<24){
            lendingTime=MyUtil.formatToseparano0(String.valueOf(data.getLendingTime()))+"小时";
        }else if(data.getLendingTime()>=24){
            lendingTime=MyUtil.formatToseparano0(String.valueOf(data.getLendingTime()/24))+"小时";
        }
        tvElements1Value.setText(lendingTime);


        String interest = "";
        if (data.getMinInterest() == data.getMaxInterest()) {
            interest = data.getMinInterest() + "%";
        } else {
            interest = data.getMinInterest() + "%-" + data.getMaxInterest() + "%";
        }
        tvElements2Value.setText(interest);

    }
}
