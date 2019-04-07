package com.bawei.weekthreemn.di.model;

import android.util.Log;

import com.bawei.weekthreemn.data.bean.BannerBean;
import com.bawei.weekthreemn.data.bean.InfoBean;
import com.bawei.weekthreemn.di.contract.IGoods;
import com.bawei.weekthreemn.utils.ApiService;
import com.bawei.weekthreemn.utils.RectrofitUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class IGoodsModelmp implements IGoods.IGoodsModel {
    @Override
    public void requestGoodsData(final CallBack callBack) {
        ApiService apiService = RectrofitUtils.getInstance().create(ApiService.class);
        Observable<InfoBean> observable = apiService.getInfo();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InfoBean>() {
                    @Override
                    public void accept(InfoBean infoBean) throws Exception {
                        callBack.onCallBack(infoBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("infoBean",throwable.getMessage());
                    }
                });

    }
}
