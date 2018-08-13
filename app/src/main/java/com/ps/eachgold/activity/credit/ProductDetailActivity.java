package com.ps.eachgold.activity.credit;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailActivity extends BaseActivity {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.im_product_detail_pic)
    ImageView imProductDetailPic;
    @BindView(R.id.tv_product_detail_name)
    TextView tvProductDetailName;
    @BindView(R.id.tv_product_detail_slogan)
    TextView tvProductDetailSlogan;
    @BindView(R.id.ib_product_detail_collectionPic)
    ImageView ibProductDetailCollectionPic;
    @BindView(R.id.iv_product_detail_coin)
    ImageView ivProductDetailCoin;
    @BindView(R.id.tv_product_detail_name1)
    TextView tvProductDetailName1;
    @BindView(R.id.tv_product_detail_sum)
    TextView tvProductDetailSum;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.iv_product_detail_coin2)
    ImageView ivProductDetailCoin2;
    @BindView(R.id.tv_product_detail_name2)
    TextView tvProductDetailName2;
    @BindView(R.id.tv_product_detail_sum2)
    TextView tvProductDetailSum2;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.textView2)
    TextView textView2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_product_detail;


    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        //标题
        title.setText("产品详情");

    }

    @Override
    public boolean isUseButterKnife() {
        return false;
    }

    @Override
    protected boolean isAllowFullScreen() {
        return false;
    }
}
