package com.bawei.weekthreemn.di.presenter;

import com.bawei.weekthreemn.data.bean.InfoBean;
import com.bawei.weekthreemn.di.contract.IGoods;
import com.bawei.weekthreemn.di.model.IGoodsModelmp;

import java.lang.ref.SoftReference;

public class IGoodsPresenterImp implements IGoods.IGoodsPresenter<IGoods.IGoodsView> {

    private IGoods.IGoodsModel goodsModel;
    private IGoods.IGoodsView goodsView;
    private SoftReference<IGoods.IGoodsView> reference;


    @Override
    public void atteachView(IGoods.IGoodsView iGoodsView) {
           this.goodsView = iGoodsView;
           goodsModel = new IGoodsModelmp();
           reference = new SoftReference<>(goodsView);
    }

    @Override
    public void deatachView(IGoods.IGoodsView iGoodsView) {
           reference.clear();
    }

    @Override
    public void responseGoodsData() {
            goodsModel.requestGoodsData(new IGoods.IGoodsModel.CallBack() {
                @Override
                public void onCallBack(InfoBean infoBean) {
                    goodsView.showGoodsData(infoBean);
                }
            });
    }
}
