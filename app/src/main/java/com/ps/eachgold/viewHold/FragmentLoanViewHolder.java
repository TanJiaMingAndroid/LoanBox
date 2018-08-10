package com.ps.eachgold.viewHold;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.ps.eachgold.R;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.util.GlideApp;
import com.ps.eachgold.util.SPutils;


/**
 * Created by 8146 on 2018/1/15.
 */

public class FragmentLoanViewHolder extends BaseViewHolder<LoanBean> {


    private ImageView ivImg;
    private TextView tvName;
    private TextView tvAmount;
    private TextView tvContent;
    private TextView tvType;

    public FragmentLoanViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_fragment_loan);

        ivImg = $(R.id.iv_img);
        tvName = $(R.id.tv_name);
        tvAmount = $(R.id.tv_amount);
        tvContent = $(R.id.tv_content);
        tvType = $(R.id.tv_type);
    }

    @Override
    public void setData(LoanBean data) {
        super.setData(data);

        String baseUrl = (String) SPutils.get(getContext(), "baseImgUrl", "");


       /* GlideApp.with(getContext()).load("http:" + baseUrl + data.getPic())
                .placeholder(R.mipmap.credit_glide)
                .error(R.mipmap.credit_glide)
                .into(ivImg);


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
        tvAmount.setText(min + "-" + max);
        tvContent.setText(data.getPresentation());
        tvType.setText("可贷额度");*/
    }
}
