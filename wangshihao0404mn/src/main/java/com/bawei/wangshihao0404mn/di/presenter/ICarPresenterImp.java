package com.bawei.wangshihao0404mn.di.presenter;

import com.bawei.wangshihao0404mn.data.bean.CarBean;
import com.bawei.wangshihao0404mn.di.contract.ICar;
import com.bawei.wangshihao0404mn.di.model.ICarModelmp;

import java.lang.ref.SoftReference;

public class ICarPresenterImp implements ICar.ICarPresenter<ICar.ICarView> {

    private  ICar.ICaroModel caroModel;
    private ICar.ICarView carView;
    private SoftReference<ICar.ICarView> reference;

    @Override
    public void atteachView(ICar.ICarView iCarView) {
        caroModel = new ICarModelmp();
        this.carView = iCarView;
        reference = new SoftReference<>(carView);
    }

    @Override
    public void deatachView(ICar.ICarView iCarView) {
        reference.clear();
    }

    @Override
    public void responeCarData() {
        caroModel.requestCarData(new ICar.ICaroModel.CallBack() {
            @Override
            public void oncallback(CarBean carBean) {
                carView.showCarData(carBean);
            }
        });
    }
}
