package com.bawei.wangshihao0404mn.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request build = chain.request().newBuilder()
                .addHeader("userId","2910")
                .addHeader("sessionId","15543396759862910")
                .build();

        return chain.proceed(build);
    }
}
