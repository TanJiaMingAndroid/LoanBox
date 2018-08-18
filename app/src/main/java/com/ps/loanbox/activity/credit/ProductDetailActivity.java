package com.ps.loanbox.activity.credit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.ps.loanbox.R;
import com.ps.loanbox.activity.BaseActivity;
import com.ps.loanbox.bean.Header;
import com.ps.loanbox.bean.ProductDetailBean;
import com.ps.loanbox.contract.credit.ProductDetailContract;
import com.ps.loanbox.presenter.ProductDetailPresenter;
import com.ps.loanbox.util.ArithUtils;
import com.ps.loanbox.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import butterknife.BindView;
import butterknife.OnClick;

public class ProductDetailActivity extends BaseActivity implements ProductDetailContract.View {
    @BindView(R.id.im_product_detail_pic)
    ImageView imProductDetailPic;
    @BindView(R.id.tv_product_detail_name)
    TextView tvProductDetailName;
    @BindView(R.id.tv_product_detail_slogan)
    TextView tvProductDetailSlogan;
    @BindView(R.id.btn_product_detail)
    TextView btnProductDetail;
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
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.left_icon)
    ImageView leftIcon;
    TextView title;
    ImageView rightIcon;
    @BindView(R.id.ib_product_detail_collectionPic)
    ImageView ibProductDetailCollectionPic;
    //借款金额
    @BindView(R.id.tv_price)
    TextView tvPrice;
    /*利息*/
    @BindView(R.id.tv_interests)
    TextView tvInterests;
    @BindView(R.id.tv_content1)
    TextView tvContenet1;
    @BindView(R.id.tv_content2)
    TextView tvContenet2;
    @BindView(R.id.tv_content3)
    TextView tvContenet3;
    int productId;
    private ProductDetailPresenter productDetailPresenter;
    private ProductDetailBean productDetailBean = new ProductDetailBean();
    private List<String> cycleOption = new ArrayList<>();
    private List<Integer> cycleOptionInt = new ArrayList<>();
    private List<String> amountOption = new ArrayList<>();
    private List<Integer> amountOptionInt = new ArrayList<>();
    private int amount, cycle, cycle1, cycle2, amount1, amount2;
    private List<ProductDetailBean.ProductDataBean> productDataBeanList = new ArrayList<>();
    private ProductDetailBean.ProductDataBean productDataBean = new ProductDetailBean.ProductDataBean();

    //跳转
    public static void createActivity(Context context, int productId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("productId", productId);
        context.startActivity(intent);
    }
    @Override
    public void getProductDetail(ProductDetailBean productDetail, Header header) {
        productDetailBean = productDetail;
        title.setText(productDetail.getName());
        for (int i = 0; i < productDetailBean.getProductData().size(); i++) {
            amountOption.add(ArithUtils.formatTosepara(productDetailBean.getProductData().get(i).getBorrowAmount()));
            amountOptionInt.add(productDetailBean.getProductData().get(i).getBorrowAmount());
            cycleOption.add(productDetailBean.getProductData().get(i).getBorrowPeriod() + "");
            cycleOptionInt.add(productDetailBean.getProductData().get(i).getBorrowPeriod());
        }
        amountOption = removeDuplicateWithOrder3(amountOption);
        cycleOption = removeDuplicateWithOrder3(cycleOption);
        Collections.sort(amountOption);
        Collections.sort(cycleOption);
        amount1 = Integer.valueOf(Collections.max(amountOptionInt));
        cycle1 = Integer.valueOf(Collections.max(cycleOptionInt));
        initData();
    }
    /*获取数据返回  往界面填充*/
    private void initData() {
        Glide.with(this).load(productDetailBean.getLogoUrl()).into(imProductDetailPic);
        tvProductDetailName.setText(productDetailBean.getName());
        tvProductDetailSum.setText(ArithUtils.formatTosepara(productDetailBean.getProductData().get(0).getBorrowAmount()));
        tvProductDetailSum2.setText(productDetailBean.getProductData().get(0).getBorrowPeriod()+"");
        tvInterests.setText(ArithUtils.formatTosepara(productDetailBean.getProductData().get(0).getInterest()));
        tvPrice.setText(ArithUtils.formatTosepara(productDetailBean.getProductData().get(0).getRepayAmount()));
        tvContenet1.setText(productDetailBean.getApplyCondition());
        tvContenet2.setText(productDetailBean.getApplyProcess());
        tvContenet3.setText(productDetailBean.getRepayInfo());
        tvProductDetailSlogan.setText(productDetailBean.getDescription());
        textView.setText("Nilai maksimal Rp " + ArithUtils.formatTosepara(amount1));
        textView2.setText("Tenor maksimal " + cycle1 + " Hair");
    }

    @Override
    protected void initVariables() {
        productId = getIntent().getIntExtra("productId", 0);
    }

    @Override
    protected void initPresenter() {
        productDetailPresenter = new ProductDetailPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_product_detail;
    }

    @OnClick({R.id.left_icon, R.id.ib_product_detail_collectionPic, R.id.rl_price, R.id.rl_date,R.id.btn_product_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                //返回
                finish();
                break;
            case R.id.ib_product_detail_collectionPic:
                ibProductDetailCollectionPic.setImageResource(R.drawable.collect);
                Toast.makeText(this, "收藏成功！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_price:
                try {
                    if (amountOption.size() > 0) {
                        OptionsPickerView optionsPickerView1 = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                //返回的分别是三个级别的选中位置
                                int bankName = Integer.valueOf(amountOption.get(options1).replace(".", ""));
                                if (amount != bankName) {
                                    amount = bankName;
                                    getProduct();
                                }
                            }
                        }).build();
                        optionsPickerView1.setPicker(amountOption);
                        optionsPickerView1.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rl_date:
                try {
                    if (cycleOption.size() > 0) {
                        OptionsPickerView optionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                //返回的分别是三个级别的选中位置
                                int bankName = Integer.valueOf(cycleOption.get(options1));
                                if (cycle != bankName) {
                                    cycle = bankName;
                                    getProduct1();
                                }
                            }
                        }).build();
                        optionsPickerView.setPicker(cycleOption);
                        optionsPickerView.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_product_detail:
                //返回
                if (!StringUtils.isEmpty(productDetailBean.getUrl())){
                    Uri uri = Uri.parse(productDetailBean.getUrl());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
                break;
        }
    }

    private void getProduct() {
        boolean b = false;
        for (int i = 0; i < productDetailBean.getProductData().size(); i++) {
            if (productDetailBean.getProductData().get(i).getBorrowAmount() == amount && productDetailBean.getProductData().get(i).getBorrowPeriod() == cycle) {
                b = true;
                productDataBean = productDetailBean.getProductData().get(i);
                break;
            }
        }
        if (!b) {
            for (int i = 0; i < productDetailBean.getProductData().size(); i++) {
                if (productDetailBean.getProductData().get(i).getBorrowAmount() == amount) {
                    productDataBean = productDetailBean.getProductData().get(i);
                    break;
                }
            }
        }
        tvProductDetailSum.setText(ArithUtils.formatTosepara(productDataBean.getBorrowAmount()));
        tvProductDetailSum2.setText(ArithUtils.formatTosepara(productDataBean.getBorrowPeriod()));
        tvInterests.setText(ArithUtils.formatTosepara(productDataBean.getInterest()));
        tvPrice.setText(ArithUtils.formatTosepara(productDataBean.getRepayAmount()));
    }

    private void getProduct1() {
        boolean b = false;
        for (int i = 0; i < productDetailBean.getProductData().size(); i++) {
            if (productDetailBean.getProductData().get(i).getBorrowAmount() == amount && productDetailBean.getProductData().get(i).getBorrowPeriod() == cycle) {
                b = true;
                productDataBean = productDetailBean.getProductData().get(i);
                break;
            }
        }
        if (!b) {
            for (int i = 0; i < productDetailBean.getProductData().size(); i++) {
                if (productDetailBean.getProductData().get(i).getBorrowPeriod() == cycle) {
                    productDataBean = productDetailBean.getProductData().get(i);
                    break;
                }
            }
        }
        tvProductDetailSum.setText(ArithUtils.formatTosepara(productDataBean.getBorrowAmount()));
        tvProductDetailSum2.setText(ArithUtils.formatTosepara(productDataBean.getBorrowPeriod()));
        tvInterests.setText(ArithUtils.formatTosepara(productDataBean.getInterest()));
        tvPrice.setText(ArithUtils.formatTosepara(productDataBean.getRepayAmount()));
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        title = findViewById(R.id.title);
        rightIcon = findViewById(R.id.right_icon);
        //标题
        if (!TextUtils.isEmpty(getIntent().getStringExtra("title"))) {
            title.setText(getIntent().getStringExtra("title"));
        }
        //  rightIcon.setImageResource(R.drawable.detail_question);
        productDetailPresenter.getProductDetailSuccess();//初始化数据

    }

    @Override
    public boolean isUseButterKnife() {
        return true;
    }

    @Override
    protected boolean isAllowFullScreen() {
        return false;
    }

    @Override
    public void getError(Throwable e) {

    }

    @Override
    public void showMyProgressDialog(String message) {

    }

    @Override
    public void hiddenProgressDialog() {

    }

    @Override
    public int getProductId() {
        return productId;
    }
    // 3. 删除ArrayList中重复元素，保持原有顺序
    public static List removeDuplicateWithOrder3(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element)) {
                newList.add(element);
            }
        }
        list.clear();
        list.addAll(newList);
        return list;
    }
}
