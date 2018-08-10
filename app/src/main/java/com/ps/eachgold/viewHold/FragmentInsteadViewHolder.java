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
 * Created by 8146 on 2018/1/24.
 */

public class FragmentInsteadViewHolder extends BaseViewHolder<LoanBean> {


    private ImageView ivImg;
    private TextView tvName;
    private TextView tvAmount;
    private TextView tvContent;
    private TextView tvType;

    public FragmentInsteadViewHolder(ViewGroup parent) {
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





    }
}
