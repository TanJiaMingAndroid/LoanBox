package com.ps.loanbox.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.ps.loanbox.App;

/**
 * Created by 8146 on 2018/1/31.
 */

public class VerisonUtil {
    /**
     * 获取当前本地apk的版本
     *
     * @param
     * @return
     */
    //获取版本号
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "找不到版本号";
        }
    }

    /**
     * 获取版本号
     */
    public static String getClientVersion() {
        PackageManager pManager = App.getAppContext().getPackageManager();
        String packageName = App.getAppContext().getPackageName();
        PackageInfo packageInfo;
        String version = "";
        try {
            packageInfo = pManager.getPackageInfo(packageName, 0);
            version = packageInfo.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return version;
        }
    }
    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
}
