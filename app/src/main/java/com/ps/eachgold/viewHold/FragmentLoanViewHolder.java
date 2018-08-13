package com.ps.eachgold.viewHold;

import android.util.Log;
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


    ImageView ivImage;

    TextView tvName;

    TextView tvSum;

    TextView tvSlogan;

    TextView tvLilv;

    TextView tvLixi;


    public FragmentLoanViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_loan_list);

        ivImage = $(R.id.iv_image);
        tvName = $(R.id.tv_name);
        tvSum = $(R.id.tv_sum);
        tvSlogan = $(R.id.tv_product_slogan);
        tvLilv = $(R.id.tv_lilv);
        tvLixi = $(R.id.tv_lixi);
    }

    @Override
    public void setData(LoanBean data) {
        super.setData(data);

        Log.e("hotdata",data.getName());

        tvName.setText(data.getName());
        tvSlogan.setText(data.getDescription());

        //String baseUrl = (String) SPutils.get(getContext(), "baseImgUrl", "");


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
