package com.ps.eachgold.viewHold;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.ps.eachgold.R;
import com.ps.eachgold.bean.BaseBean;

import butterknife.BindView;

/**
 * Created by 8146 on 2018/1/17.
 */

public class DefaultRecordViewHolder extends BaseViewHolder<BaseBean> {

    TextView tvTitle;
    TextView tvBadDebt;
    TextView tvMortgage;
    TextView tvOther;
    TextView tvPublic;

    public DefaultRecordViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_activity_default_record);
        tvTitle = $(R.id.tv_title);
        tvBadDebt = $(R.id.tv_bad_debt);
        tvMortgage = $(R.id.tv_mortgage);
        tvOther = $(R.id.tv_other);
        tvPublic = $(R.id.tv_public);
    }

    @Override
    public void setData(BaseBean data) {
        super.setData(data);

        tvTitle.setText("系统消息");
        int a=20;
        int b=0;
        int c=0;
        int d=8;
        switch (getDataPosition()){
            case 0:
                tvTitle.setText("信用卡违约记录");
                break;
            case 1:
                tvTitle.setText("房贷违约记录");
                break;
            case 2:
                tvTitle.setText("其他贷款违约记录");
                break;
        }

        if(a==0){
            tvBadDebt.setTextColor(ContextCompat.getColor(getContext(),R.color.count_0));
        }else {
            tvBadDebt.setTextColor(ContextCompat.getColor(getContext(),R.color.count_1));
        }
        if(b==0){
            tvMortgage.setTextColor(ContextCompat.getColor(getContext(),R.color.count_0));
        }else {
            tvMortgage.setTextColor(ContextCompat.getColor(getContext(),R.color.count_1));
        }
        if(c==0){
            tvOther.setTextColor(ContextCompat.getColor(getContext(),R.color.count_0));
        }else {
            tvOther.setTextColor(ContextCompat.getColor(getContext(),R.color.count_1));
        }
        if(d==0){
            tvPublic.setTextColor(ContextCompat.getColor(getContext(),R.color.count_0));
        }else {
            tvPublic.setTextColor(ContextCompat.getColor(getContext(),R.color.count_1));
        }

        tvBadDebt.setText(String.valueOf(a));
        tvMortgage.setText(String.valueOf(b));
        tvOther.setText(String.valueOf(c));
        tvPublic.setText(String.valueOf(d));

    }
}
