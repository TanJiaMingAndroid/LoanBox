package com.ps.loanbox.dialog;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

/**
 * TDialog 工具类  封装常用的dialog
 */

public class DialogUtils {
   private TDialog tDialog;


    private TDialog.Builder simpleDialog(AppCompatActivity activity, @LayoutRes int id, float widthAspect, float highAspect, int gravity, boolean isCancel) {
        TDialog.Builder builder = create(activity)
                .setLayoutRes(id)
                .setScreenWidthAspect(activity, widthAspect)
                .setScreenHeightAspect(activity, highAspect)
                .setCancelableOutside(true)
                .setGravity(gravity)
                .setCancelable(isCancel);
        return builder;
    }


    public TDialog.Builder bottomDialog(AppCompatActivity activity, @LayoutRes int id) {
        return simpleDialog(activity, id, 1f, 0, Gravity.BOTTOM, true);
    }


    public TDialog.Builder homeDialog(AppCompatActivity activity, @LayoutRes int id) {
        return simpleDialog(activity, id, 1f, 1f, Gravity.CENTER, true);
    }


    public TDialog.Builder showLoading(AppCompatActivity activity, @LayoutRes int id) {
        return simpleDialog(activity, id, 0.4f, 0.2f, Gravity.CENTER, false);
    }


    public TDialog simpleDialog(AppCompatActivity activity, @LayoutRes int id, boolean isCancelableOutside, float widthAspect, OnBindViewListener onBindViewListener) {
        tDialog = create(activity)
                .setLayoutRes(id)
                .setScreenWidthAspect(activity, widthAspect)
                .setCancelableOutside(isCancelableOutside)
                .setCancelable(true)
                .setOnBindViewListener(onBindViewListener)
                .setDimAmount(0.8f)
                .create();
        return tDialog;
    }





    private TDialog.Builder create(AppCompatActivity activity) {
        TDialog.Builder builder = new TDialog.Builder(activity.getSupportFragmentManager());
        return builder;
    }
}
