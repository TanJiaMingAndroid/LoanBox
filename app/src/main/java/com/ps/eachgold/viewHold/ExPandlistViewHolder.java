package com.ps.eachgold.viewHold;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ps.eachgold.R;
import com.ps.eachgold.bean.GroupBean;

import java.util.List;

/**
 * Created by 8146 on 2018/1/17.
 */

public class ExPandlistViewHolder extends BaseExpandableListAdapter {
    private List<GroupBean> listGroup;

    private Context context;

    public ExPandlistViewHolder(Context context, List<GroupBean> listGroup) {
        this.context = context;
        this.listGroup = listGroup;

    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listGroup.get(groupPosition).getChildlist().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return  listGroup.get(groupPosition).getChildlist().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;

        if (convertView == null) {
            holder = new GroupViewHolder();
           // LayoutInflater inflater = (LayoutInflater) context
            //        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           // convertView = inflater.inflate(R.layout.viewholder_expand_report_group, null);

            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .viewholder_expand_report_group, parent, false);
            holder.line = (View) convertView.findViewById(R.id.v_divider);
            holder.title = (TextView) convertView.findViewById(R.id.expand_group_title);
            holder.count = (TextView) convertView.findViewById(R.id.expand_group_count);
            convertView.setTag(holder);
        } else {
            //直接通过holder获取下面三个子控件，不必使用findviewbyid，加快了 UI 的响应速度
            holder = (GroupViewHolder) convertView.getTag();

        }
        if(groupPosition==0){
            holder.line.setVisibility(View.GONE);
        }else {
            holder.line.setVisibility(View.VISIBLE);
        }
        holder.title.setText(listGroup.get(groupPosition).getTitle());
        holder.count.setText(listGroup.get(groupPosition).getNum());

        return  convertView;

    }
    static class GroupViewHolder {
        View line;
        TextView title;
        TextView count;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;

        if (convertView == null) {
            holder = new ChildViewHolder();
//            LayoutInflater inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.viewholder_expand_report_child, null);

            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .viewholder_expand_report_child, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.expand_child_text);
            holder.childLine = (View) convertView.findViewById(R.id.v_divider_child);
            convertView.setTag(holder);
        } else {
            //直接通过holder获取下面三个子控件，不必使用findviewbyid，加快了 UI 的响应速度
            holder = (ChildViewHolder) convertView.getTag();

        }
//        if(childPosition==0){
//            holder.childLine.setVisibility(ViewImp.GONE);
//        }else {
//            holder.childLine.setVisibility(ViewImp.VISIBLE);
//        }
        holder.text.setText(listGroup.get(groupPosition).getChildlist().get(childPosition));


        return  convertView;
    }
    static class ChildViewHolder {
        View childLine;
        TextView text;

    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
