package com.ps.loanbox.util;

import android.content.Context;

import com.ps.loanbox.App;
import com.ps.loanbox.activity.login.LoginActivity;

/**
 * Created by 8303 on 2018/3/29.
 */

public class ButtonUtils {
    private static long lastClickTime = 0;
    private static long DIFF = 1000;
    private static int lastButtonId = -1;

    /**
     * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击    *    * @return
     */
    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(-1, DIFF);
    }

    /**
     * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击    *    * @return
     */
    public static boolean isFastDoubleClick(int buttonId) {
        return isFastDoubleClick(buttonId, DIFF);
    }

    /**
     * 判断两次点击的间隔，如果小于diff，则认为是多次无效点击    *    * @param diff    * @return
     */
    public static boolean isFastDoubleClick(int buttonId, long diff) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (lastButtonId == buttonId && lastClickTime > 0 && timeD < diff) {
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }


    /**
     * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击    *    * @return
     */
    public static boolean isFastDoubleClick2(int buttonId ,Context context) {
        return isFastDoubleClick2(buttonId, DIFF,context);
    }
    /**
     * 判断两次点击的间隔，如果小于diff，则认为是多次无效点击    *    * @param diff    * @return
     */
    public static boolean isFastDoubleClick2(int buttonId, long diff, Context context) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        boolean b= StringUtils.isEmpty((CharSequence) SPutils.get(App.getAppContext(),"",""));
        if (StringUtils.isEmpty((CharSequence) SPutils.get(App.getAppContext(),"sessionid",""))){
            LoginActivity.createActivity(context);
            return true;
        }
        if (lastButtonId == buttonId && lastClickTime > 0 && timeD < diff) {
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }
}
