package com.bawei.wangshihao0404mn.di.model;

import android.util.Log;

import com.bawei.wangshihao0404mn.data.bean.CarBean;
import com.bawei.wangshihao0404mn.di.contract.ICar;
import com.bawei.wangshihao0404mn.utils.ApiService;
import com.bawei.wangshihao0404mn.utils.RetrofitUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ICarModelmp implements ICar.ICaroModel {
    @Override
    public void requestCarData(final CallBack callBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        Observable<CarBean> observable = apiService.getCar();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarBean>() {
                    @Override
                    public void accept(CarBean carBean) throws Exception {
                        callBack.oncallback(carBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("carBean",throwable.getMessage());
                    }
                });
    }
}
