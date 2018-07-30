package com.ps.eachgold.viewHold;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.ps.eachgold.R;
import com.ps.eachgold.bean.BankBean;
import com.ps.eachgold.bean.BaseBean;
import com.ps.eachgold.util.GlideApp;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.util.T;

import java.util.List;

/**
 * Created by 8146 on 2018/1/17.
 */

public class FragmentCreditHeadViewHolder extends BaseViewHolder<BankBean> {

    private ImageView ivImg;
    private TextView tvName;
    private TextView tv_img_type;
    private int mType;

    public FragmentCreditHeadViewHolder(ViewGroup parent,int type) {
        super(parent, R.layout.viewholder_fragment_credit_head);
        ivImg = $(R.id.tv_img);
        tvName = $(R.id.tv_bank_name);
        tv_img_type = $(R.id.tv_img_type);
        mType=type;
    }

    @Override
    public void setData(BankBean data) {
        super.setData(data);
        //ICON
        String baseUrl = (String) SPutils.get(getContext(), "baseImgUrl", "");
        RequestOptions requestOptions = RequestOptions.circleCropTransform()
                .placeholder(R.mipmap.round_glide)
                .error(R.mipmap.round_glide);

        Glide.with(getContext()).load("http:" + baseUrl + data.getPic())
                .apply(requestOptions)
                .into(ivImg);
        //名字
        tvName.setText(data.getBank());
        //类型
        if(mType==0){
            tv_img_type.setVisibility(View.GONE);
        }else {
            if (data.getLabel() != null && !"".equals(data.getLabel())) {
                tv_img_type.setText(data.getLabel());
                tv_img_type.setVisibility(View.VISIBLE);
            } else {
                tv_img_type.setText("");
                tv_img_type.setVisibility(View.GONE);
            }
        }



    }
}