package com.bawei.wangshihao0404mn.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static RetrofitUtils instance;
    private Retrofit retrofit;
    private final OkHttpClient client;

    private RetrofitUtils(){


       /* HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);*/


        client = new OkHttpClient.Builder()
                //.addNetworkInterceptor(httpLoggingInterceptor)
                .addInterceptor(new MyInterceptor())
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Apis.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }


    public static RetrofitUtils getInstance() {
        if (instance == null){
            synchronized (RetrofitUtils.class){
                if (instance == null){
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }

    public <T> T create(Class<T> service){

        return retrofit.create(service);
    }
}
