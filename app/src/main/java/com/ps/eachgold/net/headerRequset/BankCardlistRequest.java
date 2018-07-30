package com.ps.eachgold.net.headerRequset;

import com.ps.eachgold.net.ApiAction;

/**
 * Created by 8146 on 2018/1/31.
 */

public class BankCardlistRequest extends BaseRequset {
    /**
     * "reqType":"bank",//请求类型  bank（表示 银行下的信用卡），hot(表示 热门的信用卡)
     * "bankId":"1",//银行编号,如果是请求类型为热门，此处可以为0
     * "page":"0",//页数 ，如为0表示 全部
     * "size":"0"//页展示行数  如为0表示 全部
     */
    private String reqType;
    private String bankId;
//    private String page;
//    private String size;

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

//    public String getPage() {
//        return page;
//    }
//
//    public void setPage(String page) {
//        this.page = page;
//    }
//
//    public String getSize() {
//        return size;
//    }
//
//    public void setSize(String size) {
//        this.size = size;
//    }

    public BankCardlistRequest() {
        createHeader();

    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.BANK_CARD_LIST);
    }
}
