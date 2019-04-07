package com.bawei.wangshihao0404mn.utils;

import com.bawei.wangshihao0404mn.data.bean.CarBean;
import com.bawei.wangshihao0404mn.data.bean.InfoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("small/commodity/v1/findCommodityByKeyword")
    Observable<InfoBean> getInfo(
            @Query("keyword") String keyword,
            @Query("page") int page,
            @Query("count") int count);



   @GET("ks/product/getCarts?uid=51")
   Observable<CarBean> getCar();
}
