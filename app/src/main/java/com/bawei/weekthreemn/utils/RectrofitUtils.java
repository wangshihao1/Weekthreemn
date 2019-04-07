package com.bawei.weekthreemn.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RectrofitUtils {

    private static volatile RectrofitUtils instance;
    private final Retrofit retrofit;

    private RectrofitUtils(){
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Apis.BASE_URL)
                .build();

    }

    public static RectrofitUtils getInstance() {
        if (instance == null){
            synchronized (RectrofitUtils.class){
                if (instance == null){
                    instance = new RectrofitUtils();
                }
            }
        }
        return instance;
    }


    public <T> T create(Class<T> service){

        return retrofit.create(service);
    }
}
