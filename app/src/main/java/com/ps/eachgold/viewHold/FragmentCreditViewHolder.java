package com.ps.eachgold.viewHold;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.ps.eachgold.R;
import com.ps.eachgold.bean.BankCardBean;
import com.ps.eachgold.bean.LoanBean;
import com.ps.eachgold.util.GlideApp;
import com.ps.eachgold.util.SPutils;

/**
 * Created by 8146 on 2018/1/17.
 */

public class FragmentCreditViewHolder extends BaseViewHolder<LoanBean> {


    private ImageView ivImage;
    private TextView tvName;
    private TextView tvSum;
    private TextView tvSlogan;
    private TextView tvLilv;
    private TextView tvLixi;




    public FragmentCreditViewHolder(ViewGroup parent) {
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
        //ICON
        //String baseUrl= (String) SPutils.get(getContext(),"baseImgUrl","");
       /* GlideApp.with(getContext()).load("http:"+baseUrl+data.getPic())
                .placeholder(R.mipmap.round_glide)
                .error(R.mipmap.round_glide)
                .into(ivImage);*/
        tvName.setText(data.getName());
    }
}
