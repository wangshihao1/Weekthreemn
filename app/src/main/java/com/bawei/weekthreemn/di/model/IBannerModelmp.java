package com.bawei.weekthreemn.di.model;

import android.util.Log;

import com.bawei.weekthreemn.data.bean.BannerBean;
import com.bawei.weekthreemn.di.contract.IBanner;
import com.bawei.weekthreemn.utils.ApiService;
import com.bawei.weekthreemn.utils.Apis;
import com.bawei.weekthreemn.utils.RectrofitUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class IBannerModelmp implements IBanner.IBannerModel {
    @Override
    public void requestBannerData(final CallBack callBack) {
        ApiService apiService = RectrofitUtils.getInstance().create(ApiService.class);
        Observable<BannerBean> observable = apiService.getBanner();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BannerBean>() {
                    @Override
                    public void accept(BannerBean bannerBean) throws Exception {
                        callBack.onCallBack(bannerBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("bannerBean",throwable.getMessage());
                    }
                });
    }
}
