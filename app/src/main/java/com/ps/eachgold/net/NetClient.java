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

  // public static final String BASE_URL = "http://10.0.16.23:10009";

   // public static final String BASE_URL = "http://192.168.20.113:10009";
   public static final String BASE_URL = "http://api.sanjinxia.cn";

    private Retrofit mRetrofit;

    private NetClient() {
        //rxjava
//        mRetrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(getHttpClient())
//                .addConverterFactory(MyGsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();

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
