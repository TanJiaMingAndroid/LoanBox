package com.ps.eachgold.adapter;/**
 * Created by 8657 on 2018/8/13.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ps.eachgold.R;
import com.ps.eachgold.bean.HotProductBean;

import java.util.List;

/**
 * creat by tanjiaming at 2018/8/13
 */
public class IndexHotProductAdapter extends RecyclerView.Adapter<IndexHotProductAdapter.ViewHolder>{
    private List<HotProductBean> mProductList;
    //定义一个内部类ViewHolder，继承自RecyclerView.ViewHolder，用来缓存子项的各个实例，提高效率
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView productImage;
        TextView productName;
        View productView;

        public ViewHolder(View view){
            super(view);
            productView = view;
           productView.findViewById(R.id.iv_index_hot_product_pic);
           productName.findViewById(R.id.tv_index_hot_product_name);
        }
    }

    //绑定传入的数据源
    public IndexHotProductAdapter(List<HotProductBean> productList){
        mProductList = productList;
    }



    @Override
    public IndexHotProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.productView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                HotProductBean hotProductBean = mProductList.get(position);
                Toast.makeText(v.getContext(),"you clicked view  "+hotProductBean.getName(),Toast.LENGTH_SHORT).show();
            }
        });

        holder.productImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                HotProductBean hotProductBean = mProductList.get(position);
                Toast.makeText(v.getContext(),"you clicked image  "+hotProductBean.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }



    @Override
    public void onBindViewHolder(IndexHotProductAdapter.ViewHolder holder, int position) {
        HotProductBean hotProductBean = mProductList.get(position);
        holder.productImage.setImageResource(Integer.parseInt(hotProductBean.getImageUrl()));
        holder.productName.setText(hotProductBean.getName());


    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }
}
