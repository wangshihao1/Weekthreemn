package com.bawei.wangshihao0404mn.di.model;

import android.util.Log;

import com.bawei.wangshihao0404mn.data.bean.InfoBean;
import com.bawei.wangshihao0404mn.di.contract.IInfo;
import com.bawei.wangshihao0404mn.utils.ApiService;
import com.bawei.wangshihao0404mn.utils.Apis;
import com.bawei.wangshihao0404mn.utils.RetrofitUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class IInfoModelmp implements IInfo.IInfoModel {
    @Override
    public void requestInfoData(String keyword, int page, int count, final CallBack callBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        Observable<InfoBean> observable = apiService.getInfo(keyword, page, count);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InfoBean>() {
                    @Override
                    public void accept(InfoBean infoBean) throws Exception {
                        callBack.oncallback(infoBean);
                    }
                },      new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("infoBean",throwable.getMessage());
                    }
                });
    }
}
