package com.ps.loanbox.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.loanbox.R;
import com.ps.loanbox.bean.HelpBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 8303 on 2018/6/12.
 */

public class HelpAdapter extends BaseAdapter {
    private List<HelpBean> helpBeanList;
    private Context context;


    public HelpAdapter(List<HelpBean> helpBeanList, Context context) {
        this.helpBeanList = helpBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return helpBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return helpBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_help, null);
        final ViewHolder holder = new ViewHolder(convertView);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(position + 1).append(".").append(helpBeanList.get(position).getTitle());
        String a=helpBeanList.get(position).getAnswer();
        holder.itemHelpMtvQ.setText(stringBuilder);
        holder.itemHelpMtvQ.setTextColor(context.getResources().getColor(R.color.black1));
        holder.itemHelpMtvA.setText(a);
        holder.itemHelpMtvA.setTextColor(Color.GRAY);
        holder.itemHelpMtvQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.itemHelpMtvA.getVisibility() == View.GONE) {
                    holder.itemHelpMtvA.setVisibility(View.VISIBLE);
                    holder.ivItemHelp.setImageResource(R.drawable.icon_down);

                } else {
                    holder.itemHelpMtvA.setVisibility(View.GONE);
                    holder.ivItemHelp.setImageResource(R.drawable.icon_next1);
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item_help_mtv_q)
        TextView itemHelpMtvQ;
        @BindView(R.id.item_help_mtv_a)
        TextView itemHelpMtvA;
        @BindView(R.id.iv_item_help)
        ImageView ivItemHelp;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
