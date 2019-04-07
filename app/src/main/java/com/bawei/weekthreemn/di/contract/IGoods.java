package com.bawei.weekthreemn.di.contract;

import com.bawei.weekthreemn.data.bean.BannerBean;
import com.bawei.weekthreemn.data.bean.InfoBean;

public interface IGoods {

    public interface IGoodsView{

        void showGoodsData(InfoBean infoBean);
    }

    public interface IGoodsPresenter<IGoodsView>{

        void atteachView(IGoodsView goodsView);

        void deatachView(IGoodsView goodsView);

        void responseGoodsData();
    }

    public interface IGoodsModel{

        public interface CallBack{

            void onCallBack(InfoBean infoBean);
        }

        void requestGoodsData(CallBack callBack);
    }
}
