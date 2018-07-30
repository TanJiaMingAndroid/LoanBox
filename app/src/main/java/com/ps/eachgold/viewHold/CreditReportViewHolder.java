package com.ps.eachgold.viewHold;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.ps.eachgold.R;
import com.ps.eachgold.bean.BaseBean;

/**
 * Created by 8146 on 2018/1/16.
 */

public class CreditReportViewHolder extends BaseViewHolder<BaseBean> {


    private ImageView ivImg;
    private TextView tvTitle;
    private TextView tvContent;


    public CreditReportViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_activity_credit_report);
        ivImg = $(R.id.iv_icon);
        tvTitle = $(R.id.tv_title);
        tvContent = $(R.id.tv_content);

    }

    @Override
    public void setData(BaseBean data) {
        super.setData(data);

        switch (getDataPosition()){
            case 0:
                Glide.with(getContext())
                        .load(R.mipmap.icon_weiyueyuqi)
                        .into(ivImg);
                tvTitle.setText("违约逾期");
                tvContent.setText("共有0次预期记录");
                break;
            case 1:
                Glide.with(getContext())
                        .load(R.mipmap.icon_xinyongleixing)
                        .into(ivImg);
                tvTitle.setText("信用类型");
                tvContent.setText("共有4个信贷账户");
                break;
            case 2:
                Glide.with(getContext())
                        .load(R.mipmap.icon_lvyuenegli)
                        .into(ivImg);
                tvTitle.setText("履约能力");
                tvContent.setText("负债总额6365.0元");
                break;
            case 3:
                Glide.with(getContext())
                        .load(R.mipmap.icon_xinyonglishi)
                        .into(ivImg);
                tvTitle.setText("信用历史");
                tvContent.setText("信用历史时长23个月");
                break;
            case 4:
                Glide.with(getContext())
                        .load(R.mipmap.icon_cahxunpinci)
                        .into(ivImg);
                tvTitle.setText("查询频次");
                tvContent.setText("最近三个月查询3次");
                break;

        }

    }
}
