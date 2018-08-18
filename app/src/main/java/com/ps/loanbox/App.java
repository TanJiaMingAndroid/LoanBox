package com.ps.loanbox;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


/**
 * Created by jingjing on 2017/3/22.
 * 承MultiDexApplication  java.lang.NoClassDefFoundError:retrofit2.Retrofit$Builder错误解决
 */

public class App extends Application {
    public static App instance;
    //刷新标志
    //网络连接标志
    public static AppCompatActivity mAppCompatActivity;

    public static App getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //TODO
       // SPutils.clear(this);
        //LeakCanary.install(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}