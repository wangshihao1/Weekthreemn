package com.bawei.wangshihao0404mn.di.contract;

import com.bawei.wangshihao0404mn.data.bean.InfoBean;

public interface IInfo {

    public interface  IInfoView{

        void showInfoData(InfoBean infoBean);
    }

    public interface IInfoPresenter<IInfoView>{

        void atteachView(IInfoView infoView);
        void deatachView(IInfoView infoView);

        void responeInfoData(String keyword,int page,int count);
    }

    public interface IInfoModel{

        public interface CallBack{

            void oncallback(InfoBean infoBean);
        }

        void requestInfoData(String keyword,int page,int count,CallBack callBack);
    }
}
