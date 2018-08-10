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

    TextView tvSum;

    TextView tvSlogan;

    TextView tvLilv;

    TextView tvLixi;



    public LoanListViewHolder(ViewGroup parent, String mtype) {
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
        //图片
        String baseUrl = (String) SPutils.get(getContext(), "baseImgUrl", "");


        GlideApp.with(getContext()).load("http:" + baseUrl + data.getLogoUrl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivImage);

        //名字
        tvName.setText(data.getProductName());
        //内容
        String mortgage = "无";









    }
}

