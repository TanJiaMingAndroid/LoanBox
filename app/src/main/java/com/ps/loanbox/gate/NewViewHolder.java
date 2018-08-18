package com.ps.loanbox.gate;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.ps.loanbox.R;

/**
 * Created by 8146 on 2018/2/9.
 */

public class NewViewHolder extends BaseViewHolder<NewBean> {



    private TextView tvName;
    private TextView tvContent;
    private TextView tvTime;

    private ImageView ivImg;

    public NewViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_fragment_new);

        tvName = $(R.id.tv_name);

        tvContent = $(R.id.tv_content);

        tvTime= $(R.id.tv_time);
        ivImg= $(R.id.iv_img);
    }

    @Override
    public void setData(NewBean data) {
        super.setData(data);

//        String baseUrl = (String) SPutils.get(getContext(), "baseImgUrl", "");
//
      Glide.with(getContext()).load(data.getImgUrl())
                .into(ivImg);

        tvName.setText(data.getTitle());
        tvContent.setText(data.getSource());

        tvTime.setText(data.getTime());
    }
}
