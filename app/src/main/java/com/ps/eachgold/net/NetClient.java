package com.ps.eachgold.net;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


/**
 * 网络访问入口
 */
public class NetClient {
    //超时时间
    private static final int TIME_OUT = 60 * 1000;

   public static final String BASE_URL = "http://sjx-api.sanjinxia.com";
   //public static final String BASE_URL = "http://172.17.0.1:11000";


    private Retrofit mRetrofit;

    private NetClient() {


        //rxjava2
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getHttpClient())
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    private static class SingletonHolder {
        private static final NetClient INSTANCE = new NetClient();
    }


    public Retrofit net() {
        return mRetrofit;
    }

    public static NetClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private OkHttpClient getHttpClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

                //打印retrofit日志  正式版 注销
                Log.i("RetrofitLog", "retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        return client;
    }

}
