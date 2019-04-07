package com.bawei.weekthreemn.di.contract;

import com.bawei.weekthreemn.data.bean.BannerBean;

public interface IBanner {

    public interface IBannerView{

        void showBannerData(BannerBean bannerBean);
    }

    public interface IBannerPresenter<IBannerView>{

        void atteachView(IBannerView bannerView);

        void deatachView(IBannerView bannerView);

        void responseBannerData();
    }

    public interface IBannerModel{

        public interface CallBack{

            void onCallBack(BannerBean bannerBean);
        }

        void requestBannerData(CallBack callBack);
    }
}
