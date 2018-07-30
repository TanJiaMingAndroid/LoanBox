package com.ps.eachgold.viewHold;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.ps.eachgold.R;
import com.ps.eachgold.bean.BaseBean;
import com.ps.eachgold.bean.MessageBean;

/**
 * Created by 8146 on 2018/1/16.
 */

public class MessageViewHolder extends BaseViewHolder<MessageBean> {


    private ImageView newMsg;
    private TextView tvTitle;
    private TextView tvTime;
    private TextView tvText;
    private int num;

    public MessageViewHolder(ViewGroup parent,int num) {
        super(parent, R.layout.viewholder_activity_message);
        tvTitle = $(R.id.tv_message_title);
        tvTime = $(R.id.tv_message_time);
        tvText = $(R.id.tv_message_text);

        newMsg=$(R.id.iv_new_message);
        this.num=num;
    }

    @Override
    public void setData(MessageBean data) {
        super.setData(data);
        tvTitle.setText(data.getTitle());
        tvTime.setText(data.getCreatedAt());
        tvText.setText(data.getDes());
        for(int i=0;i<num;i++){
            if(getDataPosition()==num){
                newMsg.setVisibility(View.VISIBLE);
            }else {
                newMsg.setVisibility(View.GONE);
            }
        }
    }
}

