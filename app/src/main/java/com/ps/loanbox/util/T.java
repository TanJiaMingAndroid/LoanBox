package com.ps.loanbox.util;

import android.widget.Toast;

import com.ps.loanbox.App;


/**
 * 弹出Toast工具类
 */

public class T {

    private static Toast mToast;

    public static void showShort(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(App.getAppContext(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }

    public static void showLong(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(App.getAppContext(), text, Toast.LENGTH_LONG);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }

    public static void println(Object text) {
        System.out.println(text);
    }
}
