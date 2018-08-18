package com.ps.loanbox.util;

import android.support.v7.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

/**

 * <p>
 * 界面管理器
 */
public class ActivityManager {
    private List<AppCompatActivity> activityList = new LinkedList<>();
    private static ActivityManager instance;

    private ActivityManager() {
    }

    //单例模式中获取唯一的MyApplication实例
    public static ActivityManager getInstance() {
        if (null == instance) {
            instance = new ActivityManager();
        }
        return instance;
    }

    //添加Activity到容器中
    public void addActivity(AppCompatActivity activity) {
        activityList.add(activity);
    }

    public void removeActivity(AppCompatActivity activity) {
        activityList.remove(activity);
    }

    //遍历所有Activity并finish
    public void finish() {
        for (AppCompatActivity activity : activityList) {
            activity.finish();
        }
    }

    //清除或者保留指定activity
    public void finish(String simpleName, boolean isReserve) {
        for (AppCompatActivity activity : activityList) {
            if (isReserve != activity.getClass().getSimpleName().equals(simpleName)) {
                activity.finish();
            }
        }
    }

    //获取指定的activity
    public AppCompatActivity getActivityBySimpleName(String simpleName) {
        for (AppCompatActivity activity : activityList) {
            if (activity.getClass().getSimpleName().equals(simpleName)) {
                return activity;
            }
        }
        return null;
    }
}
