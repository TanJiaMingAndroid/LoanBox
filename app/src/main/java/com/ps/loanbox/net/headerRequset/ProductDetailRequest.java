package com.ps.loanbox.net.headerRequset;/**
 * Created by 8657 on 2018/8/14.
 */

import com.ps.loanbox.net.ApiAction;

/**
 * creat by tanjiaming at 2018/8/14
 */
public class ProductDetailRequest extends BaseRequset{
    private int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public ProductDetailRequest() {
        createHeader();

    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.PRODUCT_DETAIL);
    }
}
