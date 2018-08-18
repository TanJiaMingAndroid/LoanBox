package com.ps.loanbox.util;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

/**
 * Created by jingjing on 2017/5/18.
 */

public class StatBarCpmpart {

    public static void init(Activity activity, LinearLayout bar){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            bar.setVisibility(View.VISIBLE);
            //获取手机状态栏高度
            int height=getBarHight(activity);
            //动态的设置隐藏布局的高度
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) bar.getLayoutParams();
            params.height = height;
            bar.setLayoutParams(params);
        }
    }

    public static int getBarHight(Activity activity) {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            Log.i("code","--状态栏高度--"+activity.getResources().getDimensionPixelSize(x));
            return activity.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
