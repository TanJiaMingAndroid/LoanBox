package com.ps.eachgold;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatActivity;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.ps.eachgold.net.ApiAction;


/**
 * Created by jingjing on 2017/3/22.
 * 承MultiDexApplication  java.lang.NoClassDefFoundError:retrofit2.Retrofit$Builder错误解决
 */

public class App extends Application {
    public static App instance;
    private BroadcastReceiver mBroadcastReceiver;
    //刷新标志
    public static boolean isCanRefresh=false;
    //网络连接标志
    public static boolean isNetOn;
    public static AppCompatActivity mAppCompatActivity;
    private static App appContext;


    public static App getInstance() {
        return appContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
       // LeakCanary.install(this);
        instance = this;
        //instance.getResources().getSystem().flushLayoutCache();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

    }


    private void initReceiver() {
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(
                        ConnectivityManager.CONNECTIVITY_ACTION)) {
//                    if (NetUtil.isConnected(getAppContext())) {
//                        isNetOn = true;
//                    } else {
//                        //T.showShort("网络异常,请检查网络连接");
//        //                        showNetDialog();
//                        isNetOn = false;
//                    }
                }
            }

        };
        registerReceiver(mBroadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);//解决方法数超标的问题
    }

    public static Context getAppContext() {
                return instance.getApplicationContext();
            }

    }
