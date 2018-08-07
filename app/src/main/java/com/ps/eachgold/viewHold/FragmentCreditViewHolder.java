package com.ps.eachgold.viewHold;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.ps.eachgold.R;
import com.ps.eachgold.bean.BankCardBean;
import com.ps.eachgold.util.GlideApp;
import com.ps.eachgold.util.SPutils;

/**
 * Created by 8146 on 2018/1/17.
 */

public class FragmentCreditViewHolder extends BaseViewHolder<BankCardBean> {


    private ImageView ivImg;
    private TextView tvName;
    private TextView tvContent1;



    public FragmentCreditViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_fragment_credit);

        ivImg = $(R.id.iv_card_img);
        tvName = $(R.id.tv_card_name);
        tvContent1 = $(R.id.tv_card_content_1);


    }

    @Override
    public void setData(BankCardBean data) {
        super.setData(data);
        //ICON
        String baseUrl= (String) SPutils.get(getContext(),"baseImgUrl","");
        GlideApp.with(getContext()).load("http:"+baseUrl+data.getPic())
                .placeholder(R.mipmap.round_glide)
                .error(R.mipmap.round_glide)
                .into(ivImg);
        tvName.setText(data.getName());
        tvContent1.setText(data.getPresentation());
    }
}
