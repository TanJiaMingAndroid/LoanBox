package com.ps.loanbox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ps.loanbox.R;

import java.util.List;

public class DialogLvAdapter extends BaseAdapter {
    List<String> lists;
    Context context;
    public DialogLvAdapter(List<String> lists, Context context) {
        this.lists = lists;
        this.context=context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=LayoutInflater.from(context).inflate(R.layout.item_dialog_lv,null);
        TextView tv=convertView.findViewById(R.id.tv_content);
        tv.setText(lists.get(position));
        return convertView;
    }
}
