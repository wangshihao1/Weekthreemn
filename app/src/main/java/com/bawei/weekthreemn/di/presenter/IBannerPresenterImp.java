package com.bawei.weekthreemn.di.presenter;

import com.bawei.weekthreemn.data.bean.BannerBean;
import com.bawei.weekthreemn.di.contract.IBanner;
import com.bawei.weekthreemn.di.model.IBannerModelmp;

import java.lang.ref.SoftReference;

public class IBannerPresenterImp implements IBanner.IBannerPresenter<IBanner.IBannerView> {

    private IBanner.IBannerModel bannerModel;
    private IBanner.IBannerView bannerView;
    private SoftReference<IBanner.IBannerView> reference;

    @Override
    public void atteachView(IBanner.IBannerView iBannerView) {
           this.bannerView = iBannerView;
           bannerModel = new IBannerModelmp();
           reference = new SoftReference<>(bannerView);

    }

    @Override
    public void deatachView(IBanner.IBannerView iBannerView) {
            reference.clear();
    }

    @Override
    public void responseBannerData() {
            bannerModel.requestBannerData(new IBanner.IBannerModel.CallBack() {
                @Override
                public void onCallBack(BannerBean bannerBean) {
                    bannerView.showBannerData(bannerBean);
                }
            });
    }
}
