package com.ps.eachgold.contract;

/**
 * Created by 8146 on 2017/1/12.
 * ViewImp
 */

public interface ImpBaseView {
    void getError(Throwable e);
    void showMyProgressDialog(String message);
    void hiddenProgressDialog();
}
