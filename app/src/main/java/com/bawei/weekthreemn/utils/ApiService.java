package com.bawei.weekthreemn.utils;

import com.bawei.weekthreemn.data.bean.BannerBean;
import com.bawei.weekthreemn.data.bean.InfoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("commodity/v1/bannerShow")
    Observable<BannerBean> getBanner();
    @GET("commodity/v1/commodityList")
    Observable<InfoBean> getInfo();
}
