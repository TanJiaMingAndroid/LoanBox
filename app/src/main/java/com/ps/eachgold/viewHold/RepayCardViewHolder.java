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


    }

}
